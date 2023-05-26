/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
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
public class ManageReservations implements Initializable {
    
    
    @FXML
    private TextField customerNumber;
    @FXML
    private TextField customerNumber1;
    
    @FXML
    private TextField lastName;
    
    @FXML
    private TextField emailAddress;
    
    @FXML
    private TextField phoneNumber;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private DatePicker checkIn_date;

    @FXML
    private DatePicker checkOut_date;
    @FXML
    private TextField lastName1;
    
    @FXML
    private TextField emailAddress1;
    
    @FXML
    private TextField phoneNumber1;
    
    @FXML
    private TextField firstName1;
    
    @FXML
    private DatePicker checkIn_date1;

    @FXML
    private DatePicker checkOut_date1;
    
    @FXML
    private Label total;

    @FXML
    private Label totalDays;
    @FXML
    private Label total1;

    @FXML
    private Label totalDays1;
    
    @FXML
    private Button checkin_btn;
    
    @FXML
    private Button receipt_btn;
    
    @FXML
    private Button reset_btn;
    @FXML
    private Button checkin_btn1;
    
    @FXML
    private Button receipt_btn1;
    
    @FXML
    private Button reset_btn1;
    
    @FXML
    private ComboBox<?> roomNumber;

    @FXML
    private ComboBox<?> area_type;
    @FXML
    private ComboBox<?> room_type;


    @FXML
    private ComboBox<?> area_number;

    
    @FXML
    private AnchorPane reservations_form;
    
    @FXML
    private AnchorPane event_reservations_form;
    
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;
    private Statement statement;
    private ResultSet result;
    private ResultSet result2;
    private double totalP = 0;
    private double totalP1 = 0;



    public void customerNumber(){
        //add table first to the db
        String customerNumb = "SELECT CustomerID FROM customer ORDER BY CustomerID DESC LIMIT 1;";
        connect = ManageDatabase.connectDb();

        try {
            prepare2 = connect.prepareStatement(customerNumb);
            result2 = prepare2.executeQuery();
            while(result2.next()){
                GetReservationData.customerNum = result2.getInt("CustomerID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        
    public void displayCustomerNumber(){
        customerNumber();
        customerNumber.setText(String.valueOf(GetReservationData.customerNum+1));
        customerNumber1.setText(String.valueOf(GetReservationData.customerNum+1));
    }
    
    
     public void customerCheckIn(){ //connect with the receipt fxml(checkin) on Action
     String insertCustomerData                                                                              
     = "INSERT INTO `customer`(`CustomerID`, `total`, `FirstName`, `LastName`, `PhoneNumber`, `Email`, `RoomNumber`, `RoomType`, `CheckInDate`, `CheckOutDate`) " 
     +"VALUES(?,?,?,?,?,?,?,?,?,?)";

     connect = ManageDatabase.connectDb();
     //add the table to the db

     try {
         String customerNum = customerNumber.getText();//these are connected with the receipt fxml file
         String roomT = (String)room_type.getSelectionModel().getSelectedItem(); //these are connected with the receipt fxml file 
         String roomN = (String)roomNumber.getSelectionModel().getSelectedItem();//these are connected with the receipt fxml file
         String firstN = firstName.getText();//these are connected with the receipt fxml file
         String lastN = lastName.getText();//these are connected with the receipt fxml file
         String phoneNum = phoneNumber.getText();//these are connected with the receipt fxml file
         String email = emailAddress.getText();//these are connected with the receipt fxml file
         String checkInDate = String.valueOf(checkIn_date.getValue());//these are connected with the receipt fxml file
         String checkOutDate = String.valueOf(checkOut_date.getValue());//these are connected with the receipt fxml file

         Alert alert;
         
         if(customerNum == null || firstN == null || lastN == null || phoneNum == null || email == null ||
            checkInDate == null || checkOutDate == null || roomT == null || roomN == null 
                 ){
             alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Message");
             alert.setHeaderText(null);
             alert.setContentText("Please fill all blank fields");
             alert.showAndWait();
         }else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");

            Optional<ButtonType> option = alert.showAndWait();
            String totalStr = total.getText(); // assuming total is a JavaFX Label
            totalStr = totalStr.replaceAll("[^\\d.]", ""); // remove any non-numeric characters except the decimal point
            double totalC = Double.parseDouble(totalStr);

             
             if(option.get().equals(ButtonType.OK)){
                 prepare = connect.prepareStatement(insertCustomerData);
                    prepare.setString(1,customerNum);
                    prepare.setDouble(2, totalC); // use setDouble instead of setString for numeric values
                    prepare.setString(3,firstN);
                    prepare.setString(4,lastN);
                    prepare.setString(5,phoneNum);
                    prepare.setString(6,email);
                    prepare.setString(7, roomN);
                    prepare.setString(8, roomT);
                    prepare.setString(9,checkInDate);
                    prepare.setString(10,checkOutDate);   
                    prepare.executeUpdate();
                 
                 String date = String.valueOf(checkIn_date.getValue());//connect with id total for the receipt information on scene builder TO GET VALUE

                 String customerN = customerNumber.getText();//connect with id total for the receipt information on scene builder TO GEY CUSTOMER NUMBER
                 
                 
                 String customerReceipt = "INSERT INTO customer_receipt (customer_num,total,date)"
                         + "VALUES (?,?,?)";
                 
                 prepare = connect.prepareStatement(customerReceipt);
                 prepare.setString(1, customerN);
                 prepare.setDouble(2, totalC);
                 prepare.setString(3,date);
                 
                 prepare.execute();
                 
                 String sqlEditStatus = "UPDATE room SET Availability = 'Occupied' WHERE roomNumber ='"+roomN+"'";
                 
                 statement = connect.createStatement();
                 statement.executeUpdate(sqlEditStatus);
                 
                 alert = new Alert (Alert.AlertType.INFORMATION);
                 alert.setTitle("Information Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Successfully checked-In!"); //successful checked in infromation to appear
                 alert.showAndWait();
                 
                 reset();
                 displayCustomerNumber();
             }else{
                 return;
             }


         }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void receipt() throws JRException{
        connect = ManageDatabase.connectDb();
        HashMap hash = new HashMap();  //id or customer id , using get from GetReservatioData
        String customerN1 = customerNumber.getText();
        int customerNumberInt = Integer.parseInt(customerN1);
        customerNumberInt -= 1;
        String newCustomerN1 = Integer.toString(customerNumberInt);
        hash.put("hotelParameter", newCustomerN1); //creating a receipt 
        
        try{
            Alert alert;
            if(totalP>0){
            
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
    
    public void reset() { //to reset the options in the reservations fxml(checkin.fxml) 
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        emailAddress.setText("");
        room_type.getSelectionModel().clearSelection();
        roomNumber.getSelectionModel().clearSelection();
        totalDays.setText("----");
        total.setText("$0.0");
        checkOut_date.setValue(null);
        checkIn_date.setValue(null);
    }
    
    public void totalDays(){
        Alert alert;
        Date date = new Date();
        LocalDate today = LocalDate.now();
        LocalDate checkInDate = checkIn_date.getValue();
        LocalDate checkOutDate = checkOut_date.getValue();
        if(checkOutDate.isAfter(checkInDate) && !checkInDate.isBefore(today) ){
            GetReservationData.totalDays = checkOutDate.compareTo(checkInDate);
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid dates");
            alert.showAndWait();
        }
        displayTotal();
    }
    
    public void roomTypeList(){
        String listType = "SELECT * FROM room WHERE Availability = 'Available' GROUP BY Type ORDER BY Type ASC ";                                       
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("Type"));
            }
            room_type.setItems(listData);
            roomNumberList();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void roomNumberList(){
        connect = ManageDatabase.connectDb();
        String item = (String) room_type.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM room WHERE Type = '" 
                + item + "' AND Availability = 'Available' ORDER BY RoomNumber ASC"; 
        try {
            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            while(result.next()){
                listData.add(result.getString("RoomNumber"));
            }
            roomNumber.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotal(){

        String totalID = String.valueOf(GetReservationData.totalDays);
        totalDays.setText(totalID);
        String selectItem = (String)roomNumber.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM room WHERE RoomNumber = '"+selectItem+"' ";
        double priceData = 0;
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                priceData = result.getDouble("Price");
            }
            totalP = ((priceData)*GetReservationData.totalDays);
            total.setText("$" + String.valueOf(totalP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
//   
    
    
    
//   ----------------------------------------------------------area---------------------------------------
 
     public void customerCheckIn1(){ //connect with the receipt fxml(checkin) on Action
     String insertCustomerData                                                                              
     = "INSERT INTO `customer`(`CustomerID`, `total`, `FirstName`, `LastName`, `PhoneNumber`, `Email`,`AreaNumber`, `AreaType`, `CheckInDate`, `CheckOutDate`) " 
     +"VALUES(?,?,?,?,?,?,?,?,?,?)";

     connect = ManageDatabase.connectDb();
     //add the table to the db

     try {
         String customerN1 = customerNumber.getText();//these are connected with the receipt fxml file
         String areaT1 = (String)area_type.getSelectionModel().getSelectedItem(); //these are connected with the receipt fxml file 
         String areaN1 = (String)area_number.getSelectionModel().getSelectedItem();//these are connected with the receipt fxml file
         String firstN1 = firstName1.getText();//these are connected with the receipt fxml file
         String lastN1 = lastName1.getText();//these are connected with the receipt fxml file
         String phoneNum1 = phoneNumber1.getText();//these are connected with the receipt fxml file
         String email1 = emailAddress1.getText();//these are connected with the receipt fxml file
         String checkInDate1 = String.valueOf(checkIn_date1.getValue());//these are connected with the receipt fxml file
         String checkOutDate1 = String.valueOf(checkOut_date1.getValue());//these are connected with the receipt fxml file
         Alert alert;
         
//RECEIPT CREATION IN MINUTE 35.19
         if(
            customerN1 == null || firstN1 == null || lastN1 == null || phoneNum1 == null || email1 == null ||
            checkInDate1 == null || checkOutDate1 == null || areaT1 == null || areaN1 == null     
                 ){
             alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Message");
             alert.setHeaderText(null);
             alert.setContentText("Please fill all blank fields");
             alert.showAndWait();
         }else{
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");

            Optional<ButtonType> option = alert.showAndWait();
            String totalStr = total1.getText(); // assuming total is a JavaFX Label
            totalStr = totalStr.replaceAll("[^\\d.]", ""); // remove any non-numeric characters except the decimal point
            double totalC = Double.parseDouble(totalStr);

             
             if(option.get().equals(ButtonType.OK)){
                 prepare = connect.prepareStatement(insertCustomerData);
                    prepare.setString(1,customerN1);
                    prepare.setDouble(2, totalC); // use setDouble instead of setString for numeric values
                    prepare.setString(3,firstN1);
                    prepare.setString(4,lastN1);
                    prepare.setString(5,phoneNum1);
                    prepare.setString(6,email1);
                    prepare.setString(7, areaN1);
                    prepare.setString(8, areaT1);
                    prepare.setString(9,checkInDate1);
                    prepare.setString(10,checkOutDate1);   
                    prepare.executeUpdate();
                 
 
                 String date1 = String.valueOf(checkIn_date1.getValue());//connect with id total for the receipt information on scene builder TO GET VALUE
                 
      
                 String customer1 = customerNumber.getText();//connect with id total for the receipt information on scene builder TO GEY CUSTOMER NUMBER
                 
                 
                 String customerReceipt = "INSERT INTO customer_receipt (customer_num,total,date)"
                         + "VALUES (?,?,?)";
                 
                 prepare = connect.prepareStatement(customerReceipt);
                 prepare.setString(1, customer1);
                 prepare.setDouble(2, totalC);
                 prepare.setString(3,date1);
                 
                 prepare.execute();
                 
                 String sqlEditStatus = "UPDATE area SET Availability = 'Occupied' WHERE AreaNumber ='"+areaN1+"'";
                 
                 statement = connect.createStatement();
                 statement.executeUpdate(sqlEditStatus);
                 
                 alert = new Alert (Alert.AlertType.INFORMATION);
                 alert.setTitle("Information Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Successfully checked-In!"); //successful checked in infromation to appear
                 alert.showAndWait();
                 
                 reset1();
                 displayCustomerNumber();
             }else{
                 return;
             }
         }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    

   
       public void reset1() { //to reset the options in the reservations fxml(checkin.fxml) 
        firstName1.setText("");
        lastName1.setText("");
        phoneNumber1.setText("");
        emailAddress1.setText("");
        area_type.getSelectionModel().clearSelection();
        area_number.getSelectionModel().clearSelection();
        totalDays1.setText("----");
        total1.setText("$0.0");
        checkOut_date1.setValue(null);
        checkIn_date1.setValue(null);
    }
    
    public void totalDays1(){
        Alert alert;
        Date date = new Date();
        LocalDate today = LocalDate.now();
        LocalDate checkInDate = checkIn_date1.getValue();
        LocalDate checkOutDate = checkOut_date1.getValue();
        if(checkOutDate.isAfter(
                checkInDate) && !checkInDate.isBefore(today) ){
            GetReservationData.totalDays = checkOutDate.compareTo(checkInDate);
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid dates");
            alert.showAndWait();
        }
        displayTotal1();
    }
    
    public void roomTypeList1(){
        String listType = "SELECT * FROM area WHERE Availability = 'Available' GROUP BY Type ORDER BY Type ASC ";    
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(listType);
            result = prepare.executeQuery();
            while(result.next()){
                listData.add(result.getString("Type"));
            }
            area_type.setItems(listData);

            FacilityNumberList();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void FacilityNumberList(){
        connect = ManageDatabase.connectDb();
        String item = (String) area_type.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM area WHERE Type = '" 
                + item + "' AND Availability = 'Available' ORDER BY AreaNumber ASC"; 
        try {
            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            result = preparedStatement.executeQuery();
            while(result.next()){
                listData.add(result.getString("AreaNumber"));
            }
            area_number.setItems(listData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotal1(){

        String totalID = String.valueOf(GetReservationData.totalDays);
        totalDays1.setText(totalID);
        String selectItem = (String)area_number.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM area WHERE AreaNumber = '"+selectItem+"' ";
        double priceData1 = 0;
        connect = ManageDatabase.connectDb();
        try {
            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                priceData1 = result.getDouble("Price");
            }
            totalP1 = ((priceData1)*GetReservationData.totalDays);
            total1.setText("$" + String.valueOf(totalP1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    

//    
    public void switchForm(MouseEvent event) throws IOException
    {
        Group button = (Group) event.getSource();     
        switch(button.getId()) {
          case "room_reservarion_btn":
            event_reservations_form.setVisible(false);
            reservations_form.setVisible(true);
            displayCustomerNumber();
              break;
          case "event_reservarion_btn":
            event_reservations_form.setVisible(true);
            reservations_form.setVisible(false);
            displayCustomerNumber();
            break;
        }        
    }
//    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayCustomerNumber();
        roomTypeList();
        roomTypeList1();
    }
    
}