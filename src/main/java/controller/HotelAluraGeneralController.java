package controller;

import javax.swing.*;
import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HotelAluraGeneralController {


    /**
     * El metodo Confirmacion se utiliza para finalizar el programa dependiendo la opcion que escoja el usuario
     * si por medio del JOptionPane el usuario utiliza la opcion YES_OPTION le programa continua con normalidad
     * Si utiliza la opcion NO.OPTION el programa finaliza por medio del system.exit(0)
     * Si utiliza la opcion CANCEL.OPTION el programa finaliza por medio del system.exit(0)
     * @param Popcion
     * @return
     */
    //Metodo para validar seleccion final
    public void ExitConfirmacion(int Popcion){
        if(Popcion== JOptionPane.YES_OPTION){

        }else if(Popcion==JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null, "Programa Finalizado","Programa",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public double TotalPagar(Date FechaEntrada, Date FechaSalida){
        double valorReserva;
        long diferenciaEnMilisegundos = FechaSalida.getTime() - FechaEntrada.getTime();
        long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);

        valorReserva = diferenciaEnDias * 500;
        return valorReserva;
    }



}
