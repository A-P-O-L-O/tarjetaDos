package PQ.LOGICA;

import PQ.IGU.NoPersonalizada;
import PQ.IGU.Personalizada;
import PQ.IGU.Recarga;
import PQ.IGU.UserGestion;
import PQ.PERSISTENCIA.TarjetaPersonalizadaJpaController;
import java.util.List;

public class Index {

    public static void main(String[] args) {

//                Personalizada creacion = new Personalizada();
//                creacion.setVisible(true);
//                creacion.setLocationRelativeTo(null);
        //
        ////        NoPersonalizada creacion = new NoPersonalizada();
        ////        creacion.setVisible(true);
        ////        creacion.setLocationRelativeTo(null);
//
        TarjetaPersonalizadaJpaController tarjetaController = new TarjetaPersonalizadaJpaController();
//
//        List<Integer> numerosDeTarjeta = tarjetaController.getNumerosTarjetaList();
//
//        for (Integer numeroTarjeta : numerosDeTarjeta) {
//            System.out.println(numeroTarjeta);
//        }

//        List<TarjetaPersonalizada> todasLasTarjetas = tarjetaController.getTodasLasTarjetas();
//
//        for (TarjetaPersonalizada tarjeta : todasLasTarjetas) {
//            System.out.println("Número de tarjeta: " + tarjeta.getNombrePropietario());
//        }
//
//Recarga recarga = new Recarga();
//recarga.setVisible(true);
//recarga.setLocationRelativeTo(null);


        UserGestion recarga = new UserGestion();
        recarga.setVisible(true);
        recarga.setLocationRelativeTo(null);

    }

}
