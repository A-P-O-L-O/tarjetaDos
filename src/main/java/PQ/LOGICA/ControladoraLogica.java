package PQ.LOGICA;

import PQ.PERSISTENCIA.ControladoraPersistencia;
import PQ.PERSISTENCIA.TarjetaPersonalizadaJpaController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ControladoraLogica {

    ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    //<------------------------------------------->CREACION  PERSONALIZADA<----------------------------------------->
    public void guardar(String nombre, String telefono, Date fechaNacimiento, String sexo, Integer identiInteger) {

        TarjetaPersonalizadaJpaController listaJpa = new TarjetaPersonalizadaJpaController();
        List<TarjetaPersonalizada> listaPersonalizadas = new ArrayList<>();
        listaPersonalizadas = listaJpa.getTodasLasTarjetas();
        boolean tarjetaExistente = false;

        for (TarjetaPersonalizada tarjeta : listaPersonalizadas) {
            if (tarjeta.getIdentificacion().equals(identiInteger)) {
                tarjetaExistente = true;
                break; // SALGO DEL BUCLE TAN PRONTO ENCUENTRO QUE UNA TARJETA YA TIENE ESE NUMERO
            }
        }

        if (tarjetaExistente) {
            JOptionPane.showMessageDialog(null, "Numero de Identificacion ya Registrado",
                    "Error Al Crear",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            TarjetaPersonalizada tarjetaPersonalizada = new TarjetaPersonalizada();

            // ESTO ES PARA EL NUMERO DE LA TARJETA
            int numeroAleatorio = (int) (Math.random() * 999999999);

            tarjetaPersonalizada.setNombrePropietario(nombre.toUpperCase());
            tarjetaPersonalizada.setIdentificacion(identiInteger);
            tarjetaPersonalizada.setTelefono(telefono);
            tarjetaPersonalizada.setSexo(sexo);
            tarjetaPersonalizada.setFechaNacimiento(fechaNacimiento);
            tarjetaPersonalizada.setFechaCreacion(Calendar.getInstance().getTime());
            tarjetaPersonalizada.setSaldo(0);
            tarjetaPersonalizada.setNumeroTarjeta(numeroAleatorio);
            tarjetaPersonalizada.setUltimoUso(null);
            tarjetaPersonalizada.setEstado(true);
            tarjetaPersonalizada.setPasajePrestado(0);

            controlPersistencia.guardar(tarjetaPersonalizada);

            JOptionPane option = new JOptionPane("Se Ha Creado Correctamente la Tarjeta ");
            option.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = option.createDialog("Creacion Exitosa");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);

        }
    }

    //<------------------------------------>RECARGAR  PERSONALIZADA<--------------------------------------->
    public void recargaPersonanalizada(Integer numeroTarjeta, double recarga) {

        TarjetaPersonalizadaJpaController tarjetaControllerDos = new TarjetaPersonalizadaJpaController();
        List<TarjetaPersonalizada> todasLasTarjetas = tarjetaControllerDos.getTodasLasTarjetas();

        boolean encontrado = false;

        for (TarjetaPersonalizada tarjeta : todasLasTarjetas) {
            if (tarjeta.getIdentificacion().equals(numeroTarjeta)) {
                encontrado = true; // Se ha encontrado una coincidencia

                if (tarjeta.getEstado() != false) {
                    if ((tarjeta.getSaldo() + recarga) >= 2950) {

                        tarjeta.recargar(tarjeta, recarga);
                        tarjeta.setPasajePrestado(0);

                        try {
                            controlPersistencia.recargarPersonalizada(tarjeta);
                        } catch (Exception ex) {
                            System.out.println("NO SE PUDO LLEVAR A CABO ESTO :(");
                        }

                        JOptionPane.showMessageDialog(null, "Tarjeta recargada con Exito: " + String.valueOf(tarjeta.getSaldo()),
                                "Recarga",
                                JOptionPane.INFORMATION_MESSAGE);

                    } else if (tarjeta.getSaldo() < recarga) {

                        double recargaObligada = Math.abs(tarjeta.getSaldo()) + 2950;

                        JOptionPane.showMessageDialog(null, "Recarga la tarjeta con un valor \n igual o superior a: " + String.valueOf(recargaObligada),
                                "Recarga Error",
                                JOptionPane.WARNING_MESSAGE);

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

    //<---------------------------------->PAGAR TRONCAL PERSONALIZADA<---------------------------------->
    public void pagar(Integer numeroDocumento) throws Exception {

        TarjetaPersonalizadaJpaController controller = new TarjetaPersonalizadaJpaController();
        List<TarjetaPersonalizada> listaPersonalizadas = controller.getTodasLasTarjetas();
        boolean usuarioEncontrado = false;
        int edad = 0; // INICIALIZO LA VARIABLE CON UN VALOR PREDETERMINDO

        for (TarjetaPersonalizada tarjeta : listaPersonalizadas) {
            if (tarjeta.getIdentificacion().equals(numeroDocumento)) {

                if (tarjeta.getEstado() != false) {
                    usuarioEncontrado = true;

                    edad = tarjeta.calcularEdad(tarjeta);

                    if ((tarjeta.getSexo().equals("FEMENINO") && edad >= 57 || (tarjeta.getSexo().equals("MASCULINO") && edad >= 62))) {

                        if (tarjeta.getSaldo() >= 2950) {
                            tarjeta.setSaldo(tarjeta.descontar(2950 - (2950 * 0.2)));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            controlPersistencia.pagar(tarjeta);
                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else if (tarjeta.prestarPasaje()) {

                            tarjeta.setSaldo(tarjeta.descontar(2950 - (2950 * 0.2)));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            tarjeta.setPasajePrestado(tarjeta.getPasajePrestado() + 1);
                            controlPersistencia.pagar(tarjeta);

                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo Insuficiente o no te podemos prestar un pasaje\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "No Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else if ((tarjeta.getSexo().equals("FEMENINO") && edad < 57 || (tarjeta.getSexo().equals("MASCULINO") && edad < 62))) {

                        if (tarjeta.getSaldo() >= 2950) {
                            tarjeta.setSaldo(tarjeta.descontar(2950));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            controlPersistencia.pagar(tarjeta);
                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else if (tarjeta.prestarPasaje()) {

                            tarjeta.setSaldo(tarjeta.descontar(2950));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            tarjeta.setPasajePrestado(tarjeta.getPasajePrestado() + 1);
                            controlPersistencia.pagar(tarjeta);

                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo Insuficiente o no te podemos prestar un pasaje\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "No Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error Desconocido", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ESTE TARJETA ESTA BLOQUEADA",
                            "Recarga Error",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }

            }
        }
        if (!usuarioEncontrado) {
            JOptionPane.showMessageDialog(null, "Usuario No Encontrado", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    //<---------------------------------------------->PAGAR ZONAL PERSONNALIZADA<------------------------------------------------>
    public void zonal(Integer numeroDocumento) {

        TarjetaPersonalizadaJpaController controller = new TarjetaPersonalizadaJpaController();
        List<TarjetaPersonalizada> listaPersonalizadas = controller.getTodasLasTarjetas();
        boolean usuarioEncontrado = false;
        int edad = 0; // INICIALIZO LA VARIABLE CON UN VALOR PREDETERMINDO

        for (TarjetaPersonalizada tarjeta : listaPersonalizadas) {
            if (tarjeta.getIdentificacion().equals(numeroDocumento)) {

                usuarioEncontrado = true;
                if (tarjeta.getEstado() != false) {

                    //ESTO ES PARA LAS FECHAS
                    Date fechaNacimiento = tarjeta.getFechaNacimiento();
                    Calendar fechaNacimientoCalendar = Calendar.getInstance();
                    fechaNacimientoCalendar.setTime(fechaNacimiento);

                    // DEBO SABER LA FECHA ACTUAL
                    Calendar fechaActual = Calendar.getInstance();

                    // CALCULO LA EDAD DEL DUEÑO DE LA TARJETA
                    edad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);

                    if ((tarjeta.getSexo().equals("FEMENINO") && edad >= 57 || (tarjeta.getSexo().equals("MASCULINO") && edad >= 62))) {

                        if (tarjeta.getSaldo() >= 2750) {
                            tarjeta.setSaldo(tarjeta.descontar(2750 - (2750 * 0.2)));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            try {
                                controlPersistencia.pagar(tarjeta);

                            } catch (Exception ex) {
                                Logger.getLogger(ControladoraLogica.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else if (tarjeta.prestarPasaje()) {

                            tarjeta.setSaldo(tarjeta.descontar(2750 - (2750 * 0.2)));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            tarjeta.setPasajePrestado(tarjeta.getPasajePrestado() + 1);
                            try {
                                controlPersistencia.pagar(tarjeta);

                            } catch (Exception ex) {
                                Logger.getLogger(ControladoraLogica.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo Insuficiente o no te podemos prestar un pasaje\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "No Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } else if ((tarjeta.getSexo().equals("FEMENINO") && edad < 57 || (tarjeta.getSexo().equals("MASCULINO") && edad < 62))) {

                        if (tarjeta.getSaldo() >= 2750) {
                            tarjeta.setSaldo(tarjeta.descontar(2750));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            try {
                                controlPersistencia.pagar(tarjeta);

                            } catch (Exception ex) {
                                Logger.getLogger(ControladoraLogica.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);

                        } else if (tarjeta.prestarPasaje()) {

                            tarjeta.setSaldo(tarjeta.descontar(2750));
                            tarjeta.setUltimoUso(Calendar.getInstance().getTime());
                            tarjeta.setPasajePrestado(tarjeta.getPasajePrestado() + 1);
                            try {
                                controlPersistencia.pagar(tarjeta);

                            } catch (Exception ex) {
                                Logger.getLogger(ControladoraLogica.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            JOptionPane.showMessageDialog(null, "Bienvenido al sistema\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo Insuficiente o no te podemos prestar un pasaje\n saldo:  " + String.valueOf(tarjeta.getSaldo()), "No Ingreso", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error Desconocido", "ERROR", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "ESTA TARJETA ESTA BLOQUEADA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
        if (!usuarioEncontrado) {
            JOptionPane.showMessageDialog(null, "Usuario No Encontrado", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    //<---------------------------------------------->BLOQUEO DE TARJETAS PERSONALIZADAS<------------------------------------------------>
    public List<TarjetaPersonalizada> traerTarjetas() {
        return controlPersistencia.traerTarjetas();
    }

    public void bloquearTarjeta(int numeroTarjeta) {

        TarjetaPersonalizadaJpaController controller = new TarjetaPersonalizadaJpaController();
        TarjetaPersonalizada tarjeta = controller.findTarjetaPersonalizada(numeroTarjeta);
        tarjeta.setEstado(false);

        try {
            controlPersistencia.bloquearOActivar(tarjeta);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraLogica.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActivarTarjeta(int numeroTarjeta) {

        TarjetaPersonalizadaJpaController controller = new TarjetaPersonalizadaJpaController();
        TarjetaPersonalizada tarjeta = controller.findTarjetaPersonalizada(numeroTarjeta);
        tarjeta.setEstado(true);

        try {
            controlPersistencia.bloquearOActivar(tarjeta);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraLogica.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
