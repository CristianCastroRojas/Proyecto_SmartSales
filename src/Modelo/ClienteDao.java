package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDao {
    
    Conexion cn = new Conexion(); // Instancia de la clase Conexion para establecer la conexión a la base de datos
    Connection con; // Objeto para la conexión a la base de datos
    PreparedStatement ps; // Objeto para ejecutar consultas preparadas
    ResultSet rs;// Objeto para almacenar los resultados de una consulta
    
    // Método para registrar un nuevo cliente en la base de datos
    public boolean RegistrarCliente(Cliente cl){
        String sql = "INSERT INTO clientes (identificacion, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)"; // Consulta SQL para insertar un nuevo cliente
        try{
            con = cn.getConnection(); // Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, cl.getIdentificacion()); // Establecer el valor del primer parámetro en la consulta preparada (identificación)
            ps.setString(2, cl.getNombre()); // Establecer el valor del segundo parámetro en la consulta preparada (nombre)
            ps.setString(3, cl.getTelefono()); // Establecer el valor del tercer parámetro en la consulta preparada (teléfono)
            ps.setString(4, cl.getDireccion()); // Establecer el valor del cuarto parámetro en la consulta preparada (dirección)
            ps.setString(5, cl.getRazon()); // Establecer el valor del quinto parámetro en la consulta preparada (razón social)
            ps.execute(); // Ejecutar la consulta
            // Si la consulta se ejecuta correctamente, devolver true
            return true;
        } catch (SQLException e){
            // En caso de error, mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, e.toString());
            // Devolver false para indicar que ocurrió un error durante la ejecución de la consulta
            return false;
        } finally {
            try{
                con.close(); // Cerrar la conexión a la base de datos
            } catch (SQLException e){
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
                System.out.println(e.toString());
            }
        }
    }  
    
    // Método para obtener una lista de todos los clientes almacenados en la base de datos
    public List ListarCliente(){
        List<Cliente>ListaCl = new ArrayList();// Lista para almacenar objetos Cliente recuperados de la base de datos
        String sql = "SELECT * FROM clientes";// Consulta SQL para seleccionar todos los registros de la tabla clientes
        try{
           con = cn.getConnection();// Establecer la conexión a la base de datos
           ps = con.prepareStatement(sql);// Preparar la consulta SQL
           rs = ps.executeQuery();// Ejecutar la consulta y obtener el conjunto de resultados
           // Recorrer cada fila del conjunto de resultados
            while (rs.next()) {
                // Crear un nuevo objeto Cliente y asignar los valores recuperados de la base de datos
                Cliente cl =  new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setIdentificacion(rs.getInt("identificacion"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                // Agregar el objeto Cliente a la lista
                ListaCl.add(cl);
            }
        }catch(SQLException e){
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
        }
        // Devolver la lista de Clientes
        return ListaCl;
    }
    
    // Método para eliminar un cliente de la base de datos por su ID
    public boolean EliminarCliente(int id){
        // Consulta SQL para eliminar un cliente con el ID proporcionado
        String sql = "DELETE FROM clientes WHERE id = ?";
        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer el valor del parámetro en la consulta preparada (ID del cliente a eliminar)
            ps.setInt(1, id);
            // Ejecutar la consulta
            ps.execute();
            // Si la consulta se ejecuta correctamente, devolver true
            return true;
        } catch (SQLException e) {
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
            // Devolver false para indicar que ocurrió un error durante la ejecución de la consulta
            return false;
        }finally{
            // Cerrar la conexión a la base de datos en el bloque finally para asegurar que se cierre incluso si hay una excepción
            try {
                con.close();
            } catch (SQLException ex) {
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
                System.out.println(ex.toString());
            }
        }
    }
    
    // Método para modificar un cliente en la base de datos
    public boolean ModificarCliente(Cliente cl){
        // Consulta SQL para actualizar los datos de un cliente
        String sql = "UPDATE clientes SET identificacion=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
             // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer los valores de los parámetros en la consulta preparada
            ps.setInt(1, cl.getIdentificacion());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.setInt(6, cl.getId());// Establecer el ID del cliente en el último parámetro
            ps.execute();
            // Devolver true para indicar que la modificación se realizó con éxito
            return true;
        } catch (SQLException e) {
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
            // Devolver false para indicar que ocurrió un error durante la ejecución de la consulta
            return false;
         // Cerrar la conexión a la base de datos en el bloque finally para asegurar que se cierre incluso si hay una excepción
        }finally{
            try{
               con.close();
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
}

