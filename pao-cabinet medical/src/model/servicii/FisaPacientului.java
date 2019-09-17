package model.servicii;

import service.Servicii;

import java.io.Serializable;
import java.sql.Date;

public class FisaPacientului extends Servicii   {
    public String getGrupaSange() {
        return grupaSange;
    }

    public void setGrupaSange(String grupaSange) {
        this.grupaSange = grupaSange;
    }

    @Override
    public String toString() {
        return "Fisa Pacientului\n" +super.toString()+
                "grupaSange = " + grupaSange + '\n' +
                "rh = " + rh ;
    }

    public FisaPacientului(Date data, String grupaSange, String rh) {
        super(data);
        this.grupaSange = grupaSange;
        this.rh = rh;
    }

    public String getRh() {
        return rh;
    }

    public FisaPacientului() {
        super();
        this.grupaSange = null;
        this.rh = null;
    }
    private String grupaSange;
    private String rh;

    @Override
    public Servicii getServiciu() {
        return new FisaPacientului(getData(),grupaSange,rh);
    }
}
