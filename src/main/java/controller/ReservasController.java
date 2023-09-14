package controller;

import dao.ReservasDAO;
import factory.FabricaConexiones;
import modelo.Reservas;

import java.util.Date;
import java.util.List;

public class ReservasController {

    private ReservasDAO reservasDAO;

    //Contructor para enviar la conexion de la base de datos a la clase ReservasDAO

    public ReservasController(){
        var factory = new FabricaConexiones();
        this.reservasDAO = new ReservasDAO(factory.recuperaConexion());
    }

    //Metodo controlador para enviarm los parametros para guardar la reservacion
    public void guardarReservacion(Reservas reservas){
        reservasDAO.guardarReserva(reservas);
    }

    public int ModificarReservas(Date FechaEntrada, Date FechaSalida, Double Valor, String FormaPago, Integer id){

        java.sql.Date fechaEntrada = new java.sql.Date(FechaEntrada.getTime());
        java.sql.Date fechaSalida = new java.sql.Date(FechaSalida.getTime());

        return reservasDAO.Modificar(fechaEntrada,fechaSalida,Valor,FormaPago, id);
    }

    public Integer llamadoNumeroReferencia(){
        return reservasDAO.NumeroReferencia();
    }

    public List<Reservas> lista(){
        return reservasDAO.listar();
    }

    public List<Reservas> BuscarReserva(Integer Id){
        return reservasDAO.BuscarReservas(Id);
    }

    public int eliminar( Integer id){
        return reservasDAO.eliminar(id);
    }


}
