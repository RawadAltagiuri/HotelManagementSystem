package HMS;

import static HMS.FXMLDocumentController.getglobaluser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 *
 * @author yesimyigitbasi
 */

public class ManageStaff implements Initializable{
    
    
    @FXML
    private FontAwesomeIcon SearchIcon;

    @FXML
    private TextField employee2Delete;
    
    @FXML
    private Button newEmployeeClear;

    @FXML
    private Button employeeDelete;

    @FXML
    private TableColumn<GetStaffData, String> employeeFirstName;

    @FXML
    private TableColumn<GetStaffData, Integer> employeeID;

    @FXML
    private TableColumn<GetStaffData, String> employeeIdx;

    @FXML
    private TableColumn<GetStaffData, String> employeeLanguages;

    @FXML
    private TableColumn<GetStaffData, String> employeeLastName;

    @FXML
    private TableColumn<GetStaffData, String> employeePhoneNumber;

    @FXML
    private TextField employeeSearch;

    @FXML
    private TableColumn<GetStaffData, String> employeeSupervisor;

    @FXML
    private AnchorPane employeeTableView_anchorpane;

    @FXML
    private TableColumn<GetStaffData, String> employeeTitle;

    @FXML
    private TableView<GetStaffData> employee_tableView;

    @FXML
    private AnchorPane manage_staff_btnForm1;

    @FXML
    private Button newEmployeeAdd;

    @FXML
    private TextField newEmployeeFirstName;

    @FXML
    private TextField newEmployeeID;

    @FXML
    private TextField newEmployeeLanguages;

    @FXML
    private TextField newEmployeeLastName;

    @FXML
    private TextField newEmployeePhoneNumber;

    @FXML
    private TextField newEmployeeSupervisor;

    @FXML
    private AnchorPane newEmployee_anchorpane;

    @FXML
    private ComboBox<?> newEmplyeeTitle;

    @FXML
    private AnchorPane staff_Form;

    @FXML
    private AnchorPane staff_form;

    @FXML
    private StackPane staff_stackpane;

    @FXML
    private AnchorPane staffform;
    
    
    private Connection connect;
    private PreparedStatement prepare; // These are Java objects used for database connectivity and manipulation
    private Statement statement;
    private ResultSet result;
    
    private ObservableList<GetStaffData> employeeDataList;
    private ObservableList<GetStaffData> employeeDataListSearchBar;//array list for the room type and availability
    private String titles[] = {"Manager", "Receptionist", "Chef", "Maintenance", "Housekeeping", "Waiter", "Concierge", "Front desk"};
    private double x = 0;
    private double y = 0;
    
    
    public ObservableList<GetStaffData> listEmployeeData(){ // retrieve employee data
        ObservableList<GetStaffData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `employee`";
        try (Connection connection = ManageDatabase.connectDb();
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet result = prepare.executeQuery())
        {
            while (result.next()) {
                GetStaffData staffData;
                staffData = new GetStaffData(
                        result.getInt("EmployeeID"),                 
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Title"),
                        result.getString("Supervisor"),
                        result.getString("Language"),
                        result.getString("PhoneNumber"));
                listData.add(staffData);
            }
        } catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    public ObservableList<GetStaffData> listEmployeeDataSearchBar(){ // retrieve employee data
        ObservableList<GetStaffData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee WHERE EmployeeID LIKE '" + employeeSearch.getText() +"' OR FirstName LIKE '" + employeeSearch.getText() + "%' OR LastName LIKE '" + employeeSearch.getText() + "%' OR Title LIKE '" + employeeSearch.getText() + "%' OR Supervisor LIKE '" + employeeSearch.getText() + "%' OR BirthDate LIKE '" + employeeSearch.getText() + "%' OR HireDate LIKE '" + employeeSearch.getText() + "%' OR PhoneNumber LIKE '" + employeeSearch.getText() + "%' OR Address LIKE '" + employeeSearch.getText() + "%' OR Extra LIKE '" + employeeSearch.getText() + "%' OR Language LIKE '" + employeeSearch.getText() + "%'";
        try (Connection connection = ManageDatabase.connectDb();
            PreparedStatement prepare = connection.prepareStatement(sql);
            ResultSet result = prepare.executeQuery())
        {
            while (result.next()) {
                GetStaffData staffData;
                staffData = new GetStaffData(
                        result.getInt("EmployeeID"),                 
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Title"),
                        result.getString("Supervisor"),
                        result.getString("Language"),
                        result.getString("PhoneNumber"));
                listData.add(staffData);
            }
        } catch(Exception e){
            Alert alert;
            alert = new Alert(AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return listData;
    }
    
    public void showEmployeeDataSearchBar(){ // populate tableview
        if(employeeSearch.getText().equals("")){
            showEmployeeData();
        }
        employeeDataListSearchBar = listEmployeeDataSearchBar(); //function that retrieves and returns a list of available rooms from a database as an ObservableList of GetRoomData objects.
            
        if (employeeDataListSearchBar != null) {
            employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
            employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
            employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
            employeeTitle.setCellValueFactory(new PropertyValueFactory<>("employeeTitle"));
            employeeSupervisor.setCellValueFactory(new PropertyValueFactory<>("employeeSupervisor"));
            employeeLanguages.setCellValueFactory(new PropertyValueFactory<>("employeeLanguages"));
            employeePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("employeePhoneNumber"));
            employee_tableView.setItems(employeeDataListSearchBar);
        }
        else{
            System.out.println("elseeeeee");
        }
    }
    
    public void showEmployeeData(){ // populate tableview
        employeeDataList = listEmployeeData(); //function that retrieves and returns a list of available rooms from a database as an ObservableList of GetRoomData objects.
        if (employeeDataList != null) {
            employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
            employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("employeeFirstName"));
            employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
            employeeTitle.setCellValueFactory(new PropertyValueFactory<>("employeeTitle"));
            employeeSupervisor.setCellValueFactory(new PropertyValueFactory<>("employeeSupervisor"));
            employeeLanguages.setCellValueFactory(new PropertyValueFactory<>("employeeLanguages"));
            employeePhoneNumber.setCellValueFactory(new PropertyValueFactory<>("employeePhoneNumber"));
            employee_tableView.setItems(employeeDataList);
        }
        else{
            return;
        }
    }
    
    public void addEmployee(){ // add employee to tableview & DB
        String manager = "Manager";
        String temp = getglobaluser();
        String occup = GetUsersData.getOccupation(temp);
        if(!occup.equals(manager)){
            Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("User does not have authority for this action");
                alert.showAndWait();
                return;
        }
        System.out.println("it is here now");
        String sql = "INSERT INTO `employee`(`EmployeeID`, `FirstName`, `LastName`, `Title`, `Supervisor`, `Language`, `PhoneNumber`)  VALUES (?,?,?,?,?,?,?)";//inserts into the database after taking input from user
        connect = ManageDatabase.connectDb();
        try{
            String employeeID = newEmployeeID.getText(); //gets user input
            String firstName = (String)newEmployeeFirstName.getText();
            String lastName = (String)newEmployeeLastName.getText();
            String title = (String) newEmplyeeTitle.getSelectionModel().getSelectedItem();
            String supervisor = (String)newEmployeeSupervisor.getText();
            String languages = (String)newEmployeeLanguages.getText();
            String phoneNumber = (String)newEmployeePhoneNumber.getText();
            Alert alert;
            //checking if there are empty fields
            if(employeeID== null || firstName== null || lastName== null || title== null || supervisor== null || languages== null || phoneNumber== null){ //if inputs from user null show error
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                        
            }else{    
                String check = "SELECT EmployeeID FROM employee WHERE EmployeeID = '"+employeeID+"' ";  
                prepare = connect.prepareStatement(check);
                result = prepare.executeQuery();
                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR); //if chosing an existing room shows arror
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee #"+employeeID+" already exists!");
                    alert.showAndWait();
                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, employeeID);
                    prepare.setString(2, firstName);
                    prepare.setString(3, lastName);
                    prepare.setString(4, title);
                    prepare.setString(5, supervisor);
                    prepare.setString(6, languages);
                    prepare.setString(7, phoneNumber);
                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION); //else shows its done or added
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();
                    showEmployeeData();
                    newEmployeeClear();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void newEmployeeClear(){                      //function to clear input fields from the user, resets options to base case
        newEmployeeID.setText("");
        newEmployeeFirstName.setText("");  
        newEmployeeLastName.setText("");           
        newEmplyeeTitle.getSelectionModel().clearSelection();       
        newEmployeeSupervisor.setText("");  
        newEmployeeLanguages.setText(""); 
        newEmployeePhoneNumber.setText("");  
    }
   
    
    public void employeeSelectData(){  //retrieves the data of a selected row from a TableView of available rooms and displays the data in relevant input fields for further editing or updating.
        GetStaffData employeeData  = employee_tableView.getSelectionModel().getSelectedItem();
        int num = employee_tableView.getSelectionModel().getSelectedIndex(); //get selected index of selected row
        if(num < 0 || num >= employeeDataList.size()){  //if index out of range do nothing
            return;
        }
        employee2Delete.setText(String.valueOf(employeeData.getEmployeeID()));        //the function populates the input fields with data from the selected GetRoomData object  
      
    }
    

    public void deleteEmployee(){ // delete employee from tableview & DB
        String manager = "Manager";
        String temp = getglobaluser();
        String occup = GetUsersData.getOccupation(temp);
        if(!occup.equals(manager)){
            Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Only accessible by managers");
                alert.showAndWait();
                return;
        }
        String employeeID = newEmployeeID.getText(); //gets user input
        String firstName = (String)newEmployeeFirstName.getText();
        String lastName = (String)newEmployeeLastName.getText();
        String deleteData = "DELETE FROM `employee` WHERE `EmployeeID`= '"+ employee2Delete.getText() +"' "; //deleting from the database
        connect = ManageDatabase.connectDb();
        try 
        {
            Alert alert;
            if(employeeID== null || firstName== null || lastName== null){ //show error if trying to update while inputs are null
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            }else{               
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message"); //confirmation message appears before confirming "are you sure?"
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete employee #" + employee2Delete.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    alert = new Alert(Alert.AlertType.INFORMATION); //successfully added message appears
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly deleted!");
                    alert.showAndWait();
                }else{
                    return;
                }
                showEmployeeData();
                newEmployeeClear();
            }    
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void employeeTitles(){ //function populates the "room type" ComboBox with the available room types from the "type" list.
        List<String> listData = new ArrayList<>();    
        for(String data: titles){
            listData.add(data);
        }      
        ObservableList list = FXCollections.observableArrayList(listData);
        newEmplyeeTitle.setItems(list);
    }
    
    public void staffSearch()
    { //connect with search bar in the staff fxml file (On Key Typed)
        FilteredList<GetStaffData> filter = new FilteredList<>(employeeDataList, e -> true);
        employeeSearch.textProperty().addListener((Observable, OldValue, newValue) ->{
            filter.setPredicate(predicateStaff ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if(predicateStaff.getEmployeeID().toString().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeeFirstName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeeLastName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeeTitle().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeeSupervisor().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeeLanguages().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateStaff.getEmployeePhoneNumber().toLowerCase().contains(searchKey)){
                    return true;
                }else
                {
                    return false;
                }
            });
        });
        SortedList<GetStaffData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(employee_tableView.comparatorProperty());
        employee_tableView.setItems(sortList);                
    }
    
    //@Override
    public void initialize(URL url, ResourceBundle rb) {
         showEmployeeData();
         staffSearch();
    }
}