
package Modelo;
// Clase que representa un objeto Cliente con sus atributos
public class Cliente {
    private int id;// Identificador único del cliente
    private int identificacion;// Número de identificación del cliente
    private String nombre;// Nombre del cliente
    private String telefono;// Número de teléfono del cliente
    private String direccion;// Dirección del cliente
    private String razon;// Razón social del cliente (si aplica)
     // Constructor vacío por defecto
    public Cliente() {
    }
    // Constructor con parámetros para inicializar todos los atributos
    public Cliente(int id, int identificacion, String nombre, String telefono, String direccion, String razon) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }
     // Métodos para acceder y modificar el atributo 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Métodos para acceder y modificar el atributo 'identificacion'
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }
    // Métodos para acceder y modificar el atributo 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Métodos para acceder y modificar el atributo 'telefono'
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    // Métodos para acceder y modificar el atributo 'direccion'
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    // Métodos para acceder y modificar el atributo 'razon'
    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
    
}
