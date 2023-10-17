package PQ.LOGICA;

import PQ.PERSISTENCIA.ControladoraPersistencia;
import java.util.Date;

public class ControladoraLogica {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    public void guardar(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identificacion, Date fecha) {
        //VUELVO A VALIDAR QUE NO VENGAN VACIOS
        if (nombre == null || nombre.isEmpty() || fechaNacimiento == null || sexo == null || identificacion == null || fecha == null) {

            throw new IllegalArgumentException("Datos de entrada inválidos.");
        }

        TarjetaPersonalizada tarjetaPersonalizada = new TarjetaPersonalizada();
        TarjetaNoPersonalizada tarjetaNoPersonalizada = new TarjetaNoPersonalizada();

        // Configurar los valores de TarjetaPersonalizada
        tarjetaPersonalizada.setNombrePropietario(nombre);
        tarjetaPersonalizada.setIdentificacion(identificacion);
        tarjetaPersonalizada.setTelefono(telefono);
        tarjetaPersonalizada.setSexo(sexo);
        tarjetaPersonalizada.setFechaNacimiento(fechaNacimiento);
        tarjetaPersonalizada.setFechaCreacion(fecha);
        tarjetaPersonalizada.setSaldo(0);
        tarjetaPersonalizada.setNumeroTarjeta(identificacion);
        tarjetaPersonalizada.setUltimoUso(null);

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

        // GUARDO LOS OBJETOS EN LA PERSISTENCIA
        controlPersistencia.guardar(tarjetaPersonalizada);
        controlPersistencia.guardar(tarjetaNoPersonalizada);

    }

}
