package Modelo;

// Definición de la clase Productos
public class Productos {
    // Atributos de la clase
    private int id; // Identificador único del producto
    private String codigo; // Código del producto
    private String nombre; // Descripción del producto
    private String proveedor; // Proveedor del producto
    private int stock; // Cantidad en stock del producto
    private double precio; // Precio del producto
    
    // Constructor vacío de la clase Productos
    public Productos(){
        
    }

    // Constructor con parámetros de la clase Productos
    public Productos(int id, String codigo, String nombre, String proveedor, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    // Métodos de acceso (getters) para obtener los valores de los atributos
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    // Métodos de modificación (setters) para establecer nuevos valores a los atributos
    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}

