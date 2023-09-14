package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosHotelDAO {

    private Connection con; // variable donde se va a almacenar la conexion

    public UsuariosHotelDAO(Connection con){
        this.con=con;
    }

    public Boolean Login(String Usuario, String Password){ //Metodo para verificar el usuario y contraseña
        try{
           final PreparedStatement Verificacion = con.prepareStatement(
                   "SELECT * FROM usuariosautenticados WHERE usuario = ? AND Password =?");

        try(Verificacion){
            Verificacion.setString(1,Usuario);
            Verificacion.setString(2,Password);

            Verificacion.execute();
            ResultSet AutenticacionUsuario = Verificacion.executeQuery();

            if(AutenticacionUsuario.next()){
                System.out.println("Inicio de sesion exitosa");
                return true;

            }else{
                return false;
            }
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


}
