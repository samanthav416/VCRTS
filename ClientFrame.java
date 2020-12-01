package vcrts;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ClientFrame implements ActionListener {

    static JFrame clientView = new JFrame("Client Sign In Form");

    static JLabel clientidLabel = new JLabel("Enter ID");
    static JTextField clientID = new JTextField("0");
    static JLabel jobDurationLabel = new JLabel("Job Duration");
    static JTextField jobDuration = new JTextField("0");
    static JLabel jobDeadlineLabel = new JLabel("Job Deadline");
    static JTextField jobDeadline = new JTextField("0");
    static JButton clientButton = new JButton("Submit");
    static JLabel blank = new JLabel();

    public ClientFrame() {
        clientView.setLayout(new GridLayout(4, 4));

        clientView.add(clientidLabel);
        clientView.add(clientID);
        clientView.add(jobDurationLabel);
        clientView.add(jobDuration);
        clientView.add(jobDeadlineLabel);
        clientView.add(jobDeadline);
        clientView.add(blank);
        clientView.add(clientButton);
        clientButton.addActionListener(this);

        clientView.setSize(500, 500);
        clientView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientView.setVisible(true);

    }

    //SETTERS
    public void setId(JTextField clientID) {
        clientID = this.clientID;
    }

    public void setDuration(JTextField jobDuration) {
        jobDuration = this.jobDuration;
    }

    public void setDeadLine(JTextField jobDeadline) {
        jobDeadline = this.jobDeadline;
    }

    public int getClientId() {
        int cID = Integer.parseInt(clientID.getText());
        return cID;
    }

    public int getDuration() {
        int jDuration = Integer.parseInt(jobDuration.getText());
        return jDuration;
    }

    public int getDeadline() {
        int dl = Integer.parseInt(jobDeadline.getText());
        return dl;
    }
    //Clears all fields 

    public static void reset() {
        clientID.setText(null);
        jobDuration.setText(null);
        jobDeadline.setText(null);
    }

    public static void hide() {
        clientView.setVisible(false);
    }

//Action Event for button
    @Override
    public void actionPerformed(ActionEvent event) {

        int iD = getClientId();
        int duration = getDuration();
        int deadLine = getDeadline();
        new VCFrame();

    }

    //CREATES NEW FILE
    /* try {
	  		      File myObj = new File("client.txt");
	  		      if (myObj.createNewFile()) {
	  		        System.out.println("File created: " + myObj.getName()); 	
	  		      } else {
	  		        System.out.println("File already exists.");
	  		      }
	  		    } catch (IOException e) {
	  		      System.out.println("An error occurred.");
	  		      e.printStackTrace();
	  		    }
	  		
	          //WRITES TO THE CREATED FILE
	  		try {
	  		      FileWriter myWriter = new FileWriter("client.txt");
	  		      myWriter.write("Client ID: "+iD);
	  		      myWriter.write("\nDuration: "+duration);
	  		      myWriter.write("\nDead-line: "+deadLine);
	  		      myWriter.close();
	  		      System.out.println("Successfully wrote to the file.");
	  		      JOptionPane.showMessageDialog(null,"Successfully wrote to the file.", null, JOptionPane.PLAIN_MESSAGE);
                              new VCFrame();
	  		      
	  		    } catch (IOException e) {
	  		      System.out.println("An error occurred.");
	  		      JOptionPane.showMessageDialog(null,"An error has occured!", null, JOptionPane.PLAIN_MESSAGE);
	  		      e.printStackTrace();
	  		    }*/
}
