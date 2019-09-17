package model.servicii.boli;

import service.Servicii;

import java.io.Serializable;
import java.sql.Date;

public class Alergie extends Boala   {
    public String getGradAlergie() {
        return gradAlergie;
    }

    public void setGradAlergie(String gradAlergie) {
        this.gradAlergie = gradAlergie;
    }

    @Override
    public String toString() {
        return "Alergie{" +super.toString()+
                "gradAlergie='" + gradAlergie + '\'' +
                '}';
    }

    public Alergie(Date data, String tip, String nume, String gradAlergie) {
        super(data,tip, nume);
        this.gradAlergie = gradAlergie;
    }
    public Alergie() {
        super();
        this.gradAlergie = null;
    }
    private String gradAlergie;
    public Servicii getServiciu() {
        return new Alergie(getData(),getTip(), getNume(),gradAlergie);
    }

}
