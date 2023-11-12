package PQ.LOGICA;

import PQ.PERSISTENCIA.ControladoraPersistencia;
import PQ.PERSISTENCIA.TarjetaNoPersonalizadaJpaController;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class controladoraLogicaNoPersonalizadas {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    //<---------------------------------------------->CREACION NO PERSONALIZADAS<------------------------------------------------>
    public void guardarNoPersonalizada(int numeroAleatorio, Date fecha) {

        TarjetaNoPersonalizada nopersonalizada = new TarjetaNoPersonalizada();

        nopersonalizada.setFechaCreacion(fecha);
        nopersonalizada.setNumeroTarjeta(numeroAleatorio);
        nopersonalizada.setSaldo(0);
        nopersonalizada.setUltimoUso(null);
        nopersonalizada.setEstado(true);

        controlPersistencia.guardarNoPersonalizada(nopersonalizada);

    }

    //<---------------------------------------------->RECARGA NO PERSONALIZADAS<------------------------------------------------>
    public void recargaNoPersonanalizada(Integer numeroTarjeta, double recarga) {

        TarjetaNoPersonalizadaJpaController tarjetaController = new TarjetaNoPersonalizadaJpaController();
        List<TarjetaNoPersonalizada> todasLasTarjetas = tarjetaController.getTodasLasTarjetas();

        boolean encontrado = false;

        for (TarjetaNoPersonalizada tarjeta : todasLasTarjetas) {
            if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) {
                encontrado = true; // AQUI SE ENCUENTRA LA TARJETA 

                if (tarjeta.getEstado() != false) {

                    if ((tarjeta.getSaldo() + recarga) >= 2950) {

                        tarjeta.recargar(tarjeta, recarga);

                        try {
                            controlPersistencia.recargarNoPersonalizada(tarjeta);
                        } catch (Exception ex) {
                            System.out.println("NO SE PUDO LLEVAR A CABO ESTO :(");
                        }
                        String saldoFinal = String.valueOf(tarjeta.getSaldo());
                        JOptionPane.showMessageDialog(null, "Tarjeta recargada con Exito: " + saldoFinal,
                                "Recarga",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        double recaraobligada = 2950 - tarjeta.getSaldo();

                        JOptionPane.showMessageDialog(null, "Debes recargar un valor igual o superior a: " + String.valueOf(recaraobligada),
                                "Recarga",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ESTE TARJETA ESTA BLOQUEADA",
                            "Recarga Error",
                            JOptionPane.WARNING_MESSAGE);
                    break;

                }

            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El número de tarjeta no se encuentra registrado",
                    "Error Al Recargar",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //<---------------------------------------------->PAGO TRONCAL NO PERSONALIZADAS<------------------------------------------------>
    public void pagarNoPersonalizada(Integer numeroTarjeta) throws Exception {

        TarjetaNoPersonalizadaJpaController controller = new TarjetaNoPersonalizadaJpaController();
        List<TarjetaNoPersonalizada> listaNoPersonalizadas = controller.getTodasLasTarjetas();

        boolean encontrado = false;

        for (TarjetaNoPersonalizada tarjeta : listaNoPersonalizadas) {
            if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) {
                encontrado = true;

                if (tarjeta.getEstado() != false) {

                    if (!tarjeta.puedeIngresar(2950)) {

                        JOptionPane.showMessageDialog(null, "Saldo Insuficiente",
                                "Error Al ingresar",
                                JOptionPane.WARNING_MESSAGE);

                    } else {

                        tarjeta.setSaldo(tarjeta.descontar(2950));
                        tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                        String saldoPostPago = String.valueOf(tarjeta.getSaldo());
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + saldoPostPago, "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        controlPersistencia.pagarNoPersonalizada(tarjeta);
                        break;

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ESTE TARJETA ESTA BLOQUEADA",
                            "Recarga Error",
                            JOptionPane.WARNING_MESSAGE);
                    break;

                }
            }
        }

        // Verifica si el numero de tarjeta es existente
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                    "Error Al ingresar",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //<---------------------------------------------->PAGO ZONAL NO PERSONALIZADAS<------------------------------------------------>

    public void zonalNoPersonalizada(Integer numeroTarjeta) {

        TarjetaNoPersonalizadaJpaController controller = new TarjetaNoPersonalizadaJpaController();
        List<TarjetaNoPersonalizada> listaNoPersonalizadas = controller.getTodasLasTarjetas();

        boolean encontrado = false;

        for (TarjetaNoPersonalizada tarjeta : listaNoPersonalizadas) {
            if (tarjeta.getNumeroTarjeta().equals(numeroTarjeta)) {
                encontrado = true;

                if (tarjeta.getEstado() != false) {

                    if (!tarjeta.puedeIngresar(2750)) {

                        JOptionPane.showMessageDialog(null, "Saldo Insuficiente",
                                "Error Al ingresar",
                                JOptionPane.WARNING_MESSAGE);

                    } else {

                        tarjeta.setSaldo(tarjeta.descontar(2750));
                        tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                        String saldoPostPago = String.valueOf(tarjeta.getSaldo());
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + saldoPostPago, "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            controlPersistencia.pagarNoPersonalizada(tarjeta);
                        } catch (Exception ex) {
                            Logger.getLogger(ControladoraLogica.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    }
                } else {

                    JOptionPane.showMessageDialog(null, "ESTA TARJETA ESTA BLOQUEADA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }

            }
        }

        // Verifica si el numero de tarjeta es existente
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado",
                    "Error Al ingresar",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    //<---------------------------------------------->BLOQUEO DE TARJETAS NO PERSONALIZADAS<------------------------------------------------>

    public List<TarjetaNoPersonalizada> traerTarjetasNoPersonalizadas() {
        return controlPersistencia.traerNoPersonalizadas();
    }
    
    public void bloquearTarjetaNoPersonalizada(int numeroTarjeta) {

        TarjetaNoPersonalizadaJpaController controller = new TarjetaNoPersonalizadaJpaController();
        TarjetaNoPersonalizada tarjeta = controller.findTarjetaNoPersonalizada(numeroTarjeta);
        tarjeta.setEstado(false);

        try {
            controlPersistencia.bloquearOActivarNoPersonalizada(tarjeta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraLogica.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ActivarTarjetaNoPersonalizada(int numeroTarjeta) {

        TarjetaNoPersonalizadaJpaController controller = new TarjetaNoPersonalizadaJpaController();
        TarjetaNoPersonalizada tarjeta = controller.findTarjetaNoPersonalizada(numeroTarjeta);
        tarjeta.setEstado(true);

        try {
            controlPersistencia.bloquearOActivarNoPersonalizada(tarjeta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
