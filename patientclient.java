package client;

import java.io.*;
import java.net.*;
import java.util.List;
import model.Patient;

public class PatientClient {
    public static String addPatient(Patient p) {
        try (Socket socket = new Socket("localhost", 5000);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("ADD");
            out.writeObject(p);
            return (String) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error adding patient!";
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Patient> viewPatients() {
        try (Socket socket = new Socket("localhost", 1111);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("VIEW");
            return (List<Patient>) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
