package model.persoana;

import java.io.Serializable;

public abstract class Persoana   {
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", CNP='" + CNP + '\'' +
                ", varsta=" + varsta +
                '}';
    }

    public Persoana(String nume, String CNP, Integer varsta) {
        this.nume = nume;
        this.CNP = CNP;
        this.varsta = varsta;
    }
    public Persoana (){
        this.nume = null;
        this.CNP = null;
        this.varsta = null;
    }
    private String nume;
    private String CNP;
    private Integer varsta;

}
