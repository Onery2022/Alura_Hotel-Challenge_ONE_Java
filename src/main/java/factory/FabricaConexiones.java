package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FabricaConexiones {

    private DataSource dataSource;

    //costructor para establecer la conexion con la base de datos
    public FabricaConexiones() {
        var comboPooleDataSource = new ComboPooledDataSource();
        comboPooleDataSource.setJdbcUrl("jdbc:mysql://localhost/bade_datos_hotel_alura?useTimeZone=true&serverTimeZone=UTC");
        comboPooleDataSource.setUser("root");
        comboPooleDataSource.setPassword("Nery2@2@");
        comboPooleDataSource.setMaxPoolSize(10);

        this.dataSource = comboPooleDataSource;
    }

    //metodo para llamar la conexion a la base de datos
    public Connection recuperaConexion(){
        try{
            return this.dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
