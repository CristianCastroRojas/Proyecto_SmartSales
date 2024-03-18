package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

    Connection con;// Objeto de conexión a la base de datos
    Conexion cn = new Conexion();// Instancia de la clase Conexion para establecer la conexión
    PreparedStatement ps;// Objeto para ejecutar consultas preparadas
    ResultSet rs;

    // Método para registrar un nuevo proveedor en la base de datos
    public boolean RegistrarProveedor(Proveedor pr) {
        // Consulta SQL para insertar un nuevo proveedor
        String sql = "INSERT INTO proveedor (nit, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection(); // Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            // Establecer los valores de los parámetros en la consulta preparada
            ps.setInt(1, pr.getNit()); // Nit del proveedor
            ps.setString(2, pr.getNombre()); // Nombre del proveedor
            ps.setString(3, pr.getTelefono()); // Teléfono del proveedor
            ps.setString(4, pr.getDireccion()); // Dirección del proveedor
            ps.setString(5, pr.getRazon()); // Razón social del proveedor
            ps.execute(); // Ejecutar la consulta
            return true; // Devolver verdadero para indicar que el registro se realizó con éxito
        } catch (SQLException e) {
            // En caso de error durante la ejecución de la consulta, imprimir el error en la consola
            System.out.println(e.toString());
            return false;// Devolver falso para indicar que ocurrió un error
        } finally {
            try {
                con.close();// Cerrar la conexión a la base de datos
            } catch (SQLException e) {
                // En caso de error al cerrar la conexión, imprimir el error en la consola
                System.out.println(e.toString());
            }
        }
    }

    //Metodo para listar proveedores en la tabla y base de datos
    public List ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList();// Crear una lista para almacenar los proveedores
        String sql = "SELECT * FROM proveedor";// Consulta SQL para seleccionar todos los proveedores de la tabla
        try {
            con = cn.getConnection();// Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql);// Preparar la consulta SQL
            rs = ps.executeQuery();// Ejecutar la consulta y obtener los resultados en un ResultSet
            // Iterar a través de los resultados del ResultSet
            while (rs.next()) {
                Proveedor pr = new Proveedor();// Crear un nuevo objeto Proveedor para cada registro
                // Asignar los valores de cada columna del resultado al objeto Proveedor
                pr.setId(rs.getInt("id"));
                pr.setNit(rs.getInt("nit"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                // Agregar el objeto Proveedor a la lista
                Listapr.add(pr);
            }
        } catch (SQLException e) {
            // En caso de error durante la ejecución de la consulta, imprimir el error en la consola
            System.out.println(e.toString());
        }
        // Devolver la lista de proveedores
        return Listapr;
    }

    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ?"; // Consulta SQL para eliminar un proveedor por su ID
        try {
            con = cn.getConnection(); // Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el valor del parámetro ID en la consulta preparada
            ps.execute(); // Ejecutar la consulta para eliminar el proveedor
            return true; // Devolver verdadero si la eliminación fue exitosa
        } catch (SQLException e) {
            // En caso de error durante la ejecución de la consulta, imprimir el error en la consola
            System.out.println(e.toString());
            return false; // Devolver falso para indicar que la eliminación falló
        } finally {
            try {
                con.close(); // Cerrar la conexión a la base de datos
            } catch (SQLException e) {
                // En caso de error al cerrar la conexión, imprimir el error en la consola
                System.out.println(e.toString());
            }
        }
    }

    public boolean ModificarProveedor(Proveedor pr) {
        // Consulta SQL para actualizar los datos del proveedor en la base de datos
        String sql = "UPDATE proveedor SET nit=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            con = cn.getConnection(); // Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            // Establecer los valores de los parámetros en la consulta preparada
            ps.setInt(1, pr.getNit()); // Establecer el valor del NIT del proveedor
            ps.setString(2, pr.getNombre()); // Establecer el valor del nombre del proveedor
            ps.setString(3, pr.getTelefono()); // Establecer el valor del teléfono del proveedor
            ps.setString(4, pr.getDireccion()); // Establecer el valor de la dirección del proveedor
            ps.setString(5, pr.getRazon()); // Establecer el valor de la razón social del proveedor
            ps.setInt(6, pr.getId()); // Establecer el valor del ID del proveedor
            ps.execute(); // Ejecutar la consulta SQL para modificar los datos del proveedor
            return true; // Devolver true si la modificación se realizó correctamente
        } catch (SQLException e) {
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
            return false; // Devolver false si ocurrió un error durante la modificación
        } finally {
            try {
                con.close(); // Cerrar la conexión a la base de datos
            } catch (SQLException e) {
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
                System.out.println(e.toString());
            }
        }
    }

}
