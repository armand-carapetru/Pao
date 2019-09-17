package model.servicii;

import service.Servicii;

import java.io.Serializable;
import java.util.Arrays;
import java.sql.Date;

public class Consultatie extends Servicii   {
    public String[] getManifestari() {
        return manifestari;
    }

    public void setManifestari(String[] manifestari) {
        for(int i=0;i<manifestari.length;i++)
            this.manifestari[i] = manifestari[i];
    }

    public Consultatie(Date data,String[] manifestari) {
        super(data);
        this.manifestari=new String[manifestari.length];
        for(int i=0;i<manifestari.length;i++)
            this.manifestari[i] = manifestari[i];
    }
    public Consultatie() {
        super();
        manifestari=null;
    }
    private String []manifestari;

    @Override
    public String toString() {
        return "Consultatie{" +super.toString()+
                "manifestari=" + Arrays.toString(manifestari) +
                '}';
    }

    @Override
    public Servicii getServiciu() {
        return new Consultatie(getData(),manifestari);
    }
}
