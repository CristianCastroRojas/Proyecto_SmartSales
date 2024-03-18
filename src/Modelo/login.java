
package Modelo;
// Clase que representa un objeto de inicio de sesión con sus atributos
public class login {
    private int id;// Identificador único del usuario
    private String nombre;// Nombre del usuario
    private String correo;// Correo electrónico del usuario
    private String pass;// Contraseña del usuario
     // Constructor vacío por defecto
    public login() {
    }
    // Constructor con parámetros para inicializar todos los atributos
    public login(int id, String nombre, String correo, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }
    // Métodos para acceder y modificar el atributo 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // Métodos para acceder y modificar el atributo 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    // Métodos para acceder y modificar el atributo 'correo'
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    // Métodos para acceder y modificar el atributo 'pass'
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
