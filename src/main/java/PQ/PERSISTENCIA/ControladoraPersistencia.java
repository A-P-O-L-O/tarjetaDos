package PQ.PERSISTENCIA;

import PQ.LOGICA.TarjetaNoPersonalizada;
import PQ.LOGICA.TarjetaPersonalizada;
import PQ.LOGICA.TuLlave;


public class ControladoraPersistencia {
    
    TarjetaPersonalizadaJpaController personalizada = new TarjetaPersonalizadaJpaController();
    TarjetaNoPersonalizadaJpaController noPersonalizada = new TarjetaNoPersonalizadaJpaController();
            

    public void guardar(TarjetaPersonalizada tarjetaPersonalizada) {
        
        //CREAR EN LA BASE DE DATOS LA TARJETAPERSONALIZADA
        personalizada.create(tarjetaPersonalizada);
    }
    
    public void guardar(TarjetaNoPersonalizada tarjetaNoPersonalizada){
        
        noPersonalizada.create(tarjetaNoPersonalizada);
    
    }

    
}
