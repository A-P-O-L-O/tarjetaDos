package PQ.LOGICA;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.OneToOne;

public class TuLlave extends TarjetaDigital implements Serializable {

    public double saldo;
    public Integer numeroTarjeta;
    public Date fechaCreacion;
    public Date ultimoUso;

    @OneToOne
    private TarjetaPersonalizada tarjetaPersonalizada;

    public TuLlave() {
    }

    public TuLlave(double saldo, Integer numeroTarjeta, Date fechaCreacion, Date ultimoUso) {
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCreacion = fechaCreacion;
        this.ultimoUso = ultimoUso;
    }

    public TuLlave(int id_tullave, double saldo, Integer numeroTarjeta, Date fechaCreacion, Date ultimoUso, TarjetaPersonalizada tarjetaPersonalizada) {
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCreacion = fechaCreacion;
        this.ultimoUso = ultimoUso;
        this.tarjetaPersonalizada = tarjetaPersonalizada;
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

    public TarjetaPersonalizada getTarjetaPersonalizada() {
        return tarjetaPersonalizada;
    }

    public void setTarjetaPersonalizada(TarjetaPersonalizada tarjetaPersonalizada) {
        this.tarjetaPersonalizada = tarjetaPersonalizada;
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
