package PQ.PERSISTENCIA;

import PQ.LOGICA.TarjetaNoPersonalizada;
import PQ.LOGICA.TarjetaPersonalizada;

public class ControladoraPersistencia {

    TarjetaPersonalizadaJpaController personalizada = new TarjetaPersonalizadaJpaController();
    TarjetaNoPersonalizadaJpaController noPersonalizada = new TarjetaNoPersonalizadaJpaController();

    public void guardar(TarjetaPersonalizada tarjetaPersonalizada) {
        //CREAR EN LA BASE DE DATOS LA TARJETAPERSONALIZADA
        personalizada.create(tarjetaPersonalizada);
    }

    public void guardarNoPersonalizada(TarjetaNoPersonalizada tarjetaNoPersonalizada) {
        //CREO EN LA BASE DE DATOS LA TARJETA NO PERSONALIZADA 
        noPersonalizada.create(tarjetaNoPersonalizada);

    }

    public void actualizar(TarjetaPersonalizada tarjeta) throws Exception {
        
        personalizada.edit(tarjeta);
    }

    public void pagar(TarjetaPersonalizada tarjeta) throws Exception {
        
        personalizada.edit(tarjeta);
    }

}
