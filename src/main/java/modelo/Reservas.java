package modelo;

import java.util.Date;

public class Reservas {

    private Integer id;
    private Date FechaEntrada;
    private Date FechaSalida;
    private Double Valor;
    private String FormaPago;



    //Constructor
    public Reservas(Integer Id, Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
        this.id=Id;
        FechaEntrada = fechaEntrada;
        FechaSalida = fechaSalida;
        Valor = valor;
        FormaPago = formaPago;
    }
    public Reservas(Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
        FechaEntrada = fechaEntrada;
        FechaSalida = fechaSalida;
        Valor = valor;
        FormaPago = formaPago;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        FechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        FechaSalida = fechaSalida;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String formaPago) {
        FormaPago = formaPago;
    }

    @Override
    public String toString() {

        return String.format(
                "{0id: %d, fechaentrada: %s, fechasalida: %s, valor: %s, formapago: %d}",
                this.id, this.FechaEntrada, this.FechaSalida, this.Valor, this.FormaPago);
    }
}
