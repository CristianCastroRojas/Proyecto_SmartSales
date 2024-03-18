package Modelo;

// Definición de la clase Proveedor
public class Proveedor {

    // Atributos de la clase Proveedor
    private int id; // Identificador único del proveedor
    private int nit; // RUT (Rol Único Tributario) del proveedor
    private String nombre; // Nombre del proveedor
    private String telefono; // Número de teléfono del proveedor
    private String direccion; // Dirección del proveedor
    private String razon; // Razón social del proveedor

    // Constructor por defecto de la clase Proveedor
    public Proveedor() {

    }

    // Constructor parametrizado de la clase Proveedor
    public Proveedor(int id, int nit, String nombre, String telefono, String direccion, String razon) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }

    // Métodos getters y setters para acceder y modificar los atributos de la clase Proveedor
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

}
