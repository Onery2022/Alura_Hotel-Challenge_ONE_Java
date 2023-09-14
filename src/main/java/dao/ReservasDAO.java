package dao;

import controller.HotelAluraGeneralController;
import modelo.Reservas;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {
    private Connection con;
    private HotelAluraGeneralController hotelAluraGeneralController= new HotelAluraGeneralController();

    //Constructor para establecer la conexion
    public ReservasDAO(Connection con){
        this.con =con;
    }

    public void guardarReserva(Reservas reservas){ //Metodo para guardar las reservas del hotel
    try  {
        PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO RESERVAS"
                    +"(FechaEntrada, FechaSalida, Valor, FormaPago)"
                    +"VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

    try(statement) {
        statement.setDate(1, (Date) reservas.getFechaEntrada());
        statement.setDate(2, (Date) reservas.getFechaSalida());
        statement.setDouble(3, reservas.getValor());
        statement.setString(4, reservas.getFormaPago());

        statement.execute();

        final ResultSet resultSet = statement.getGeneratedKeys();

        try (resultSet) {
            while (resultSet.next()) {
                reservas.setId(resultSet.getInt(1));

                System.out.println("La reserva se ingreso con exito");
            }
        }
    }
     }catch (SQLException e){
        throw new RuntimeException(e);
     }
  }

    //Metodo para Modificar los datos de los huespedes

    public int Modificar(Date FechaEntrada, Date FechaSalida, Double Valor, String FormaPago, Integer id ){

        if(EvaluarFechas(FechaEntrada,FechaSalida)){
            System.out.println("Fechas siguen igual");
        }else{
            java.util.Date FechaN =FechaEntrada;
            java.util.Date FechaS=FechaSalida;
            Double Modificacionvalor =hotelAluraGeneralController.TotalPagar(FechaN,FechaS);
            Valor=Modificacionvalor;
        }

        try{
            final PreparedStatement ModificarQuery = con.prepareStatement(
                    "UPDATE RESERVAS SET"
                        +" FechaEntrada = ?,"
                        +" FechaSalida = ?,"
                        +" Valor = ?, "
                        +"FormaPago = ? "
                        +"WHERE ID= ?");
        try(ModificarQuery){
            ModificarQuery.setDate(1, FechaEntrada);
            ModificarQuery.setDate(2, FechaSalida);
            ModificarQuery.setDouble(3,Valor);
            ModificarQuery.setString(4,FormaPago);
            ModificarQuery.setInt(5,id);
            ModificarQuery.execute();

            int RegistroActualizado = ModificarQuery.getUpdateCount();
            System.out.println("sonsulta realizada con exito");
            return RegistroActualizado;

        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    //Metodo para evaluar el cambio de fechas de ingreso y salida del cliente
    public boolean EvaluarFechas(java.sql.Date FechaEntrada, java.sql.Date FechaSalida){
        try{
            final PreparedStatement VerificarFechas = con.prepareStatement(
                    "SELECT FechaEntrada, FechaSalida FROM RESERVAS WHERE FechaEntrada = ? AND FechaSalida = ? ");

            try(VerificarFechas){
                VerificarFechas.setDate(1,FechaEntrada);
                VerificarFechas.setDate(2,FechaSalida);

                VerificarFechas.execute();
                ResultSet ValidarFecha= VerificarFechas.executeQuery();

                if(ValidarFecha.next()){
                    System.out.println("Fecha Igueles");
                    return true;
                }else{
                    System.out.println("Las Fechas no son iguales");
                    return false;

                }

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        //parte 2 del codigo
    }


    public Integer NumeroReferencia(){
        Integer idr=0;
        try{
            final PreparedStatement statement = con.prepareStatement(
                    "SELECT ID FROM reservas ORDER BY Id DESC LIMIT 1");
            try(statement){
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    while(resultSet.next()){
                        idr=resultSet.getInt("ID");
                    }
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return idr;
    }

    public List<Reservas> listar(){
        List<Reservas> resultado = new ArrayList<>();

        try{
            final PreparedStatement listaQuery = con.prepareStatement(
                    "SELECT Id, FechaEntrada, FechaSalida, Valor, FormaPago FROM reservas");
        try(listaQuery){
            listaQuery.execute();

            final ResultSet resultSet = listaQuery.getResultSet();
                try(resultSet){
                    while(resultSet.next()){
                        resultado.add(new Reservas(
                             resultSet.getInt("Id"),
                             resultSet.getDate("FechaEntrada"),
                             resultSet.getDate("FechaSalida"),
                             resultSet.getDouble("Valor"),
                             resultSet.getString("FormaPago")));

                    }
                }
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    //Metodo para eliminar un registro de la base de datos
    public int eliminar(Integer id){
        try{
            final PreparedStatement eliminarQuery = con.prepareStatement(
                    "DELETE FROM RESERVAS WHERE ID = ?");
        try(eliminarQuery){

            eliminarQuery.setInt(1,id); //envio del paramtro para el WHERE
            eliminarQuery.execute();

            int RegistroEliminado = eliminarQuery.getUpdateCount();

            return RegistroEliminado;
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservas> BuscarReservas(Integer Id){
        List<Reservas> Resultado = new ArrayList<>();
        Boolean Consulta = false;

        try{
            final PreparedStatement BusquedaReserva = con.prepareStatement(
                    "SELECT * FROM RESERVAS WHERE Id = ?");

        try(BusquedaReserva){
            BusquedaReserva.setInt(1,Id);
            BusquedaReserva.execute();

            final ResultSet ResultadoBusqueda = BusquedaReserva.getResultSet();

               try(ResultadoBusqueda) {
                   while(ResultadoBusqueda.next()){
                       Resultado.add(new Reservas(
                               ResultadoBusqueda.getInt("Id"),
                               ResultadoBusqueda.getDate("FechaEntrada"),
                               ResultadoBusqueda.getDate("FechaSalida"),
                               ResultadoBusqueda.getDouble("Valor"),
                               ResultadoBusqueda.getString("FormaPago")
                       ));
                       Consulta = true;
                   }
                    if(Consulta){

                    }else{
                        JOptionPane.showMessageDialog(null, "No se Encontro ningun resultado","Hotel Alura Informacion",JOptionPane.INFORMATION_MESSAGE);
                    }

               }catch (SQLException e) {
                   throw new RuntimeException(e);
               }
        }
        }catch (SQLException e){
            throw new RuntimeException(e);


        }

        return Resultado;
    }


}//Este es el final de la clase
