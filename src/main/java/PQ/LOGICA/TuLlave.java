package PQ.LOGICA;

import java.io.Serializable;
import java.util.Date;

public class TuLlave extends TarjetaDigital implements Serializable {

    private double saldo;
    private Integer numeroTarjeta;
    private Date fechaCreacion;
    private Date ultimoUso;

    public TuLlave() {
    }

    public TuLlave(double saldo, Integer numeroTarjeta, Date fechaCreacion, Date ultimoUso) {
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCreacion = fechaCreacion;
        this.ultimoUso = ultimoUso;
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

    @Override
    public void recargar() {
    }

    @Override
    public double descontar() {
        return 0;
    }

    @Override
    public boolean ingresar() {
        return false;
    }

}
