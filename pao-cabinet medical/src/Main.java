import GraphicInterface.Login;
import model.Fir;
import service.MedicService;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Login l =new Login();
        l.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        l.setVisible(true);
        Fir f = new Fir("incarca date");
        f.start();
        f.join();

    }
}






