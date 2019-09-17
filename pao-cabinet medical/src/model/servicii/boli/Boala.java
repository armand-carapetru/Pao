package model.servicii.boli;

import service.Servicii;

import java.io.Serializable;
import java.sql.Date;

public class Boala extends Servicii   {
    public Boala(Date data,String tip, String nume) {
        super(data);
        this.tip = tip;
        this.nume = nume;
    }
    public Boala() {
        super();
        this.tip = null;
        this.nume = null;
    }

    public String getTip() {
        return tip;
    }

    @Override
    public String toString() {
        return "Boala{" +super.toString()+
                "tip='" + tip + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    private String tip;
    private String nume;

    @Override
    public Servicii getServiciu() {
        return new Boala(getData(),tip, nume);
    }
}
