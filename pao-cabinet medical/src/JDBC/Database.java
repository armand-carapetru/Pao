package JDBC;

import model.UserLog;
import model.persoana.Medic;
import model.persoana.Pacienti;
import model.servicii.FisaPacientului;
import model.servicii.boli.Alergie;
import service.UserLogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    //DELETE
    public static void deletePacientById(Connection connection, String id) {
        String sql = "DELETE FROM pacient WHERE CNP_pacient = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            connection.close();
            deleteAlegieByCNP(connection,id);
            deleteFisaPacientuluiByCNP(connection,id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteUserById(Connection connection, Integer id) {
        String sql = "DELETE FROM user_passwords WHERE id_user = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteAlegieByCNP(Connection connection, String CNP) {
        String sql = "DELETE FROM alergii WHERE CNP_pacient = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CNP);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteFisaPacientuluiByCNP(Connection connection, String CNP) {
        String sql = "DELETE FROM fisapacientului WHERE CNP_pacient = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CNP);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //INSERT

    public static void insertFisaPacientului(Connection connection,String CNP, FisaPacientului m) {
        try {
            int id = 0;
            String query = "select max(id_fisa) from fisapacientului";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            String insert = "insert into fisapacientului values (?,?,?,?,?)";
            stmt = connection.prepareStatement(insert);
            stmt.setDate(1, m.getData());
            stmt.setString(2, m.getGrupaSange());
            stmt.setString(3,m.getRh());
            stmt.setString(4,CNP);
            stmt.setInt(5,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertPacient(Connection connection, Pacienti m, String CNP_medic) {
        try {
            String insert = "insert into pacient values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(insert);
            stmt.setString(1, m.getCNP());
            stmt.setString(2, m.getNume());
            stmt.setInt(3,m.getVarsta());
            stmt.setString(4,CNP_medic);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertMedic(Connection connection, Medic m) {
        try {
            String insert = "insert into medic values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(insert);
            stmt.setString(1, m.getCNP());
            stmt.setString(2, m.getNume());
            stmt.setInt(3,m.getVarsta());
            stmt.setString(4,m.getSpecialitate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertAlergie(Connection connection, Alergie m, String CNP) {
        try {
            int id = 0;
            String query = "select max(id_alergie) from alergii";
            String insert = "insert into alergii values (?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            stmt = connection.prepareStatement(insert);
            stmt.setDate(1,  m.getData());
            stmt.setString(2, CNP);
            stmt.setString(3,m.getGradAlergie());
            stmt.setInt(4,id+1);
            stmt.setString(5,m.getTip());
            stmt.setString(6,m.getNume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertUser(Connection connection, UserLog m) {
        try {
            String insert = "insert into medic values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(insert);
            stmt.setInt(3, UserLogService.numberOfUsers);
            stmt.setString(1, m.getUsername());
            stmt.setString(2,m.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //UPDATE
    public static void updatePacientNameById(Connection conn ,String name, String id){
        String sql = "UPDATE pacienti SET nume = ? WHERE CNP_pacient = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMedicNameById(Connection conn ,String name, String id){
        String sql = "UPDATE medic SET nume = ? WHERE CNP_medic = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateGradAlergieById(Connection conn ,String CNP,String grad, Integer id){
        String sql = "UPDATE alergii SET grad_alergie = ? WHERE CNP_pacient = ? and id_alergie=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, grad);
            pstmt.setString(2, CNP);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateUserById(Connection conn , String user,int id){
        String sql = "UPDATE USERS SET username = ? WHERE id_user = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
