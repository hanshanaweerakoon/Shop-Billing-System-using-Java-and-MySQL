
package shopbillingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NewClass implements NewInterface1{
    
    public Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShopBillingSystem", "root", "");

        } catch (Exception e) {
            //System.out.println(e);
        }
        return con;
    }
    

    
    
}
