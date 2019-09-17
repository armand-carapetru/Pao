package model;

import JDBC.ConnectionUtils;
import JDBC.Database;
import model.persoana.Medic;
import service.MedicService;
import service.UserLogService;

public class Fir extends Thread{

    private String actiune;
    private String CNP;
    private Medic m;

    public Fir(String act){
        actiune = act;
    }

    public Fir(String act, String CNP){
        actiune = act;
        this.CNP = CNP;
    }

    public Fir(String act, Medic m){
        actiune = act;
        this.m = new Medic(m.getNume(),m.getCNP(),m.getVarsta(),m.getSpecialitate());
    }

    public void run(){

        if(actiune.equals("incarca date")) {
            MedicService.incarcaData();
            UserLogService.incarcaUsers();
        }
        if(actiune.equals("sterge pacient")){
            Database.deletePacientById(ConnectionUtils.getDBConnection(),CNP );
        }
        if(actiune.equals("add medic"))
        {
            Database.insertMedic(ConnectionUtils.getDBConnection(),m);
        }

    }

}
