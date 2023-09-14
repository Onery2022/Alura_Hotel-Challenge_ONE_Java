package modelo;

import java.util.Date;

public class Huespedes {
    private Integer id;
    private String Nombre;
    private String Apellido;
    private Date FechaNacimiento;

    private String Nacionalidad;
    private Integer Telefono;
    private Integer idReserva;


    //Constructor

    public Huespedes(Integer id,String nombre, String apellido, Date fechaNacimiento, String nacionalidad, Integer telefono,Integer IdReserva) {
        this.id=id;
        Nombre = nombre;
        Apellido = apellido;
        FechaNacimiento = fechaNacimiento;
        Nacionalidad = nacionalidad;
        Telefono = telefono;
        this.idReserva=IdReserva;

    }

    public Huespedes(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, Integer telefono) {
        Nombre = nombre;
        Apellido = apellido;
        FechaNacimiento = fechaNacimiento;
        Nacionalidad = nacionalidad;
        Telefono = telefono;

    }


    //Getters y Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public Integer getTelefono() {
        return Telefono;
    }

    public void setTelefono(Integer telefono) {
        Telefono = telefono;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }


}
