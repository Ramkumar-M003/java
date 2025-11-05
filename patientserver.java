package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import database.DBConnection;
import model.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1111);
            System.out.println("🟢 Patient Server Started... Waiting for client...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("✅ Client connected");
                new Thread(() -> handleClient(socket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            String command = (String) in.readObject();

            if (command.equals("ADD")) {
                Patient p = (Patient) in.readObject();
                addPatient(p);
                out.writeObject("Patient Added Successfully ✅");
            } else if (command.equals("VIEW")) {
                List<Patient> list = viewPatients();
                out.writeObject(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addPatient(Patient p) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO patients(name, age, gender, disease, treatment) VALUES(?,?,?,?,?)"
        );
        ps.setString(1, p.getName());
        ps.setInt(2, p.getAge());
        ps.setString(3, p.getGender());
        ps.setString(4, p.getDisease());
        ps.setString(5, p.getTreatment());
        ps.executeUpdate();
    }

    private static List<Patient> viewPatients() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM patients");
        List<Patient> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Patient(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("disease"),
                rs.getString("treatment")
            ));
        }
        return list;
    }
}
