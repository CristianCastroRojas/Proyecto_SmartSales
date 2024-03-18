
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con; // Objeto para almacenar la conexión a la base de datos
    // Método para establecer una conexión a la base de datos
    public Connection getConnection(){
        try{
            // Cadena de conexión a la base de datos MySQL
            String myBD = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=UTC";
             // Intentar establecer la conexión usando DriverManager
            con = DriverManager.getConnection(myBD, "root", "");// URL, usuario, contraseña
            // Si la conexión se establece correctamente, devolver el objeto Connection
            return con;
        }catch (SQLException e){// Captura de excepciones SQL
            // En caso de error, imprimir la descripción del error en la consola
            System.out.println(e.toString());
        }
        // Si ocurre un error, devolver null
        return null;
    }
}
