package PQ.LOGICA;

import PQ.IGU.Validacion;
import PQ.PERSISTENCIA.ControladoraPersistencia;
import java.util.Date;

public class ControladoraLogica {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    public void guardar(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identiInteger, Date fecha) {

        TarjetaPersonalizada tarjetaPersonalizada = new TarjetaPersonalizada();

        //CREAMOS OBJETO Y ASIGNAMOS VALORES TARJETA PERSONALIZADA:)
        tarjetaPersonalizada.setNombrePropietario(nombre);
        tarjetaPersonalizada.setIdentificacion(identiInteger);
        tarjetaPersonalizada.setTelefono(telefono);
        tarjetaPersonalizada.setSexo(sexo);
        tarjetaPersonalizada.setFechaNacimiento(fechaNacimiento);
        tarjetaPersonalizada.setFechaCreacion(fecha);
        tarjetaPersonalizada.setSaldo(0);
        tarjetaPersonalizada.setNumeroTarjeta(tarjetaPersonalizada.getIdentificacion());
        tarjetaPersonalizada.setUltimoUso(null);
        
        controlPersistencia.guardar(tarjetaPersonalizada);

    }

    public void guardarNopersonalizada(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identiInteger, Date fecha) {
        
        TarjetaNoPersonalizada tarjetaNoPersonalizada = new TarjetaNoPersonalizada();
        
        //CREAMOS OBJETO Y ASIGNAMOS VALORES TARJETA PERSONALIZADA:)
        tarjetaNoPersonalizada.setNombrePropietario(nombre);
        tarjetaNoPersonalizada.setIdentificacion(identiInteger);
        tarjetaNoPersonalizada.setTelefono(telefono);
        tarjetaNoPersonalizada.setSexo(sexo);
        tarjetaNoPersonalizada.setFechaNacimiento(fechaNacimiento);
        tarjetaNoPersonalizada.setFechaCreacion(fecha);
        tarjetaNoPersonalizada.setSaldo(0);
        tarjetaNoPersonalizada.setNumeroTarjeta(tarjetaNoPersonalizada.getIdentificacion());
        tarjetaNoPersonalizada.setUltimoUso(null);
        
        controlPersistencia.guardar(tarjetaNoPersonalizada);
        
    }

    public void recargar(Integer documento, Integer recarga) {
        
        
        
        
        
    }
}
