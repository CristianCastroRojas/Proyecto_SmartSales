package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int IdVenta() {
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }

    public int RegistrarVenta(Venta v) {
        String sql = "INSERT INTO ventas (cliente,vendedor,total, fecha) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.setString(4,v.getFecha());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return r;
    }

    public int RegistrarDetalle(Detalle Dv) {
        String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, id_venta) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getCod_pro());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }

    public boolean ActualizarStock(int cant, String cod) {
        String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
        public List Listarventas() {
        // Crear una lista para almacenar los productos
        List<Venta> ListaVenta = new ArrayList();
        // Consulta SQL para seleccionar todos los productos
        String sql = "SELECT * FROM ventas";
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
                Venta vent = new Venta();
                // Establecer los valores del producto a partir de los datos obtenidos de la consulta
                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("cliente"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));
                // Agregar el producto a la lista de productos
                ListaVenta.add(vent);
            }
        } catch (SQLException e) {
            // Capturar y manejar cualquier excepción SQL, imprimiendo el mensaje de error
            System.out.println(e.toString());
        }
        // Devolver la lista de productos
        return ListaVenta;
    }
}
