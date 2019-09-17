package service;

import java.io.*;
import java.sql.Timestamp;

public class AuditService {
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public void print(String text) {

        try (PrintWriter writer = new PrintWriter(new FileWriter("audit.csv", true))) {

            StringBuilder sb=new StringBuilder();
            sb.append(timestamp.toString() + " ");
            sb.append(text);
            sb.append("\n");
            writer.write(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
