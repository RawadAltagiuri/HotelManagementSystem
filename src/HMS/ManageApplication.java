package HMS;

import static com.ibm.icu.impl.PluralRulesLoader.loader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManageApplication extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws Exception {
        
     
        Parent root = FXMLLoader.load(getClass().getResource("login-window.fxml")); //this code opens the fxaml design decided, so this will load login fxml file

        
        Scene scene = new Scene(root);

        // set up the drag functionality
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> { // allowing the dragging of the app, needs to be limited
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.4);
        });

        root.setOnMouseReleased((MouseEvent event) -> { // used to make app transparent when dragged, might be useless
            stage.setOpacity(1);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());         //setting bounds and screen ration 
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
