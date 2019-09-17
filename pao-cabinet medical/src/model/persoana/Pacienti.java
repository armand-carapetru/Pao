package model.persoana;

import JDBC.ConnectionUtils;
import JDBC.Database;
import org.jetbrains.annotations.NotNull;
import model.servicii.Consultatie;
import model.servicii.Reteta;
import service.Servicii;
import model.servicii.boli.Boala;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;

public class Pacienti extends Persoana implements Comparable < Pacienti>  {
    private List<Servicii> S = new ArrayList<>(Arrays.asList());
    public static int numarFise=0;
    public Pacienti(String nume, String CNP, Integer varsta) {
        super(nume, CNP, varsta);
    }
    public Pacienti()
    {
        super();
        S=null;
    }

    @Override
    public String toString() {
        return "Pacienti{" + super.toString()+
                "S=" + S +
                '}';
    }

    public List<Servicii> getS() {
        return S;
    }

    public void setS(List <Servicii> s) {
        S=new ArrayList<>(Arrays.asList());
        for(Servicii i :s)
            S.add(i);
        sort(S);
    }



    @Override
    public int compareTo(@NotNull Pacienti o) {
        return this.getNume().toLowerCase().compareTo(o.getNume().toLowerCase());
    }

}
