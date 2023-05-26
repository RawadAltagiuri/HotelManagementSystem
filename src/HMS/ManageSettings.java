package HMS;

import HMS.GetUsersData.User;
import static HMS.GetUsersData.fetchUsersFromDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ManageSettings {

    @FXML
    private Button AddUserBtn;

    @FXML
    private TextField ConfirmPasswordField;

    @FXML
    private Label ConfirmationWarning;

    @FXML
    private Label ConfirmationWarning1;
    
    @FXML
    private ComboBox<?> CreateNewUserComboBox;

    @FXML
    private TextField CreateNewUserConfirm;

    @FXML
    private AnchorPane CreateNewUserForm;

    @FXML
    private Group CreateNewUserFormBtn;

    @FXML
    private TextField CreateNewUserNamefield;

    @FXML
    private TextField CreateNewUserPasswordField;

    @FXML
    private TextField CreateNewUserSurnamefield;

    @FXML
    private TextField CreateNewUsernameField;

    @FXML
    private Button DeleteUser;

    @FXML
    private TextField DesiredUsername;

    @FXML
    private Label Edit1;

    @FXML
    private Group EditUserInfoFormBtn;

    @FXML
    private AnchorPane EditUsersInfo;

    @FXML
    private TextField IdNumberField;

    @FXML
    private Label NameNewUser;

    @FXML
    private Label NameNewUser1;

    @FXML
    private Label NameNewUser11;

    @FXML
    private Label NameNewUser12;

    @FXML
    private Label NameNewUser2;

    @FXML
    private TextField OccupationField;

    @FXML
    private TextField PasswordFieldChange;

    @FXML
    private Button Refresh;

    @FXML
    private AnchorPane Setting_form;

    @FXML
    private Label TitleLabel;

    @FXML
    private Label TitleLabel1;

    @FXML
    private Label TitleLabel2;

    @FXML
    private Label TitleLabel3;

    @FXML
    private Label TitleLabel31;

    @FXML
    private Label TitleLabel32;

    @FXML
    private Label TitleLabel321;

    @FXML
    private Label TitleLabel3211;

    @FXML
    private Label TitleLabel322;

    @FXML
    private Button UpdateUser;

    @FXML
    private TableView<User> UsersTable;

    @FXML
    private Label WarningMessageUsername;

    @FXML
    private Rectangle btn_rectangle121;

    @FXML
    private Rectangle btn_rectangle122;

    @FXML
    private TextField firstName2;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TableColumn<User, String> idcolumn;

    @FXML
    private TableColumn<User, String> namecolumn;

    @FXML
    private AnchorPane settings_btnForm32;

    @FXML
    private TableColumn<User, String> surnamecolumn;

    @FXML
    private TextField surnamefield;

    @FXML
    private TableColumn<User, String> usernamecolumn;

    @FXML
    private TextField usernamefield;
    
    private ObservableList<User> UserList;
    private String titles[] = {"Manager", "Receptionist"};

    @FXML
    void AddUser(ActionEvent event) {
        String username = CreateNewUsernameField.getText();
        String password = CreateNewUserPasswordField.getText();
        String surname = CreateNewUserSurnamefield.getText();
        String firstname = CreateNewUserNamefield.getText();
        String occupation = (String) CreateNewUserComboBox.getSelectionModel().getSelectedItem();;
      
        if(FXMLDocumentController.getglobalpassword().equals(CreateNewUserConfirm.getText())){
            ConfirmationWarning.setVisible(false);
            GetUsersData.AddUser(username, password, firstname, surname, occupation);
            CreateNewUsernameField.setText("");
            CreateNewUserPasswordField.setText("");
            CreateNewUserSurnamefield.setText("");
            CreateNewUserNamefield.setText("");
            CreateNewUserComboBox.getSelectionModel().clearSelection();  ;
        }else{
            ConfirmationWarning.setVisible(true);
        }
    }

    @FXML
    void CallEditUserForm(MouseEvent event) {
        EditUsersInfo.setVisible(true);
        CreateNewUserForm.setVisible(false);
    }
    
    @FXML
    void CallCreateNewUserForm(MouseEvent event) {
        EditUsersInfo.setVisible(false);
        CreateNewUserForm.setVisible(true);
    }

    @FXML
    void FillTextWithUserData(MouseEvent event) {
        GetUsersData.User user = GetUsersData.FillFields(DesiredUsername.getText());
        if (user != null) {
            WarningMessageUsername.setVisible(false);
            IdNumberField.setText(Integer.toString(user.getId()));
            usernamefield.setText(user.getUsername());
            PasswordFieldChange.setText(user.getPassword());
            firstnamefield.setText(user.getName());
            surnamefield.setText(user.getSurname());
            OccupationField.setText(user.getOccupation());
        }else{
            WarningMessageUsername.setVisible(true);
        }
    }
    
    @FXML
    void FillTextWithUserDataTABLE(MouseEvent event) {
        User tableuser = UsersTable.getSelectionModel().getSelectedItem();
        if (tableuser != null) {
            WarningMessageUsername.setVisible(false);
            DesiredUsername.setText(tableuser.getUsername());
            IdNumberField.setText(Integer.toString(tableuser.getId()));
            usernamefield.setText(tableuser.getUsername());
            PasswordFieldChange.setText(tableuser.getPassword());
            firstnamefield.setText(tableuser.getName());
            surnamefield.setText(tableuser.getSurname());
            OccupationField.setText(tableuser.getOccupation());
        }else{
            WarningMessageUsername.setVisible(true);
        }
    }
    
    @FXML
    public void FillUserTable(){
        UserList = fetchUsersFromDatabase(); //function that retrieves and returns a list of available rooms from a database as an ObservableList of GetRoomData objects.
        System.out.println();
        if (UserList != null) {
            System.out.println("Hello");

            namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            surnamecolumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            UsersTable.setItems(UserList);
        }
        else{
        }
    }
    
    
    @FXML
    void UpdateUser(ActionEvent event) {
        String username = usernamefield.getText();
        String password = PasswordFieldChange.getText();
        String surname = surnamefield.getText();
        String firstname = firstnamefield.getText();
        String occupation = OccupationField.getText();
        
        if(FXMLDocumentController.getglobalpassword().equals(ConfirmPasswordField.getText())){
            ConfirmationWarning1.setVisible((false));
            GetUsersData.UpdateUser(username, password, firstname, surname, occupation);
            usernamefield.setText("");
            PasswordFieldChange.setText("");
            surnamefield.setText("");
            firstnamefield.setText("");
            OccupationField.setText("");
            IdNumberField.setText("");
        }
        else{
            ConfirmationWarning1.setVisible((true));
        }
    }
    

    @FXML
    void deleteUser(ActionEvent event) {
        if(FXMLDocumentController.getglobalpassword().equals(ConfirmPasswordField.getText())){
            GetUsersData.deleteUser(DesiredUsername.getText());
            usernamefield.setText("");
            PasswordFieldChange.setText("");
            surnamefield.setText("");
            firstnamefield.setText("");
            OccupationField.setText("");
            IdNumberField.setText("");
            FillUserTable();
        }else{
            ConfirmationWarning1.setVisible((true));
        }
    }

    @FXML
    void func(MouseEvent event) {

    }
    
    public void employeeTitles(){ //function populates the "room type" ComboBox with the available room types from the "type" list.
        List<String> listData = new ArrayList<>();    
        for(String data: titles){
            listData.add(data);
        }      
        ObservableList list = FXCollections.observableArrayList(listData);
        CreateNewUserComboBox.setItems(list);
    }

}
