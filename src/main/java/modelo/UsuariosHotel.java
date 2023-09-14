package modelo;

public class UsuariosHotel {

    private Integer Id;
    private String Usuario;
    private String CorreoElectronico;
    private Integer Telefono;
    private String Password;

    public UsuariosHotel(String usuario, String correoElectronico, Integer telefono, String password) {
        this.Usuario = usuario;
        this.CorreoElectronico = correoElectronico;
        this.Telefono = telefono;
        this.Password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(Integer telefono) {
        Telefono = telefono;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
