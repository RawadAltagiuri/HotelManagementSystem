/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author unusualenterprise
 */
public class ManageHotelConfig implements Initializable {
                                                            //these are the sample skeleton controller which are used to create the functions                                                         
    @FXML                                                   //that will connect to the controller to control the app
    private Button closeBtn;
    
    @FXML
    private Button minimizeBtn;
    
    @FXML
    private Label username;
    
    @FXML
    private Group dashboard_btn;
    
    @FXML
    private Rectangle btn_rectangle;
    
    @FXML
    private Button reservations_btn;
    
    @FXML
    private Circle profilePic;
    
    @FXML
    private Button customers_btn;
    
    @FXML
    private AnchorPane dashboard_form;
    
    @FXML
    private Label dashboard_bookToday;

    @FXML
    private Label dashboard_incomeToday; 
    
    @FXML
    private Label dashboard_totalIncome;
    
    @FXML
    private AreaChart<?, ?> dashboard_areaChart;
    
    @FXML
    private AnchorPane availableRooms_form;
    
    @FXML
    private TextField availableRooms_roomNumber;

    @FXML
    private ComboBox<?> availableRooms_roomType;

    @FXML
    private ComboBox<?> availableRooms_status;
    
    @FXML
    private TextField availableRooms_price;
    
    @FXML
    private Button availableRooms_addBtn;
    
    @FXML
    private Button availableRooms_updateBtn;
    
    @FXML
    private Button availableRooms_clearBtn;  
    
    @FXML
    private Button availableRooms_deleteBtn;
    
    @FXML
    private Button availableRooms_checkinBtn;

    @FXML
    private TableView<GetRoomData> availableRooms_tableView;
    
    @FXML
    private TableColumn<GetRoomData, String> availableRooms_col_roomNumber;
 
    @FXML
    private TableColumn<GetRoomData, String> availableRooms_col_roomType;
    
    @FXML
    private TableColumn<GetRoomData, String> availableRooms_col_status;

    @FXML
    private TableColumn<GetRoomData, String> availableRooms_col_price;

    @FXML
    private TextField availableRooms_search;
   
    @FXML
    private AnchorPane customer_Form;
    
    @FXML
    private TableView<?> customer_tableView;
    
    @FXML
    private TableColumn<?, ?> customers_customerNumber;
    
    @FXML
    private TableColumn<?, ?> customers_firstName;
    
    @FXML
    private TableColumn<?, ?> customer_lastName;

    @FXML
    private TableColumn<?, ?> customers_phoneNumber;
    
    @FXML
    private TableColumn<?, ?> customers_totalPayment;

    @FXML
    private TableColumn<?, ?> customers_checkedIn;

    @FXML
    private TableColumn<?, ?> customers_checkedOut;
    
    @FXML
    private TextField customers_search; 
        
    @FXML
    private AnchorPane hotelConfig_form;
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;   // These are Java objects used for database connectivity and manipulation
    private Statement statement;
    private ResultSet result;
    private ResultSet result2;
   
    private ObservableList<GetRoomData> roomDataList; //array list for the room type and availability
    private String type[] = {"Standard Room", "Deluxe Room", "Suite", "Executive Room"};
    public String status[] = {"Available", "Not Available", "Occupied"};
    private double x = 0;
    private double y = 0;
    
    
    


    public ObservableList<GetRoomData> availableRoomsListData() {   //This is a function in Java that retrieves and returns a list of available rooms from a database.

        ObservableList<GetRoomData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `room`";
   
        try (Connection connection = ManageDatabase.connectDb();
            PreparedStatement prepare2 = connection.prepareStatement(sql);
            ResultSet result2 = prepare2.executeQuery())
        {
            while (result2.next()) {
                GetRoomData roomD;
                roomD = new GetRoomData(
                        result2.getInt("RoomNumber"),                 
                         result2.getString("Type"),
                         result2.getString("Availability"),
                         result2.getDouble("Price"));
                listData.add(roomD);
            }
        } catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
    
    

    public void availableRoomsShowData(){       //This populates a TableView with data of available rooms.
        roomDataList = availableRoomsListData(); //function that retrieves and returns a list of available rooms from a database as an ObservableList of GetRoomData objects.


        if (roomDataList != null) {
            availableRooms_col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("room"));
            availableRooms_col_roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            availableRooms_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            availableRooms_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

            availableRooms_tableView.setItems(roomDataList);
        }
        else{

        }
    }
    
    
    
    public void avaialbleRoomsSelectData(){  //retrieves the data of a selected row from a TableView of available rooms and displays the data in relevant input fields for further editing or updating.
        
        GetRoomData roomD  = availableRooms_tableView.getSelectionModel().getSelectedItem();//get room data
        int num = availableRooms_tableView.getSelectionModel().getSelectedIndex(); //get selected index of selected row
        
        if(num < 0 || num >= roomDataList.size()){  //if index out of range do nothing
            return;
        }
        
        availableRooms_roomNumber.setText(String.valueOf(roomD.getRoom()));        //the function populates the input fields with data from the selected GetRoomData object  
        availableRooms_price.setText(String.valueOf(roomD.getPrice()));             // It sets the text of availableRooms_roomNumber and availableRooms_price
        
        String statusToSelect = roomD.getStatus();
        String typeToSelect = roomD.getRoomType();
        ObservableList<?> status = availableRooms_status.getItems();
        ObservableList<?> type = availableRooms_roomType.getItems();

        for (int i = 0; i < status.size(); i++) {
            if (status.get(i).equals(statusToSelect)) {
                availableRooms_status.getSelectionModel().select(i);
                break;
            }
        }
        for (int i = 0; i < type.size(); i++) {
            if (type.get(i).equals(typeToSelect)) {
                availableRooms_roomType.getSelectionModel().select(i);
                break;
            }
        }
  
    }
    
        
    
    public void availableRoomsAdd(){  //adds a new room record to the database table "room".
        String sql = "INSERT INTO `room`(`RoomNumber`, `Type`, `Availability`, `price`) VALUES (?,?,?,?)"; //inserts into the database after taking input from user
        
        connect = ManageDatabase.connectDb();
        
        try{
            
            String roomNumber = availableRooms_roomNumber.getText(); //gets user input
            String type = (String)availableRooms_roomType.getSelectionModel().getSelectedItem();
            String status = (String)availableRooms_status.getSelectionModel().getSelectedItem();
            String price = availableRooms_price.getText();
    
            Alert alert;
            //checking if there are empty fields
            
            if(roomNumber== null || type== null || status== null || price== null){ //if inputs from user null show error
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                
                
            }else{
                
                String check = "SELECT RoomNumber FROM room WHERE RoomNumber = '"+roomNumber+"' ";
                
                prepare = connect.prepareStatement(check);
                result = prepare.executeQuery();
                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR); //if chosing an existing room shows arror
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Room #"+roomNumber+" already exists!");
                    alert.showAndWait();
                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, roomNumber);
                    prepare.setString(2, type);
                    prepare.setString(3, status);
                    prepare.setString(4, price);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION); //else shows its done or added
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();

                    availableRoomsShowData();
                    availableRoomsClear();
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public void availableRoomsUpdate(){ // function for updating the data of a selected room in the database.
        
        String type1 = (String)availableRooms_roomType.getSelectionModel().getSelectedItem(); //gets input from the user
        String status1 = (String)availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum1 = availableRooms_roomNumber.getText();
        String sql = "UPDATE `room` SET `Type`= '"+type1+"'  ,`Availability`= '"+status1+"',`Price`='"+price1+"' WHERE `RoomNumber`= '"+roomNum1+"' "; //update the database
        connect = ManageDatabase.connectDb();

        try 
        {
            
            Alert alert;
            if(roomNum1== null || type1== null || status1== null || price1== null){ //show error if trying to update while inputs are null
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();
                    
                alert = new Alert(Alert.AlertType.INFORMATION); //else update successfuly 
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();
                
                availableRoomsShowData();
                availableRoomsClear();
            }
                   
        }catch(Exception e){e.printStackTrace();}
    
    }
    
    
    
    public void availableRoomsDelete() //function to delete the data for the rooms
    {    
        String type1 = (String)availableRooms_roomType.getSelectionModel().getSelectedItem(); //gets input from the user
        String status1 = (String)availableRooms_status.getSelectionModel().getSelectedItem();
        String price1 = availableRooms_price.getText();
        String roomNum1 = availableRooms_roomNumber.getText();       
        String deleteData = "DELETE FROM `room` WHERE `RoomNumber`= '"+roomNum1+"'   "; //deleting from the database
        connect = ManageDatabase.connectDb();

        try 
        {
            Alert alert;
            if(roomNum1 == null || type1 == null || status1 == null || price1 == null){ //show error if trying to update while inputs are null
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{               
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confiramtion Message"); //confirmation message appears before confirming "are you sure?"
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Room #" + roomNum1 + "?");
                
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
                availableRoomsShowData();
                availableRoomsClear();
            }
                   
        }catch(Exception e){e.printStackTrace();}
    }
    
    
        
    public void availableRoomsClear(){                      //function to clear input fields from the user, resets options to base case
        availableRooms_roomNumber.setText("");
        availableRooms_roomType.getSelectionModel().clearSelection();
        availableRooms_status.getSelectionModel().clearSelection();           
        availableRooms_price.setText("");               
    }
    
  
    
    public void availableRoomsRoomType(){ //function populates the "room type" ComboBox with the available room types from the "type" list.
        List<String> listData = new ArrayList<>();    
        for(String data: type){
            listData.add(data);
        }      
        ObservableList list = FXCollections.observableArrayList(listData);
        availableRooms_roomType.setItems(list);
    }
    
   
    
    public void availableroomStatus(){ //This function populates the "availableRooms_status" ComboBox with status options list
        List<String> listData = new ArrayList<>();
        
        for(String data: status){
            listData.add(data);
        }
        ObservableList list = FXCollections.observableArrayList(listData);
        
        availableRooms_status.setItems(list);
        
    }
    


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        availableRoomsRoomType();
        availableroomStatus();
        availableRoomsShowData();    
    }
}
