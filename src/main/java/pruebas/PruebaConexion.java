package pruebas;

import factory.FabricaConexiones;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        FabricaConexiones factory = new FabricaConexiones();
        Connection con = factory.recuperaConexion();
        System.out.println("cerrando la conexion ");

        con.close();
    }
}
