package controller;

import dao.UsuariosHotelDAO;
import factory.FabricaConexiones;


public class UsuariosHotelController {

    private UsuariosHotelDAO usuariosHotelDAO;

    public UsuariosHotelController(){
        var factory = new FabricaConexiones();
        this.usuariosHotelDAO = new UsuariosHotelDAO(factory.recuperaConexion());
    }

    //Metodo para verificar el usuario y contraseña
    public Boolean AutenticacionLogin(String Usuario, String Password){
        return usuariosHotelDAO.Login(Usuario,Password);
    }


}
