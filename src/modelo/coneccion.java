package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class coneccion {
    Connection con;

    public Connection conectar(){
        String url ="jdbc:mysql://localhost:3306/app_prueba";
        String user = "root";
        String pass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
        }

        return con;
}
}
