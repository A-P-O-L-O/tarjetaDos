package PQ.LOGICA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public final class TarjetaNoPersonalizada extends TuLlave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_nopersonalizada;

    @Column
    private double saldo;
    @Column
    private Integer numeroTarjeta;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Temporal(TemporalType.DATE)
    private Date ultimoUso;
    private boolean estado;

    public TarjetaNoPersonalizada() {
    }

    public TarjetaNoPersonalizada(int id_nopersonalizada, double saldo, Integer numeroTarjeta, Date fechaCreacion, Date ultimoUso, boolean estado) {
        this.id_nopersonalizada = id_nopersonalizada;
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCreacion = fechaCreacion;
        this.ultimoUso = ultimoUso;
        this.estado = estado;
    }

    public int getId_nopersonalizada() {
        return id_nopersonalizada;
    }

    public void setId_nopersonalizada(int id_nopersonalizada) {
        this.id_nopersonalizada = id_nopersonalizada;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double descontar(double descontar) {

        double pagar = saldo - descontar;
        return pagar;

    }

    public void recargar(TarjetaNoPersonalizada tarjetaNo, double recarga) {

        double valorRecarga = tarjetaNo.getSaldo() + recarga;
        tarjetaNo.setSaldo(valorRecarga);

    }

    public boolean puedeIngresar(double valorPasaje) {
        return saldo >= valorPasaje;
    }

}
