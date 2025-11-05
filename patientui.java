package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import client.PatientClient;
import model.Patient;
import java.util.List;

public class PatientUI extends JFrame {
    JTextField nameField, ageField, genderField, diseaseField, treatmentField;
    JTextArea displayArea;

    public PatientUI() {
        setTitle("🏥 Patient Medical History System");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Enter Patient Details"));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        formPanel.add(genderField);

        formPanel.add(new JLabel("Disease:"));
        diseaseField = new JTextField();
        formPanel.add(diseaseField);

        formPanel.add(new JLabel("Treatment:"));
        treatmentField = new JTextField();
        formPanel.add(treatmentField);

        JButton addBtn = new JButton("Add Patient");
        JButton viewBtn = new JButton("View Patients");
        formPanel.add(addBtn);
        formPanel.add(viewBtn);

        add(formPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        addBtn.addActionListener(e -> {
            Patient p = new Patient(
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    genderField.getText(),
                    diseaseField.getText(),
                    treatmentField.getText()
            );
            String msg = PatientClient.addPatient(p);
            JOptionPane.showMessageDialog(this, msg);
        });

        viewBtn.addActionListener(e -> {
            List<Patient> list = PatientClient.viewPatients();
            displayArea.setText("ID | Name | Age | Gender | Disease | Treatment\n");
            displayArea.append("----------------------------------------------\n");
            for (Patient p : list) {
                displayArea.append(p.toString() + "\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new PatientUI();
    }
}
