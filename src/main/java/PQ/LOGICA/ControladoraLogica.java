package PQ.LOGICA;

import PQ.PERSISTENCIA.ControladoraPersistencia;
import PQ.PERSISTENCIA.TarjetaPersonalizadaJpaController;
import java.util.Date;
import java.util.List;

public class ControladoraLogica {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    public void guardar(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identificacion, Date fecha) {

        TarjetaPersonalizada tarjetaPersonalizada = new TarjetaPersonalizada();

        // SETEO VALORES A LA PERSONALIZADA
        tarjetaPersonalizada.setNombrePropietario(nombre);
        tarjetaPersonalizada.setIdentificacion(identificacion);
        tarjetaPersonalizada.setTelefono(telefono);
        tarjetaPersonalizada.setSexo(sexo);
        tarjetaPersonalizada.setFechaNacimiento(fechaNacimiento);
        tarjetaPersonalizada.setFechaCreacion(fecha);
        tarjetaPersonalizada.setSaldo(0);
        tarjetaPersonalizada.setNumeroTarjeta(identificacion);
        tarjetaPersonalizada.setUltimoUso(null);

        // GUARDO LOS OBJETOS EN LA PERSISTENCIA
        controlPersistencia.guardar(tarjetaPersonalizada);

    }

    public void guardarNoPersonalizada(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identificacion, Date fecha) {

        TarjetaNoPersonalizada tarjetaNoPersonalizada = new TarjetaNoPersonalizada();

        //SETEO DE LOS VALORES LA TARJETA NO PERSONALIZADA :)
        tarjetaNoPersonalizada.setNombrePropietario(nombre);
        tarjetaNoPersonalizada.setIdentificacion(identificacion);
        tarjetaNoPersonalizada.setTelefono(telefono);
        tarjetaNoPersonalizada.setSexo(sexo);
        tarjetaNoPersonalizada.setFechaNacimiento(fechaNacimiento);
        tarjetaNoPersonalizada.setFechaCreacion(fecha);
        tarjetaNoPersonalizada.setSaldo(0);
        tarjetaNoPersonalizada.setNumeroTarjeta(identificacion);
        tarjetaNoPersonalizada.setUltimoUso(null);

        controlPersistencia.guardarNoPersonalizada(tarjetaNoPersonalizada);

    }

    public void actualizar(TarjetaPersonalizada tarjeta) throws Exception {
        controlPersistencia.actualizar(tarjeta);
    }

}
