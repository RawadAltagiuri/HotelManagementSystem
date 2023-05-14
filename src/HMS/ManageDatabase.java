/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMS;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author youse                        //connecting the database
 */
public class ManageDatabase {
    
    public static Connection connectDb(){
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
         
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel","root","" );
            
            return connect; 
             
            
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
