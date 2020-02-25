/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gorzki.usterki;

/**
 *
 * @author A
 */
import java.sql.*;
import java.util.Properties;
 
/**
 * This program demonstrates how to use UCanAccess JDBC driver to read/write
 * a Microsoft Access database.
 * @author www.codejava.net
 *
 */
public class UcanAccess {
 
    public static void main(String[] args) {
         
        String url = "jdbc:ucanaccess://d://program files//bases//ec4.mdb";
      

        Properties properties = new Properties();
        properties.setProperty("", "");
        properties.setProperty("password", "46");
        //String sql = "SELECT typ, nazwa  from przetworniki_typ T, producenci P where p.idproducenta=t.idproducenta";
        String sql = "SELECT typ, nazwa  from przetworniki_typ T  INNER JOIN producenci P ON T.idproducenta=P.idproducenta";

        try (Connection connection = DriverManager.getConnection(url,properties);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1)+" ");
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException sqlex) {
            System.err.println("Wystąpił błąd w trakcie realizacji operacji bazodanowej, więcej szczegółów:");
            for (Throwable throwable : sqlex) {
                System.err.println(throwable.getMessage());
            }
        } catch (Exception ex) {
            System.err.println("Wystąpił niezidentyfikowany błąd" + ex.getClass().getSimpleName());
            System.exit(1);
        }
    }
}
