package modelo;

public class persona {

    int num_doc;
    String nombre;
    String apellidos;
    String mail;

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public persona() {
    }

    public persona(int num_doc, String nombre, String apellidos, String mail) {
        this.num_doc = num_doc;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
    }
    
    

}
