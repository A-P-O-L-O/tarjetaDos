package PQ.PERSISTENCIA;

import PQ.LOGICA.TarjetaNoPersonalizada;
import PQ.LOGICA.TarjetaPersonalizada;
import java.util.List;

public class ControladoraPersistencia {

    TarjetaPersonalizadaJpaController personalizadaJPA = new TarjetaPersonalizadaJpaController();
    TarjetaNoPersonalizadaJpaController noPersonalizadaJPA = new TarjetaNoPersonalizadaJpaController();

    // <------------->METODO PARA CREAR LAS TARJETAS<------------->
    public void guardar(TarjetaPersonalizada tarjetaPersonalizada) {
        //CREAR EN LA BASE DE DATOS LA TARJETAPERSONALIZADA
        personalizadaJPA.create(tarjetaPersonalizada);
    }

    public void guardarNoPersonalizada(TarjetaNoPersonalizada nopersonalizada) {
        noPersonalizadaJPA.create(nopersonalizada);
    }

    // <------------->METODOS DE RECARGA<------------->
    public void recargarPersonalizada(TarjetaPersonalizada tarjeta) throws Exception {
        personalizadaJPA.edit(tarjeta);
    }

    public void recargarNoPersonalizada(TarjetaNoPersonalizada tarjeta) throws Exception {
        noPersonalizadaJPA.edit(tarjeta);
    }

    // <------------->METODOS DE PAGAR<------------->
    public void pagarNoPersonalizada(TarjetaNoPersonalizada tarjeta) throws Exception {
        noPersonalizadaJPA.edit(tarjeta);
    }

    public void pagar(TarjetaPersonalizada tarjeta) throws Exception {
        personalizadaJPA.edit(tarjeta);
    }

    //<--------------------------->Traer los datos a la tabla para las personalizadas <-------------------------------->
    public List<TarjetaPersonalizada> traerTarjetas() {
        return personalizadaJPA.findTarjetaPersonalizadaEntities();
    }

    public void bloquearOActivar(TarjetaPersonalizada tarjeta) throws Exception {
        personalizadaJPA.edit(tarjeta);
    }

    //<--------------------------->Traer los datos a la tabala para las no personalizadas <-------------------------------->
    public List<TarjetaNoPersonalizada> traerNoPersonalizadas() {
        return noPersonalizadaJPA.findTarjetaNoPersonalizadaEntities();
    }

    public void bloquearOActivarNoPersonalizada(TarjetaNoPersonalizada tarjeta) throws Exception {
        noPersonalizadaJPA.edit(tarjeta);
    }

}
