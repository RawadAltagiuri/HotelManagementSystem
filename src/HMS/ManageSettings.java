package HMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    private AnchorPane CreateNewUserForm;

    @FXML
    private Group CreateNewUserFormBtn;

    @FXML
    private TextField CreateNewUserNamefield;

    @FXML
    private TextField CreateNewUserOccupationfield;

    @FXML
    private TextField CreateNewUserConfirm;

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
    private Label Edit;

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
    private Group ProgramSettingsFormButton;

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
    private Label WarningMessageUsername;

    @FXML
    private Rectangle btn_rectangle12;

    @FXML
    private Rectangle btn_rectangle121;

    @FXML
    private Rectangle btn_rectangle122;

    @FXML
    private TextField firstName2;

    @FXML
    private TextField firstnamefield;

    @FXML
    private AnchorPane settings_btnForm32;

    @FXML
    private TextField surnamefield;

    @FXML
    private TextField usernamefield;

    @FXML
    void AddUser(ActionEvent event) {
        String username = CreateNewUsernameField.getText();
        String password = CreateNewUserPasswordField.getText();
        String surname = CreateNewUserSurnamefield.getText();
        String firstname = CreateNewUserNamefield.getText();
        String occupation = CreateNewUserOccupationfield.getText();
      
        if(FXMLDocumentController.getglobalpassword().equals(CreateNewUserConfirm.getText())){
            ConfirmationWarning.setVisible(false);
            GetUsersData.AddUser(username, password, surname, firstname, occupation);
            CreateNewUsernameField.setText("");
            CreateNewUserPasswordField.setText("");
            CreateNewUserSurnamefield.setText("");
            CreateNewUserNamefield.setText("");
            CreateNewUserOccupationfield.setText("");
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
        GetUsersData.deleteUser(DesiredUsername.getText());
        usernamefield.setText("");
        PasswordFieldChange.setText("");
        surnamefield.setText("");
        firstnamefield.setText("");
        OccupationField.setText("");
        IdNumberField.setText("");
    }

    @FXML
    void func(MouseEvent event) {

    }

}
