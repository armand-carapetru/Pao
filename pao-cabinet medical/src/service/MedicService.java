package service;

import JDBC.ConnectionUtils;
import JDBC.Database;
import model.Fir;
import model.persoana.Medic;
import model.persoana.Pacienti;
import model.servicii.FisaPacientului;
import model.servicii.boli.Alergie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;



public class MedicService {

    private static AuditService audit = new AuditService();
    private static List<Medic> lista = new ArrayList<>();
    @Override
    public String toString() {
        return "MedicService{" +
                "medic=" +lista+
                '}';
    }
    public Medic getOne(String CNP) {
        audit.print("getOne "+CNP);
        for (Medic i : lista) {
            if (i.getCNP().toLowerCase().equals(CNP.toLowerCase()))
                return i;
        }
        return null;
    }

    public Medic getByPacient(String CNP) {
        audit.print("getByPacient "+CNP);
        for (Medic i : lista) {
            List<Pacienti> P = i.getP();
            for (Pacienti j : P) {
                if (j.getCNP().equals(CNP))
                    return i;
            }
        }
        return null;
    }
    public static void addPacientByMedicCNP(String CNP_medic, Pacienti p){
        for (Medic i : lista){
            if(i.getCNP().equals(CNP_medic)){
                i.addPacient(p);
            }
       }
    }
    public void setMedic(Set<Medic> medic) {
        audit.print("setMedic");
        for (Medic i : medic){
            this.lista.add(i);
        }
    }

    public List<Medic> getMedici(){
        return lista;
    }

    public void addMedic(Medic m) throws InterruptedException {
        audit.print("addMedic" + m.toString());
        lista.add(m);
        Fir f = new Fir("add medic", m);
        f.start();
        f.join();
    }

    public Servicii getAlergii(String CNP) {
        audit.print("getAlergii, "+CNP);
        for (Medic m : lista)
            for (Pacienti p : m.getP())
                if (p.getCNP().toLowerCase().equals(CNP.toLowerCase()))
                    for (Servicii i : p.getS()) {
                        if (i.getClass().toString().equals("class model.servicii.boli.Alergie"))
                            return i;
                    }
        return null;
    }

    public Servicii getFisaPacientului(String CNP) {
        audit.print("getFisaPacientului, "+CNP);
        for (Medic m : lista)
            for (Pacienti p : m.getP())
                if (p.getCNP().toLowerCase().equals(CNP.toLowerCase()))
                    for (Servicii i : p.getS()) {
                        if (i.getClass().toString().equals("class model.servicii.FisaPacientului"))
                           return i;
                    }
        return null;
    }

    public static int addFisaPacientului(String CNP_Pacient, FisaPacientului R) {
        audit.print("addFisaPacientului, " +  ", pacient: "+CNP_Pacient+ ", "+R.toString());
        for (Medic m : lista)
                for (Pacienti p : m.getP())
                if (p.getCNP().equals(CNP_Pacient) && p.numarFise==0){
                    p.getS().add(R);
                    p.numarFise++;
                    Database.insertFisaPacientului(ConnectionUtils.getDBConnection(),CNP_Pacient,R);
                }
                else
                    return 0;
        return 1;
    }

    private static void addAlergie(String CNP_Pacient, Alergie R) {
        String CNP_medic = null;
        for (Medic m : lista)
                for (Pacienti p : m.getP())
                    if (p.getNume().equals(CNP_Pacient)){
                        CNP_medic = m.getCNP();
                    p.getS().add(R);
                    String text = m.getCNP() + "," + p.getCNP() + ",Alergie" + R.getData().toString() + "," + R.getTip() + "," + R.getNume()+ ","+R.getGradAlergie();
//                    print(text, "Servicii.csv");
                    }
        audit.print("addAlergie, " + "medic: " + CNP_medic + ", pacient: " + CNP_medic + ", " + R.toString());
    }

    public static void PublicAddAlergie(String CNP_Pacient, Alergie R) {
        addAlergie(CNP_Pacient, R);
        Database.insertAlergie(ConnectionUtils.getDBConnection(),R, CNP_Pacient);
    }

    private static MedicService ourInstance = new MedicService();

    public static MedicService getInstance() {
        return ourInstance;
    }

    public static void incarcaData() {
        Connection connection=new ConnectionUtils().getDBConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from medic");
            while (rs.next()) {
                   Medic m = new Medic (rs.getString("nume"),rs.getString("cnp_medic"),rs.getInt("varsta"), rs.getString("specialitate"));
                   lista.add(m);
            }
            rs = statement.executeQuery("select * from pacient");
            while (rs.next()) {
                Pacienti m = new Pacienti (rs.getString("nume"),rs.getString("cnp_pacient"),rs.getInt("varsta"));
                addPacientByMedicCNP(rs.getString("cnp_medic"), m);
            }
            rs = statement.executeQuery("select * from alergii");
            while (rs.next()) {
                Alergie m = new Alergie (rs.getDate("data_alergie"),rs.getString("tip"),rs.getString("nume"), rs.getString("grad_alergie"));
                addAlergie(rs.getString("cnp_pacient"), m);
            }
            rs = statement.executeQuery("select * from fisapacientului");
            while (rs.next()) {
                FisaPacientului m = new FisaPacientului (rs.getDate("data_fisa"),rs.getString("grupa_sange"),rs.getString("rh"));
                addFisaPacientului(rs.getString("cnp_pacient"), m);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}