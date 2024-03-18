
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    Connection con;// Objeto para la conexión a la base de datos
    PreparedStatement ps;// Objeto para ejecutar consultas preparadas
    ResultSet rs;// Objeto para almacenar los resultados de la consulta
    Conexion cn = new Conexion ();// Instancia de la clase Conexion para establecer la conexión
    // Método para realizar el inicio de sesión
    public login log(String correo, String pass){
        login l = new login();// Creación de un objeto de tipo login
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";// Consulta SQL para buscar un usuario por correo y contraseña
        try{
           con = cn.getConnection();// Establecer la conexión a la base de datos
           ps = con.prepareStatement(sql);// Preparar la consulta SQL
           ps.setString(1, correo);// Establecer el valor del primer parámetro en la consulta preparada (correo)
           ps.setString(2, pass);// Establecer el valor del segundo parámetro en la consulta preparada (contraseña)
           rs = ps.executeQuery();// Ejecutar la consulta y obtener el resultado
            // Si se encuentra un resultado en el conjunto de resultados
           if(rs.next()){
               // Asignar los valores obtenidos de la base de datos al objeto de tipo login
               l.setId(rs.getInt("id"));
               l.setNombre(rs.getString("nombre"));
               l.setCorreo(rs.getString("correo"));
               l.setPass(rs.getString("pass"));
           }
        }catch (SQLException e){// Captura de excepciones SQL
            // En caso de error, imprimir la descripción del error en la consola
            System.out.println(e.toString());
        }
         // Devolver el objeto de tipo login, que puede estar vacío si no se encontró el usuario
        return l;
    }
}
