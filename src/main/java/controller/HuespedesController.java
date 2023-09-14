package controller;

import dao.HuespedesDAO;
import factory.FabricaConexiones;
import modelo.Huespedes;

import java.util.Date;
import java.util.List;

public class HuespedesController {

    private HuespedesDAO huespedesDAO;

    public HuespedesController(){
        var factory = new FabricaConexiones();
        this.huespedesDAO = new HuespedesDAO(factory.recuperaConexion());
    }

    public void guardarHuespedesController(Huespedes huespedes, Integer IdReserva){
        huespedes.setIdReserva(IdReserva);
        huespedesDAO.guardarHuespedes(huespedes);

    }
    public int ModificarHuespedes(String Nombre, String Apellido, Date FechaNacimiento, String Nacionalidad, Integer Telefono, Integer IdReserva, Integer Id){

        java.sql.Date Fechanacimiento = new java.sql.Date(FechaNacimiento.getTime());

        return huespedesDAO.ModificarHuespedes(Nombre,Apellido,Fechanacimiento,Nacionalidad,Telefono,IdReserva, Id);

    }

    public int EliminarHuesped(Integer Id){
        return huespedesDAO.EliminarHuesped(Id);
    }

    //Metodo para llamar el listado de los huespedes
    public List<Huespedes> listar(){
        return huespedesDAO.listar();
    }

    public List<Huespedes> BuscarHuesped(String Apellido){
        return huespedesDAO.BuscadorHuesped(Apellido);
    }

}
