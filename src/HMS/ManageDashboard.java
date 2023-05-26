/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HMS;

import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**---
 *
 * @author youse
 */
public class ManageDashboard implements Initializable{

    @FXML
    private AreaChart<?, ?> dashboard_areaChart;

    @FXML
    private Label dashboard_bookToday;

    @FXML
    private Label dashboard_incomeToday;

    @FXML
    private Label dashboard_totalIncome;


    
    private Connection connect;            // These are Java objects used for database connectivity and manipulation
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private int count;
    


    public void countdashboard_bookToday(){
        
        Date date = new Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        String sql = "SELECT COUNT(CustomerID) FROM customer WHERE CheckInDate = '"+sqldate+"'";
        
        connect =  ManageDatabase.connectDb();
        
        count = 0; 
        try{
            
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();   
            while (result.next()){
                count = result.getInt("COUNT(CustomerID)");
            }
    
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    public void dashboardDisplaydashboard_bookToday(){
        countdashboard_bookToday();
        dashboard_bookToday.setText(String.valueOf(count));
    }
    
    private double sumToday = 0;
    public void countdashboard_incomeToday(){
        
        Date date =  new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT SUM(total) FROM customer_receipt WHERE date = '"+sqlDate+"'";
   
        connect = ManageDatabase.connectDb();
                
        try{
            
            prepare = connect.prepareStatement(sql);
            result =  prepare.executeQuery();
            
            while (result.next()){
                sumToday = result.getDouble("SUM(total)");
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void dashboardDisplaytodayIncome(){
        
        countdashboard_incomeToday();
        dashboard_incomeToday.setText("$" + String.valueOf(sumToday));
        
    }
    
    private double  overall = 0;
    
    public void sumTotalIncome(){
        
        String sql = "SELECT SUM(total) from customer_receipt ";
        
        connect = ManageDatabase.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while(result.next()){
                overall = result.getDouble("SUM(total)");
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    
    public void displayTotalIncome(){
        sumTotalIncome();
        
        dashboard_totalIncome.setText("$" +String.valueOf(overall));
    }
    
    public void dashboardChart(){
        dashboard_areaChart.getData().clear();
        
        String sql = "SELECT date, total FROM customer_receipt GROUP BY date order BY TIMESTAMP(date) ASC LIMIT 8";
        
        connect = ManageDatabase.connectDb();
        
        XYChart.Series chart =  new XYChart.Series();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            
            dashboard_areaChart.getData().add(chart);
        }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        dashboardDisplaydashboard_bookToday();
        dashboardDisplaytodayIncome();
        displayTotalIncome();
        dashboardChart();
    }
    
}
