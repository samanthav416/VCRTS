package vcrts;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VCFrame implements ActionListener {

    ClientFrame cF = new ClientFrame();
    OwnerFrame oF = new OwnerFrame();
    UserFrame uF = new UserFrame();
    JFrame controllerView = new JFrame();
    JButton accept = new JButton("Would you like to Accept?");
    JButton reject = new JButton("Would you like to Reject?");
    JLabel clientId;
    JLabel duration;
    JLabel deadline;
    JLabel ownerId;
    JLabel licensePlate;
    JLabel residency;
    JFrame jobRejected = new JFrame();
    JLabel sorry = new JLabel("     Sorry, this job has been rejected.");
    JFrame jobAccepted = new JFrame();
    JLabel congrats = new JLabel("     Congrats, this job has been accepted.");
    Queue<Integer> jobDuration = new LinkedList<>();
    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/vcrts?useTimezone=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "pw12345";
    boolean type = uF.getUserType();

    VCFrame() {
        cF.hide();
        oF.hide();
        uF.hide();
        controllerView.setLayout(new FlowLayout());
        //Client
        if (type) {
            clientId = new JLabel("Client Id: " + cF.getClientId());
            duration = new JLabel("Duration: " + cF.getDuration());
            deadline = new JLabel("Deadline: " + cF.getDeadline());
            controllerView.add(clientId);
            controllerView.add(duration);
            controllerView.add(deadline);
        } //Owner
        else {
            ownerId = new JLabel("Owner Id: " + oF.getOwnerID());
            licensePlate = new JLabel("License Plate: " + oF.getLicensePlate());
            residency = new JLabel("Residency: " + oF.getResidency());
            controllerView.add(ownerId);
            controllerView.add(licensePlate);
            controllerView.add(residency);
        }
        controllerView.add(accept);
        accept.addActionListener(this);

        controllerView.add(reject);
        reject.addActionListener(this);

        controllerView.setSize(500, 500);
        controllerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controllerView.setVisible(true);

        jobAccepted.add(congrats);
        jobAccepted.setSize(500, 500);
        jobAccepted.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jobRejected.add(sorry);
        jobRejected.setSize(500, 500);
        jobRejected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == accept.getActionCommand()) {

            String clientsql = "INSERT INTO client (ClientId, Duration, Deadline) VALUES (?,?,?)";
            String ownersql = "INSERT INTO owner (OwnerId, LicensePlate, Residency) VALUES (?,?,?)";
            jobAccepted.setVisible(true);
            try {
                connection = DriverManager.getConnection(url, username, password);
                if (type) {
                    PreparedStatement pstmt = connection.prepareStatement(clientsql);
                    pstmt.setInt(1, cF.getClientId());
                    pstmt.setInt(2, cF.getDuration());
                    pstmt.setInt(3, cF.getDeadline());
                    int row = pstmt.executeUpdate();
                    System.out.println("Data Successfully Added");
                    System.out.println(row);
                    connection.close();
                } else {
                    PreparedStatement pstmt = connection.prepareStatement(ownersql);
                    pstmt.setInt(1, oF.getOwnerID());
                    pstmt.setString(2, oF.getLicensePlate());
                    pstmt.setInt(3, oF.getResidency());
                    int row = pstmt.executeUpdate();
                    System.out.println("Data Successfully Added");
                    System.out.println(row);
                    connection.close();
                }
            } catch (SQLException f) {
                f.getMessage();
            }

        } else if (e.getActionCommand() == reject.getActionCommand()) {
            jobRejected.setVisible(true);
            System.out.println("Data Was Rejected");

        }

        // Display contents of the queue. 
        System.out.println("Elements of queue " + jobDuration);
        JOptionPane.showMessageDialog(null, "Jobs: " + jobDuration, null, JOptionPane.PLAIN_MESSAGE);

        try {
            File myObj = new File("controller.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException v) {
            System.out.println("An error occurred.");
            v.printStackTrace();
        }

        //WRITES TO THE CREATED FILE
        try {
            FileWriter myWriter = new FileWriter("controller.txt");
            myWriter.write("Client ID: " + cF.getClientId());
            myWriter.write("\nDuration: " + cF.getClientId());
            myWriter.write("\nDead-line: " + cF.getClientId());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            if (jobDuration.peek() != null) {
                JOptionPane.showMessageDialog(null, "Client: " + cF.getClientId() + "\nJob: " + jobDuration.peek(), null, JOptionPane.PLAIN_MESSAGE);
                jobDuration.remove();
                JOptionPane.showMessageDialog(null, "Jobs: " + jobDuration, null, JOptionPane.PLAIN_MESSAGE);
            }

        } catch (IOException v) {
            System.out.println("An error occurred.");
            JOptionPane.showMessageDialog(null, "An error has occured!", null, JOptionPane.PLAIN_MESSAGE);
            v.printStackTrace();
        }

    }

}
