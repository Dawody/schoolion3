/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author mdawod
 */
public class dbmanager {
    
           Connection myconn ;
           Statement  mystmt ;
           ResultSet  myrs   ;
           String conn       ;

    public dbmanager(){
          myconn = null;
          mystmt = null;
          myrs   = null;
          conn  = "jdbc:mysql://localhost:3306/schoolion1";
        try {
            myconn = DriverManager.getConnection(conn ,"root","dawod123@SQL");
        } catch (SQLException ex) {
            Logger.getLogger(dbmanager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
    
    
    public ResultSet execute(String q){
               try {
                   mystmt = myconn.createStatement();
                   myrs = mystmt.executeQuery(q);
               } catch (SQLException ex) {
                   Logger.getLogger(dbmanager.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           return myrs;
    }
            
    
    
    public int eid(String query)
    {
      Statement mystm=null;
      int myres=0;
      if(conn!=null)
      {
        try{
        mystm=(Statement)myconn.createStatement();
        myres=mystm.executeUpdate(query);
        return myres;
           } catch(SQLException exc) {
        exc.printStackTrace();
        JOptionPane.showMessageDialog(null, "incorrect data");
      }
       
      }
      return 0;
    }
    
    
    
    
}
