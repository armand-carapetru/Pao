package model.servicii;

import service.Servicii;

import java.io.Serializable;
import java.util.Arrays;
import java.sql.Date;

public class Reteta extends  Servicii   {
    public String[] getMedicamente() {
        return medicamente;
    }

    public void setMedicamente(String[] medicamente) {
        this.medicamente=new String[medicamente.length];
        for(int i=0; i<medicamente.length;i++)
            this.medicamente [i]= medicamente[i];
    }

    public Reteta(Date data, String[] medicamente) {
        super(data);
        this.medicamente=new String[medicamente.length];
        for(int i=0; i<medicamente.length;i++)
            this.medicamente [i]= medicamente[i];
    }
    public Reteta() {
        super();
        this.medicamente=null;
    }
    @Override
    public String toString() {
        return "Reteta{" + super.toString()+
                "medicamente=" + Arrays.toString(medicamente) +
                '}';
    }

    private String []medicamente;

    @Override
    public Servicii getServiciu() {
        return new Reteta(getData(),medicamente);
    }
}
