/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMS;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author youse
 */
public class FXMLDocumentController implements Initializable {
        
    @FXML
    private Text hotel_name;
    
    @FXML
    private Text hotel_name1;
    
    @FXML
    private AnchorPane stack_form;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button closeBtn;
    
    @FXML
    private Button minimizeBtn;
   
    //---------forms_btns---------------
 
    @FXML
    private Group dashboard_btn; 
    @FXML
    private Group reservations_btn; 
    @FXML
    private Group rooms_btn; 
    @FXML
    private Group events_btn; 
    @FXML
    private Group guests_btn; 
    @FXML
    private Group staff_btn;    
    @FXML
    private Group hotelConfig_btn;
    @FXML
    private Group logout_btn;
    @FXML
    private Group settings_btn;
    @FXML
    private Label profileUsername;    
    
    @FXML
    private AnchorPane lastVisibleForm;
    @FXML
    private AnchorPane dashboard_form;  
    @FXML
    private AnchorPane reservations_form;  
    @FXML
    private AnchorPane rooms_form;  
    @FXML
    private AnchorPane guests_form;  
    @FXML
    private AnchorPane staff_form;  
    @FXML
    private AnchorPane hotelConfig_form;
    @FXML
    private AnchorPane Setting_form;
    
    
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Pane centerPane;
    
    
    
    
    
    //---------controllers---------------  
    @FXML
    private ManageReservations manageReservations;
    @FXML
    private ManageDashboard manageDashboard;
    @FXML
    private ManageGuests manageGuests;
    @FXML
    private ManageHotelConfig manageHotelConfig;
    @FXML
    private ManageApplication manageApplication;


    //Database tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    private static String globalbuser;
    private static String globalpassword;

    
    public void setHotelName() {
        String sql = "SELECT `hotel_name` FROM `hotelsettings` WHERE 1";
        String hotelName = "";

        try (Connection connection = ManageDatabase.connectDb();
             PreparedStatement prepare2 = connection.prepareStatement(sql);
             ResultSet result = prepare2.executeQuery()) {

            if (result.next()) {
                hotelName = result.getString("hotel_name");
                if (hotel_name != null) {
                    hotel_name.setText(hotelName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setHotelName1() {
        String sql = "SELECT `hotel_name` FROM `hotelsettings` WHERE 1";
        String hotelName = "";

        try (Connection connection = ManageDatabase.connectDb();
             PreparedStatement prepare2 = connection.prepareStatement(sql);
             ResultSet result = prepare2.executeQuery()) {

            if (result.next()) {
                hotelName = result.getString("hotel_name");
                if (hotel_name1 != null) {
                    hotel_name1.setText(hotelName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void checkPressedKey(){
        stack_form.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });
    }


    public void login() {
        String user = username.getText();
        globalbuser = user;
        String pass = password.getText();
        globalpassword = pass;
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";

        connect = ManageDatabase.connectDb();
        
        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            result = prepare.executeQuery();

            Alert alert;
            //check if inputs are empty
            if (user.isEmpty() || pass.isEmpty()) {
             
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all the blank fields");
              alert.showAndWait();
                
            }else{

                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Logged in!");
                    alert.showAndWait();
                    //hide login page after login
                    loginBtn.getScene().getWindow().hide();
                    GetReservationData.username = username.getText();
                    Parent root = FXMLLoader.load(getClass().getResource("main-window.fxml"));
                    
        
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event)->{
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseDragged((MouseEvent event)->{
                        stage.setX(event.getSceneX() - x);
                        stage.setY(event.getSceneY() - y);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setX(0);
                    stage.setY(0);
                    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
                    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
                    stage.show();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                    Parent root = FXMLLoader.load(getClass().getResource("login-window.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setX(0);
                    stage.setY(0);
                    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
                    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
                    stage.show();
                
                logout_btn.getScene().getWindow().hide();
            }else{
                return;
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    

    public void close(){
        System.exit(0);
    }
    
    
    
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    

 
    
    public void switchForm(MouseEvent event) throws IOException
    {
        Group button = (Group) event.getSource();     
        switch(button.getId()) {
          case "dashboard_btn":
              FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("dashboard-window.fxml"));
              Pane dashboardPane = dashboardLoader.load();
              mainBorderPane.setCenter(dashboardPane);
              break;
          case "reservations_btn":
              FXMLLoader reservationsdLoader = new FXMLLoader(getClass().getResource("reservations-window.fxml"));
              Pane reservationsPane = reservationsdLoader.load();
              mainBorderPane.setCenter(reservationsPane);
              break;
          case "rooms_btn":
              FXMLLoader roomsdLoader = new FXMLLoader(getClass().getResource("rooms-window.fxml"));
              Pane roomsPane = roomsdLoader.load();
              mainBorderPane.setCenter(roomsPane);
              break;
          case "events_btn":
              FXMLLoader eventsLoader = new FXMLLoader(getClass().getResource("events-window.fxml"));
              Pane eventsPane = eventsLoader.load();
              mainBorderPane.setCenter(eventsPane);
              break;
          case "guests_btn":
              FXMLLoader guestsLoader = new FXMLLoader(getClass().getResource("guests-window.fxml"));
              Pane guestsPane = guestsLoader.load();
              mainBorderPane.setCenter(guestsPane);
              break;
          case "staff_btn":
              FXMLLoader staffLoader = new FXMLLoader(getClass().getResource("staff-window.fxml"));
              Pane staffPane = staffLoader.load();
              mainBorderPane.setCenter(staffPane);
              break;
          case "hotelConfig_btn":
              FXMLLoader hotelConfigLoader = new FXMLLoader(getClass().getResource("hotelConfig-window.fxml"));
              Pane hotelConfigPane = hotelConfigLoader.load();
              mainBorderPane.setCenter(hotelConfigPane);
              break;
          case "settings_btn":
              String manager = "Manager";
              String temp = getglobaluser();
              String occup = GetUsersData.getOccupation(temp);
              if(occup.equals(manager)){
                FXMLLoader SettingLoader = new FXMLLoader(getClass().getResource("Setting-Window.fxml"));
                Pane SettingPane = SettingLoader.load();
                mainBorderPane.setCenter(SettingPane);
              }
              else{
                Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("User does not have authority for this action");
                alert.showAndWait();
              }
              break;
        }      
   
    }
    
    public void ChangeDisplayName(){
        profileUsername.setText(GetUsersData.getFullName(globalbuser));
    }
    public static String getglobaluser() {
        return globalbuser;
    }
    
    public static String getglobalpassword() {
        return globalpassword;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        setHotelName();
        setHotelName1();
        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("dashboard-window.fxml"));
        try{
            Pane dashboardPane = dashboardLoader.load();
            mainBorderPane.setCenter(dashboardPane);
        }catch(Exception e){e.printStackTrace();}   
    }
}

