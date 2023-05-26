/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author unusualenterprise
 */
public class ManageGuests implements Initializable {

    @FXML
    private DatePicker checkIn;

    @FXML
    private DatePicker checkOut;

    @FXML
    private Button checkout_btn;

    @FXML
    private Button clear;

    @FXML
    private TextField email;
    
    @FXML
    private TextField lastName;

    @FXML
    private Button receipt;

    @FXML
    private Label total;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField num;

    @FXML
    private TextField phone;

    @FXML
    private Button update;
    
    @FXML
    private AnchorPane guests_form;
    
    @FXML
    private TableView<GetGuestData> guests_TableView;
    
    
    @FXML
    private TextField customers_search; 
        
    @FXML
    private TableColumn<GetGuestData, String> customers_customerNumber;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_firstName;
    
    @FXML
    private TableColumn<GetGuestData, String> customer_lastName;

    @FXML
    private TableColumn<GetGuestData, String> customers_phoneNumber;

    @FXML
    private TableColumn<GetGuestData, String> customers_emailAddress;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_totalPayment;

    @FXML
    private TableColumn<GetGuestData, String> customers_checkedIn;

    @FXML
    private TableColumn<GetGuestData, String> customers_checkedOut;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_roomType;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_roomNumber;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_areaType;
    
    @FXML
    private TableColumn<GetGuestData, String> customers_areaNumber;
    
    @FXML
    private ComboBox<String> roomNum;
    @FXML
    private ComboBox<String> roomType;
    
    @FXML
    private ComboBox<?> areaNumber;
    @FXML
    private ComboBox<?> areaType;
    
    private String type[] = {"Standard Room", "Deluxe Room", "Suite", "Executive Room"};
    public String status[] = {"Available", "Not Available", "Occupied"};
    
//    private ObservableList<GetFacilitiesData> areaDataList;
    private String areaTypes[] = {"Bar", "Big Hall", "Conference Room", "Fitness Center", "Small Hall", "Spa", "Swimming Pool"};
    

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;  
    

   
 
    public ObservableList<GetGuestData> guestListData() {

        ObservableList<GetGuestData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM `customer`";
   
        try (Connection connection = ManageDatabase.connectDb();
            PreparedStatement prepare2 = connection.prepareStatement(sql);
            ResultSet result2 = prepare2.executeQuery())
        {
            GetGuestData guestD;
            while (result2.next()) {
                guestD = new GetGuestData(
                    result2.getInt("CustomerID"),    
                    result2.getString("FirstName"),                 
                    result2.getString("LastName"),
                    result2.getString("PhoneNumber"),
                    result2.getString("Email"),
                    result2.getDouble("total"),
                    result2.getDate("CheckInDate"),
                    result2.getDate("CheckOutDate"),
                    result2.getString("RoomNumber"),
                    result2.getString("RoomType"),
                    result2.getString("AreaNumber"),
                    result2.getString("AreaType"));
                listData.add(guestD);
            }
        } catch(Exception e){e.printStackTrace();}
        
        return listData;
    }
    
    
    private ObservableList<GetGuestData> guestListData;
    public void guestShowData(){

        guestListData = guestListData();
        
        if (guestListData != null) {

            customers_customerNumber.setCellValueFactory(new PropertyValueFactory<>("customerNum"));

            customers_firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

            customer_lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
  
            customers_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));

            customers_totalPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
 
            customers_emailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));

            customers_checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
 
            customers_checkedOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
            
            customers_roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            
            customers_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
            
            customers_areaType.setCellValueFactory(new PropertyValueFactory<>("areaType"));
            
            customers_areaNumber.setCellValueFactory(new PropertyValueFactory<>("areaNum"));


            guests_TableView.setItems(guestListData);
        }
        else{
            return;
        }
    }
    
        public void receipt() throws JRException{
        connect = ManageDatabase.connectDb();
        HashMap hash = new HashMap();  //id or customer id , using get from GetReservatioData
        hash.put("hotelParameter", num.getText()); //creating a receipt 
        
        try{
            Alert alert;
            if(num.getText() != null){
            
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\frees\\Documents\\GitHub\\Hotel-Bushra\\src\\HMS\\report.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, connect);

            JasperViewer.viewReport(jPrint, false);
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Receipt");
                alert.showAndWait();                                    //information to show if receipt is invalid
            }
                                                                   
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
   
    public void guestSearch()
    { //connect with search bar in the guests fxml file (On Key Typed)
        
        FilteredList<GetGuestData> filter = new FilteredList<>(guestListData, e -> true);
        
        customers_search.textProperty().addListener((Observable, OldValue, newValue) ->{
            
            filter.setPredicate(predicateGuest ->{
                
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                
                if(predicateGuest.getCustomerNum().toString().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getFirstName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getLastName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getTotal().toString().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getPhoneNumber().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getEmailAddress().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getCheckIn().toString().contains(searchKey)){
                    return true;
                }else if(predicateGuest.getCheckOut().toString().contains(searchKey)){
                    return true;
                }else
                {
                    return false;
                }
   
            });
        });  
        SortedList<GetGuestData> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(guests_TableView.comparatorProperty());
        guests_TableView.setItems(sortList); 
    }
    
    
     public void guestsSelectData(){  //retrieves the data of a selected row from a TableView of available rooms and displays the data in relevant input fields for further editing or updating.
        
        GetGuestData guestData  = guests_TableView.getSelectionModel().getSelectedItem();
        int nums = guests_TableView.getSelectionModel().getSelectedIndex(); //get selected index of selected row
        
        if(nums < 0 || nums >= guestListData.size()){  //if index out of range do nothing
            return;
        }
        
        guestClear();
        RoomTypes();
        RoomNums();
        AreaTypes();
        AreaNums();
         
        num.setText(String.valueOf(guestData.getCustomerNum()));
        firstName.setText(String.valueOf(guestData.getFirstName()));
        lastName.setText(String.valueOf(guestData.getLastName()));             
        phone.setText(String.valueOf(guestData.getPhoneNumber()));
        email.setText(String.valueOf(guestData.getEmailAddress()));
        String dateString = String.valueOf(guestData.getCheckIn()); 
        LocalDate date = LocalDate.parse(dateString);
        checkIn.setValue(date);
        String dateString2 = String.valueOf(guestData.getCheckOut()); 
        LocalDate date2 = LocalDate.parse(dateString2);
        checkOut.setValue(date2);
        total.setText("$" + String.valueOf(guestData.getTotal()));
        
        
        String numToSelect = guestData.getRoomNum();
        String typeToSelect = guestData.getRoomType();
        String areaNumToSelect = guestData.getAreaNum();
        String areaTypeToSelect = guestData.getAreaType();
        ObservableList<?> roomNums = roomNum.getItems();
        ObservableList<?> roomTypes = roomType.getItems();
        ObservableList<?> areaNums = areaNumber.getItems();
        ObservableList<?> areaTypes = areaType.getItems();

         
        if(numToSelect == null ){
            roomNum.getSelectionModel().clearSelection();
        }else{
            for (int i = 0; i < roomNums.size(); i++) {
                if (roomNums.get(i)!= null && roomNums.get(i).equals(numToSelect)) {
                    roomNum.getSelectionModel().select(i);
                    break;
                }
            }
        }       
         if(typeToSelect == null){
            roomType.getSelectionModel().clearSelection();
            roomNum.getSelectionModel().clearSelection();
        }else{             
            for (int i = 0; i < roomTypes.size(); i++) {
                if (roomTypes.get(i).equals(typeToSelect)) {
                    roomType.getSelectionModel().select(i);
                    break;
                }
            }
        }
        if(areaNumToSelect == null){
            areaNumber.getSelectionModel().clearSelection();
        }else{
            for (int i = 0; i < areaNums.size(); i++) {
                if (areaNums.get(i)!= null && areaNums.get(i).equals((String)areaNumToSelect)) {
                    areaNumber.getSelectionModel().select(i);
                    break;
                }
            }
        }
        if(areaTypeToSelect == null){
            areaType.getSelectionModel().clearSelection();
        }else{

           for (int i = 0; i < areaTypes.size(); i++) {
               if (areaTypes.get(i).equals(areaTypeToSelect)) {
                   areaType.getSelectionModel().select(i);
                   break;
               }
           }
        }

    }  
 
    public void guestClear(){    
        num.setText("");
        firstName.setText("");
        lastName.setText("");
        phone.setText("");
        email.setText("");
        areaType.getSelectionModel().clearSelection();
        areaNumber.getSelectionModel().clearSelection();
        roomType.getSelectionModel().clearSelection();
        roomNum.getSelectionModel().clearSelection();
        total.setText("$0.0");
        checkIn.setValue(null);
        checkOut.setValue(null);
    }
    
    public void roomTypeList(){
        String listType = "SELECT * FROM room WHERE Availability = 'Available' GROUP BY type ORDER BY Type ASC ";                                       
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("Type"));
            }
            roomType.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }


    public void roomNumberList(){
        Connection connect = ManageDatabase.connectDb();

        String item = (String) roomType.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM room WHERE Type = '" 
                + item + "' AND Availability = 'Available' ORDER BY RoomNumber ASC"; 
     
        if(item != null){                
            try {
                ObservableList listData = FXCollections.observableArrayList();
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                result = preparedStatement.executeQuery();

                while(result.next()){
                    listData.add(result.getString("RoomNumber"));
                }
                roomNum.setItems(listData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void RoomTypes() {
        ObservableList list = FXCollections.observableArrayList();
        Connection connect = ManageDatabase.connectDb();
        String query = "SELECT DISTINCT RoomType FROM customer";
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(query);
            result = preparedStatement.executeQuery(query);

            while (result.next()) {
                String roomTypes = result.getString("RoomType");
                if(roomTypes != null && !roomTypes.isEmpty()){
                    list.add(roomTypes);
                }
            }

            roomType.setItems(list);

        } catch (SQLException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }
    public void RoomNums() {
        ObservableList list = FXCollections.observableArrayList();
        Connection connect = ManageDatabase.connectDb();
        String query = "SELECT DISTINCT RoomNumber FROM customer";
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(query);
            result = preparedStatement.executeQuery(query);

            while (result.next()) {
                String roomNums = result.getString("RoomNumber");
                list.add(roomNums);
            }

            roomNum.setItems(list);

        } catch (SQLException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }
   
   

        public void updateGuest(){
            
        GetGuestData guestData  = guests_TableView.getSelectionModel().getSelectedItem();
            int nums = guests_TableView.getSelectionModel().getSelectedIndex(); //get selected index of selected row

            if(nums < 0 || nums >= guestListData.size()){  //if index out of range do nothing
                return;
            }

            String previousRoomNumber = guestData.getRoomNum() ;
            String previousAreaNumber = guestData.getAreaNum() ;        
            
         
          
            String numToSelect = guestData.getRoomNum();
            String areaNumToSelect = guestData.getAreaNum();


            String customerNum1 = num.getText();
            String first = firstName.getText();
            String last = lastName.getText();
            String mob = phone.getText();
            String em = email.getText();
            String type1 = (String)roomType.getSelectionModel().getSelectedItem();
            String num1 = (String)roomNum.getSelectionModel().getSelectedItem();
            String areaType1 = (String)areaType.getSelectionModel().getSelectedItem();
            String areaNum1 = (String)areaNumber.getSelectionModel().getSelectedItem();

            if(type1!= null){
                String add1 = "UPDATE customer SET `RoomType`='"+type1+"' WHERE `CustomerID`= '"+customerNum1+"' ";  
                try {
                    prepare.executeUpdate(add1);
                }catch(Exception e){e.printStackTrace();}
            }
            if(num1!= null){
                String add2 = "UPDATE customer SET `RoomNumber`= '"+num1+"' WHERE `CustomerID`= '"+customerNum1+"'";
                String roomUpdate2 = "UPDATE `room` SET `Availability` = 'Occupied' WHERE RoomNumber = '"+num1+"' ";
                String roomUpdate = "UPDATE `room` SET `Availability` = 'Available' WHERE RoomNumber = '"+previousRoomNumber+"' ";
                try {
                    prepare.executeUpdate(add2);
                    prepare.executeUpdate(roomUpdate2);
                    prepare.executeUpdate(roomUpdate);
                }catch(Exception e){e.printStackTrace();}
            }
            if(areaType1!= null){
                String add3 = "UPDATE customer SET `AreaType`='"+areaType1+"'WHERE `CustomerID`= '"+customerNum1+"' ";
                try {
                    prepare.executeUpdate(add3);
                }catch(Exception e){e.printStackTrace();}
            }
            if(areaNum1!= null){
                String add4 = "UPDATE customer SET `AreaNumber`= '"+areaNum1+"'WHERE `CustomerID`= '"+customerNum1+"' ";
                String areaUpdate2 = "UPDATE `room` SET `Availability` = 'Occupied' WHERE RoomNumber = '"+areaNum1+"' ";
                String areaUpdate = "UPDATE `area` SET `Availability` = 'Available' WHERE AreaNumber = '"+previousAreaNumber+"' "; 

                try {
                    prepare.executeUpdate(add4);
                    prepare.executeUpdate(areaUpdate);
                    prepare.executeUpdate(areaUpdate2);
                }catch(Exception e){e.printStackTrace();}
            }

            String sql = "UPDATE `customer` SET `CustomerID`= '"+customerNum1+"'  ,`FirstName`= '"+first+"',"
            + "`LastName`='"+last+"' ,`PhoneNumber`= '"+mob+"'  ,`Email`= '"+em+"'"
            + " WHERE `CustomerID`= '"+customerNum1+"' "; 
            connect = ManageDatabase.connectDb();

        try 
        {
            
            Alert alert;
            if(customerNum1== null || first== null || last== null || mob== null || mob== null
                    || em== null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();
                    
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully updated!");
                alert.showAndWait();
                
                guestClear();
                guestShowData(); //to show tableview for the guest tab
            }
                   
        }catch(Exception e){e.printStackTrace();}
        

    }
    
    
     public void guestCheckOut()
    {  
 
        GetGuestData guestData  = guests_TableView.getSelectionModel().getSelectedItem();
            int nums = guests_TableView.getSelectionModel().getSelectedIndex(); //get selected index of selected row

            if(nums < 0 || nums >= guestListData.size()){  //if index out of range do nothing
                return;
            }
        String previousRoomNumber = guestData.getRoomNum() ;
        String previousAreaNumber = guestData.getAreaNum() ;
        String customerNum1 = num.getText();
        String first = firstName.getText();
        String last = lastName.getText();   
        String deleteData = "DELETE FROM `customer` WHERE `CustomerID`= '"+customerNum1+"'   ";
        String roomUpdate = "UPDATE `room` SET `Availability` = 'Available' WHERE RoomNumber = '"+previousRoomNumber+"' ";
        String areaUpdate = "UPDATE `area` SET `Availability` = 'Available' WHERE AreaNumber = '"+previousAreaNumber+"' ";
        connect = ManageDatabase.connectDb();

        try 
        {
            Alert alert;
            if(customerNum1 == null || first == null || last == null ){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the data first");
                alert.showAndWait();
            
            }else{               
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confiramtion Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Room #" + customerNum1 + "?");
                
                Optional<ButtonType> option = alert.showAndWait();
                if(option.get().equals(ButtonType.OK)){
                    
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);
                    statement.executeUpdate(roomUpdate);
                    statement.executeUpdate(areaUpdate);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfuly checkedOut!");
                    alert.showAndWait();

                }else{
                    return;
                }
                guestClear();
                guestShowData(); //to show tableview for the guest tab
            }
                   
        }catch(Exception e){e.printStackTrace();}
    }   
    
    
    
    
//     ---------------------------AREA-------------------------------
     
     
     
     
    public void areaTypeList(){
        String listType = "SELECT * FROM area WHERE Availability = 'Available' GROUP BY Type ORDER BY Type ASC ";                                       
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("Type"));
            }
            areaType.setItems(listData);
            areaNumberList();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public void areaNumberList(){
        Connection connect = ManageDatabase.connectDb();

        String item = (String) areaType.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM area WHERE Type = '" 
                + item + "' AND Availability = 'Available' ORDER BY AreaNumber ASC"; 
     
        if(item != null){                
            try {
                ObservableList listData = FXCollections.observableArrayList();
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                result = preparedStatement.executeQuery();

                while(result.next()){
                    listData.add(result.getString("AreaNumber"));
                }
                areaNumber.setItems(listData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
        public void AreaTypes() {
        ObservableList list = FXCollections.observableArrayList();
        Connection connect = ManageDatabase.connectDb();
        String query = "SELECT DISTINCT AreaType FROM customer";
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(query);
            result = preparedStatement.executeQuery(query);

            while (result.next()) {
                String areaTypes = result.getString("AreaType");
                if(areaTypes != null){
                    list.add(areaTypes);
                }
            }

            areaType.setItems(list);

        } catch (SQLException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }
        public void AreaNums() {
        ObservableList list = FXCollections.observableArrayList();
        Connection connect = ManageDatabase.connectDb();
        String query = "SELECT DISTINCT AreaNumber FROM customer";
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(query);
            result = preparedStatement.executeQuery(query);

            while (result.next()) {
                String areaNums = result.getString("AreaNumber");
                list.add(areaNums);
            }

            areaNumber.setItems(list);

        } catch (SQLException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guestShowData(); //to show tableview for the guest tab
        guestSearch();//to search guest data on table view
        guestsSelectData();
        RoomTypes();
        RoomNums();
        AreaTypes();
        AreaNums();

    }
}


