package PQ.LOGICA;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.JOptionPane;

@Entity
public final class TarjetaPersonalizada extends TuLlave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id_personalizada;

    @Basic
    private String nombrePropietario;
    private Integer identificacion;
    private String telefono;
    private String sexo;
    private double saldo;
    private Integer numeroTarjeta;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Temporal(TemporalType.DATE)
    private Date ultimoUso;
    private boolean estado;
    private int pasajePrestado;

    public TarjetaPersonalizada() {
    }

    public TarjetaPersonalizada(int id_personalizada, String nombrePropietario, Integer identificacion, String telefono, String sexo, double saldo, Integer numeroTarjeta, Date fechaNacimiento, Date fechaCreacion, Date ultimoUso, boolean estado, Integer pasajePrestado) {
        this.id_personalizada = id_personalizada;
        this.nombrePropietario = nombrePropietario;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.sexo = sexo;
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.ultimoUso = ultimoUso;
        this.estado = estado;
        this.pasajePrestado = pasajePrestado;
    }

    public int getPasajePrestado() {
        return pasajePrestado;
    }

    public void setPasajePrestado(int pasajePrestado) {
        this.pasajePrestado = pasajePrestado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getId_personalizada() {
        return id_personalizada;
    }

    public void setId_personalizada(int id_personalizada) {
        this.id_personalizada = id_personalizada;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Integer identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Integer numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimoUso() {
        return ultimoUso;
    }

    public void setUltimoUso(Date ultimoUso) {
        this.ultimoUso = ultimoUso;
    }

    public boolean prestarPasaje() {
        return pasajePrestado < 2;
    }

    public double descontar(double valorPasaje) {

        double pago = saldo - valorPasaje;
        return pago;
    }

    public void recargar(TarjetaPersonalizada tarjeta, double recarga) {

        double valorRecarga = tarjeta.getSaldo() + recarga;
        tarjeta.setSaldo(valorRecarga);

    }

    public int calcularEdad(TarjetaPersonalizada tarjeta) {

        int calculoEdad = 0;

        //ESTO ES PARA LAS FECHAS
        Date fechaNacido = tarjeta.getFechaNacimiento();
        Calendar fechaNacimientoCalendar = Calendar.getInstance();
        fechaNacimientoCalendar.setTime(fechaNacido);

        // DEBO SABER LA FECHA ACTUAL
        Calendar fechaActual = Calendar.getInstance();

        // CALCULO LA EDAD DEL DUEÑO DE LA TARJETA
        calculoEdad = fechaActual.get(Calendar.YEAR) - fechaNacimientoCalendar.get(Calendar.YEAR);
        return calculoEdad;

    }

}
