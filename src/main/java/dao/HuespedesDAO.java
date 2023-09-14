package dao;

import modelo.Huespedes;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDAO {

    private Connection con; // variable donde se va almacenar la conexion

    public HuespedesDAO(Connection con){
        this.con=con;
    }

    //Metodo para guardar los datos de los huespedes
    public void guardarHuespedes(Huespedes huespedes){
        try{
            PreparedStatement statement;
            statement = con.prepareStatement(
                    "INSERT INTO HUESPEDES"
                    +"(Nombre, Apellido, FechaNacimiento, Nacionalidad, Telefono, IdReserva )"
                    +"VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        try(statement){
            statement.setString(1,huespedes.getNombre());
            statement.setString(2,huespedes.getApellido());
            statement.setDate(3, (Date) huespedes.getFechaNacimiento());
            statement.setString(4,huespedes.getNacionalidad());
            statement.setInt(5,huespedes.getTelefono());
            statement.setInt(6,huespedes.getIdReserva());

            statement.execute(); //Ejecutamos la consulta

            final ResultSet resultSet = statement.getGeneratedKeys();

            try(resultSet){
                while (resultSet.next()){
                    huespedes.setId((resultSet.getInt(1)));
                    System.out.println(String.format("Fue insertado el producto: %s", huespedes));
                }
            }

        }
        }catch (SQLException e){
            JOptionPane.showInternalMessageDialog(null, "Error en la Reservacion", "Hotel Alura información", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);

        }



    }

    public int EliminarHuesped(Integer Id){
        try{
            final PreparedStatement EliminarQuery = con.prepareStatement(
                    "DELETE FROM HUESPEDES WHERE Id = ?");

        try(EliminarQuery){
            EliminarQuery.setInt(1,Id);
            EliminarQuery.execute();

            int DatoEliminado = EliminarQuery.getUpdateCount();
            return DatoEliminado;
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int ModificarHuespedes(String Nombre, String Apellido, Date FechaNacimiento, String Nacionalidad, Integer Telefono, Integer IdReserva, Integer Id){

        try{
            final PreparedStatement EditarHuespedes = con.prepareStatement(
                    "UPDATE HUESPEDES SET"
                        +" Nombre = ?,"
                        +" Apellido = ?,"
                        +" FechaNacimiento = ?,"
                        +" Nacionalidad = ?,"
                        +" Telefono = ?,"
                        +" IdReserva = ?"
                        +" WHERE Id = ?");

        try(EditarHuespedes){

            EditarHuespedes.setString(1,Nombre);
            EditarHuespedes.setString(2,Apellido);
            EditarHuespedes.setDate(3,FechaNacimiento);
            EditarHuespedes.setString(4,Nacionalidad);
            EditarHuespedes.setInt(5,Telefono);
            EditarHuespedes.setInt(6,IdReserva);
            EditarHuespedes.setInt(7,Id);

            EditarHuespedes.execute();
            int RegistroActualizado = EditarHuespedes.getUpdateCount();
            System.out.println("Consulta Huespedes realizada con exito");
            return RegistroActualizado;
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    //Metodo para carlar la lista de Huespedes
    public List<Huespedes> listar(){
        List<Huespedes> resultado = new ArrayList<>();

        try{
            final PreparedStatement listaQuery = con.prepareStatement(
                    "SELECT Id, Nombre, Apellido, FechaNacimiento,Nacionalidad ,Telefono,IdReserva FROM huespedes");

        try(listaQuery){
            listaQuery.execute();

            final ResultSet resultSet = listaQuery.getResultSet();

            try(resultSet){
                while(resultSet.next()){
                    resultado.add(new Huespedes(
                            resultSet.getInt("Id"),
                            resultSet.getString("Nombre"),
                            resultSet.getString("Apellido"),
                            resultSet.getDate("FechaNacimiento"),
                            resultSet.getString("Nacionalidad"),
                            resultSet.getInt("Telefono"),
                            resultSet.getInt("IdReserva")

                    ));
                }
            }
        }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public List<Huespedes> BuscadorHuesped(String ApellidoHuesped){
        List<Huespedes> listaBusqueda = new ArrayList<>();
        Boolean Consulta = false;
            try{
                final PreparedStatement BuscadorQuery = con.prepareStatement(
                        "SELECT * FROM Huespedes WHERE Apellido = ?");

            try(BuscadorQuery){
                BuscadorQuery.setString(1,ApellidoHuesped);
                BuscadorQuery.execute();

                final ResultSet ResultadosQuery = BuscadorQuery.getResultSet();
            try(ResultadosQuery){
                    while (ResultadosQuery.next()){
                        listaBusqueda.add(new Huespedes(
                                ResultadosQuery.getInt("id"),
                                ResultadosQuery.getString("Nombre"),
                                ResultadosQuery.getString("Apellido"),
                                ResultadosQuery.getDate("FechaNacimiento"),
                                ResultadosQuery.getString("Nacionalidad"),
                                ResultadosQuery.getInt("Telefono"),
                                ResultadosQuery.getInt("IdReserva")
                        ));
                            Consulta=true;
                    }
                if(Consulta){

                }else{
                    JOptionPane.showMessageDialog(null, "No se Encontro ningun resultado","Hotel Alura Informacion",JOptionPane.INFORMATION_MESSAGE);
                }

            }
            }

            }catch (SQLException e){
                //JOptionPane.showInternalMessageDialog(null, "No se encontro ningun Resultado", "Hotel Alura información", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            }

        return listaBusqueda;
    }



}
