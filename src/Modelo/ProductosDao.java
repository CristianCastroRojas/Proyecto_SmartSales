package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class ProductosDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    //Metodo para registrar un producto en la base de datos
    public boolean RegistrarProductos(Productos pro) {
        // Definición de la consulta SQL para insertar un nuevo producto en la tabla 'productos'
        String sql = "INSERT INTO productos (codigo,nombre,proveedor,stock,precio) VALUES (?,?,?,?,?)";
        try {
            // Establecer la conexión a la base de datos
            con = cn.getConnection();
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer los parámetros de la consulta con los valores del objeto Productos
            ps.setString(1, pro.getCodigo()); // Código del producto
            ps.setString(2, pro.getNombre()); // Descripción del producto
            ps.setString(3, pro.getProveedor()); // Proveedor del producto
            ps.setInt(4, pro.getStock()); // Stock del producto
            ps.setDouble(5, pro.getPrecio()); // Precio del producto
            // Ejecutar la consulta SQL
            ps.execute();
            // Si la consulta se ejecuta correctamente, devolver true
            return true;
        } catch (SQLException e) {
            // En caso de error durante la ejecución de la consulta, imprimir el mensaje de error
            System.out.println(e.toString());
            // Devolver false para indicar que ocurrió un error al registrar el producto
            return false;
        }
    }

    //Metodo para consultar los proveedores
    public void ConsultarProveedor(JComboBox proveedor) {
        // Consulta SQL para seleccionar el nombre de todos los proveedores en la tabla "proveedor"
        String sql = "SELECT nombre FROM proveedor";
        try {
            // Establecer la conexión a la base de datos
            con = cn.getConnection();
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();
            // Iterar a través de los resultados
            while (rs.next()) {
                // Agregar cada nombre de proveedor al JComboBox proporcionado como argumento
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            // Capturar y manejar cualquier excepción SQL, imprimiendo el mensaje de error
            System.out.println(e.toString());
        }
    }

    //Metodo para listar un producto
    public List ListarProductos() {
        // Crear una lista para almacenar los productos
        List<Productos> Listapro = new ArrayList();
        // Consulta SQL para seleccionar todos los productos
        String sql = "SELECT * FROM productos";
        try {
            // Establecer la conexión a la base de datos
            con = cn.getConnection();
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Ejecutar la consulta y obtener el resultado
            rs = ps.executeQuery();
            // Iterar a través de los resultados
            while (rs.next()) {
                // Crear un nuevo objeto Productos para almacenar los datos de cada producto
                Productos pro = new Productos();
                // Establecer los valores del producto a partir de los datos obtenidos de la consulta
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                // Agregar el producto a la lista de productos
                Listapro.add(pro);
            }
        } catch (SQLException e) {
            // Capturar y manejar cualquier excepción SQL, imprimiendo el mensaje de error
            System.out.println(e.toString());
        }
        // Devolver la lista de productos
        return Listapro;
    }

    // Método para eliminar un producto de la base de datos por su ID
    public boolean EliminarProductos(int id) {
        // Consulta SQL para eliminar un producto con el ID proporcionado
        String sql = "DELETE FROM productos WHERE id = ?";
        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer el valor del parámetro en la consulta preparada (ID del producto a eliminar)
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
        } finally {
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
    public boolean ModificarProductos(Productos pro) {
        // Consulta SQL para actualizar los datos de un producto
        String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=? WHERE id=?";
        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer los valores de los parámetros en la consulta preparada
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.setInt(6, pro.getId());// Establecer el ID del producto en el último parámetro
            ps.execute();
            // Devolver true para indicar que la modificación se realizó con éxito
            return true;
        } catch (SQLException e) {
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
            // Devolver false para indicar que ocurrió un error durante la ejecución de la consulta
            return false;
            // Cerrar la conexión a la base de datos en el bloque finally para asegurar que se cierre incluso si hay una excepción
        } finally {
            try {
                con.close();
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    //Metodo para buscar un producto por su codigo
    public Productos BuscarPro(String cod) {
        // Se crea un objeto Productos para almacenar la información del producto encontrado
        Productos producto = new Productos();

        // Consulta SQL para buscar un producto por su código
        String SQL = "SELECT * FROM productos WHERE codigo = ?";

        try {
            // Se establece la conexión a la base de datos
            con = cn.getConnection();
            // Se prepara la consulta SQL
            ps = con.prepareStatement(SQL);
            // Se establece el parámetro de la consulta SQL con el código del producto
            ps.setString(1, cod);
            // Se ejecuta la consulta y se obtiene el resultado
            rs = ps.executeQuery();

            // Si se encuentra un producto con el código especificado
            if (rs.next()) {
                // Se establecen los datos del producto encontrado en el objeto Productos
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            // En caso de error, se imprime el mensaje de error en la consola
            System.out.println(e.toString());
        }

        // Se devuelve el objeto Productos con la información del producto encontrado
        return producto;
    }

    public Config BuscarDatos() {
        Config conf = new Config();
        String SQL = "SELECT * from config";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId(rs.getInt("id"));
                conf.setIdentificacion(rs.getString("identificacion"));
                conf.setNombre(rs.getString("nombre"));
                conf.setTelefono(rs.getString("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                conf.setRazon(rs.getString("razon"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return conf;
    }

    // Método para modificar un cliente en la base de datos
    public boolean ModificarDatos(Config conf) {
        // Consulta SQL para actualizar los datos de un producto
        String sql = "UPDATE config SET identificacion=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
            // Preparar la consulta SQL
            ps = con.prepareStatement(sql);
            // Establecer los valores de los parámetros en la consulta preparada
            ps.setString(1, conf.getIdentificacion());
            ps.setString(2, conf.getNombre());
            ps.setString(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setString(5, conf.getRazon());
            ps.setInt(6, conf.getId());// Establecer el ID del producto en el último parámetro
            ps.execute();
            // Devolver true para indicar que la modificación se realizó con éxito
            return true;
        } catch (SQLException e) {
            // En caso de error, imprimir el mensaje de error en la consola
            System.out.println(e.toString());
            // Devolver false para indicar que ocurrió un error durante la ejecución de la consulta
            return false;
            // Cerrar la conexión a la base de datos en el bloque finally para asegurar que se cierre incluso si hay una excepción
        } finally {
            try {
                con.close();
                // En caso de error al cerrar la conexión, imprimir el mensaje de error en la consola
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

}
