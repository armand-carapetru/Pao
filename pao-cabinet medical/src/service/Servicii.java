package service;

import java.sql.Date;

public abstract class Servicii implements Comparable <Servicii> {
    public abstract Servicii getServiciu();
    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "data = " + data + "\n";
    }

    public Servicii(Date data) {
        this.data = data;
    }
    public Servicii() {
        this.data = null;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int compareTo(Servicii o){
        return data.compareTo(o.data);
    }
    private Date data;
}
