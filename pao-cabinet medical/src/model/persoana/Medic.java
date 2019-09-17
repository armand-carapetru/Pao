package model.persoana;

import JDBC.ConnectionUtils;
import JDBC.Database;
import model.Fir;
import org.jetbrains.annotations.NotNull;
import service.AuditService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;

public class Medic extends Persoana implements Comparable <Medic> {
    @Override
    public String toString() {
        return "Medic{" +super.toString()+
                "P=" + P +
                ", Specialitate='" + Specialitate + '\'' +
                '}';
    }

    public List<Pacienti> getP() {
        return P;
    }

    public void setP(List <Pacienti> p) {
        P=new ArrayList<>(Arrays.asList());
        for(Pacienti i :p)
            P.add(i);
        sort(P);
    }

    public String getSpecialitate() {
        return Specialitate;
    }

    public void setSpecialitate(String specialitate) {
        Specialitate = specialitate;
    }

    public Medic(String nume, String CNP, Integer varsta, String specialitate) {
        super(nume, CNP, varsta);
        P=new ArrayList<>(Arrays.asList());
        Specialitate = specialitate;
    }
    public Medic(){
        super();
        P=null;
        Specialitate =null;
    }
    public int compareTo(@NotNull Medic o) {
        return this.getNume().toLowerCase().compareTo(o.getNume().toLowerCase());
    }
    public Pacienti getPacient(String CNP){
        AuditService audit=new AuditService();
        audit.print("getPacient, " + "medic: " + getCNP() + ", pacient: " + CNP);
        for(Pacienti p : P){
            if(p.getCNP().equals(CNP))
                return p;
        }
        return null;
    }

    public void addPacient(Pacienti p){
        AuditService audit=new AuditService();
        audit.print("addPacient, " + "medic: " + getCNP() + ", pacient: " + p.getCNP());
        P.add(p);
        Database.insertPacient(ConnectionUtils.getDBConnection(),p,getCNP());
        sort(P);
    }

    public int deletePacient(String CNP) throws InterruptedException {
        AuditService audit=new AuditService();
        audit.print("deletePacient, " + "medic: " + getCNP() + ", pacient: " + CNP);
        int p=-1, j=0;
        for(Pacienti i:P){
            if(i.getCNP().equals(CNP))
                p=j;
            j++;
        }
        if(p==-1)
            return 0;//nu s-a efectuat stergerea
        P.remove(p);
        Fir f = new Fir("Sterge Pacient", CNP);
        f.start();
        f.join();
        return 1;
    }
    private List<Pacienti> P;
    private String Specialitate;



}
