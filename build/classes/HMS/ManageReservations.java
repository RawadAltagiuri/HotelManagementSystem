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
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author unusualenterprise
 */
public class ManageReservations implements Initializable {
    
    
    @FXML
    private TextField customerNumber;   //these are the sample skeleton controller which are used to create the functions
                                        //that will connect to the controller to control the app
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
    private Label total;

    @FXML
    private Label totalDays;
    
    @FXML
    private ComboBox<?> roomNumber;

    @FXML
    private ComboBox<?> roomType;
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;
    private Statement statement;
    private ResultSet result;
    private ResultSet result2;

    
    
    @FXML
    private AnchorPane reservations_form;
    
    


//    public void setReservationsAnchorPaneVisibility(boolean visible) {
//        reservations_form.setVisible(visible);
//    }
    
    

    public void customerNumber(){           //gets the customerID values by retreiving customer table and sets the reservation data and customer number to customer id 
        //add table first to the db         //
        String customerNumb = "SELECT CustomerID FROM customer ";
        connect = ManageDatabase.connectDb();

        try {
            prepare2 = connect.prepareStatement(customerNumb);
            result2 = prepare2.executeQuery();
            while(result2.next()){

            GetReservationData.customerNum = result.getInt("CustomerID");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    public void displayCustomerNumber(){ //function to display the customer number
        customerNumber();
        customerNumber.setText(String.valueOf(GetReservationData.customerNum+1));

    }
    
    
    
    public void customerCheckIn(){ //function to add a new customer's check-in data into the database
     String insertCustomerData = "INSERT INTO customer (CustomerID, FirstName, LastName, PhoneNumber, Email, CheckInDate, CheckOutDate)"
     +"[VALUES(?,?,?,?,?,?,?)]"; //query to add dtat into the table

     connect = ManageDatabase.connectDb();
     //add the table to the db

     try {
         String customerNum = customerNumber.getText(); //gets user input
         String firstN = firstName.getText();
         String lastN = lastName.getText();
         String phoneNum = phoneNumber.getText();
         String email = emailAddress.getText();
         String checkInDate = String.valueOf(checkIn_date.getValue());
         String checkOutDate = String.valueOf(checkOut_date.getValue());

         Alert alert;

         if(customerNum == null || firstN == null || lastN == null || phoneNum == null || email == null ||
            checkInDate == null || checkOutDate == null){

             alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Message");               //shows error if inputs are null
             alert.setHeaderText("null");
             alert.setContentText("Please fill all blank fields");
             alert.showAndWait();
         }else{

             alert = new Alert(Alert.AlertType.CONFIRMATION); //shows confirmation message are inputing data
             alert.setTitle("Confirmation Message");
             alert.setHeaderText("null");
             alert.setContentText("Are you sure?");

             Optional<ButtonType> option = alert.showAndWait();
             if(option.get().equals(ButtonType.OK)){
                 prepare = connect.prepareStatement(insertCustomerData);
                 prepare.setString(1,customerNum);
                 prepare.setString(2,firstN);
                 prepare.setString(3,lastN);
                 prepare.setString(4,phoneNum);
                 prepare.setString(5,email);
                 prepare.setString(6,checkInDate);
                 prepare.setString(7,checkOutDate);   
                 prepare.executeUpdate();
                 alert.setTitle("Information Message");             //shows successful message after inserting data
                 alert.setHeaderText("null");
                 alert.setContentText("Successfully checked-In!");
                 alert.showAndWait();

             }else{
                 return;
             }


         }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void totalDays(){ //function that calculates the total number of days between a check-in date and a check-out date selected by the user.

        Date date = new Date();
        if((checkOut_date.getValue().isAfter(checkIn_date.getValue()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");                //error incase check out is before check in, incase some illogical expresion is made
            alert.setHeaderText("null");
            alert.setContentText("Invalid check-out date");
            alert.showAndWait();
        }else{
            GetReservationData.totalDays = checkOut_date.getValue().compareTo(checkIn_date.getValue());
        }
        displayTotal();
    }
    
    public void roomTypeList(){ //function that queries the database to retrieve a list of the available room types and populates the menu with the results from the database
    String listType = "SELECT * FROM room WHERE Availability = 'Available' "; //shows available rooms from database

    connect = ManageDatabase.connectDb();
    //add the table to the db

    try {

        ObservableList listData = FXCollections.observableArrayList();
        prepare = connect.prepareStatement(listType);
        result = prepare.executeQuery();

        while(result.next()){
            listData.add(result.getString("Type"));
        }

        roomType.setItems(listData);
        roomNumberList();


    } catch (Exception e) {
        e.printStackTrace();
    }

}

    public void roomNumberList(){ //function that queries the database to retrieve a list of the available room numbers and pupolates its menu with the results from the datbase
        connect = ManageDatabase.connectDb();
        String item = (String) roomType.getSelectionModel().getSelectedItem();
        String sql = "SELECT * FROM room WHERE Type = ?";


        try {
            ObservableList listData = FXCollections.observableArrayList();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, item);
            result = prepare.executeQuery();

            while(result.next()){
                listData.add(result.getInt("RoomNumber"));

            }
            roomNumber.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayTotal(){ //function that is responsible for displaying the total number of days and the total price of the reservation

        String totalID = String.valueOf(GetReservationData.totalDays);
        totalDays.setText(totalID);

        String selectItem = (String)roomNumber.getSelectionModel().getSelectedItem();

        String sql = "SELECT * FROM room WHERE roomNumber = '"+selectItem+"' ";

        double priceData = 0;
        connect = ManageDatabase.connectDb();

        try {

            ObservableList listData = FXCollections.observableArrayList();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                priceData = result.getDouble("price");
            }

            float totalP = (float) ((priceData)*GetReservationData.totalDays);
            total.setText("$" + String.valueOf(totalP));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayCustomerNumber();
        roomTypeList();
        roomNumberList();
    }
    
}
