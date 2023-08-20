import java.util.*;
import java.io.*;
import java.net.PasswordAuthentication;
import java.awt.*;

import javax.net.ssl.ManagerFactoryParameters;
import javax.swing.*;

import java.awt.event.*;
import java.awt.event.MouseAdapter;


public class Frame extends JFrame implements ActionListener{
//initalize variables (buttons, text areas, all the GUI objects)
  private Container c;
  private Doctor manageDoctors = new Doctor();
  private JScrollPane scroll;
  private JTextArea patientDetails;
  private JLabel title, enterName, enterNumber, patientPriority, checkOutName, estimatedWaitingTime, numDoctors;
  private JComboBox patientIssue, checkOutMenu;
  private JButton userInterface, doctorInterface, getTicket, assignAll, assignNext, checkOut, checkEveryoneOut, mainScreen, confirmCheckOut, backButton, ticketRecieved, enterPassword, terminate;
  private JPanel selectInterface, ticketKiosk, patientManager, checkOutPanel, thankYou, safetyCheck;
  private JTextField patientName, patientNumber, password;
  private String font = "Times New Roman";
  private int estimatedTime = 0;

    public Frame() { //constructor
      //draw main screen

        setTitle("");
        setSize(800,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Hospital Waiting Room");
        title.setFont(new Font(font, Font.PLAIN, 30));
        title.setSize(800, 70);
        title.setLocation(10, 15);
        title.setHorizontalAlignment(JLabel.CENTER);
        c.add(title);

        mainScreen = new JButton("Main Screen");
        mainScreen.setFont(new Font(font, Font.PLAIN, 15));
        mainScreen.setSize(125,25);
        mainScreen.setLocation(10, 15);
        mainScreen.setHorizontalAlignment(JButton.CENTER);
        mainScreen.addActionListener(this);
        c.add(mainScreen);

        //add terminate button

        terminate = new JButton("Terminate");
        terminate.setFont(new Font(font, Font.PLAIN, 15));
        terminate.setSize(100,25);
        terminate.setLocation(700, 350);
        terminate.setHorizontalAlignment(JButton.CENTER);
        terminate.addActionListener(this);
        c.add(terminate);

        //draw ticket confirmation screen

        thankYou = new JPanel(new GridBagLayout());
        thankYou.setLocation(0, 0);
        thankYou.setSize(800, 400);

        //draw password screen

        safetyCheck = new JPanel(new GridBagLayout());
        safetyCheck.setLocation(0, 0);
        safetyCheck.setSize(800, 400);

        password = new JTextField("");
        password.setPreferredSize(new Dimension(150, 20));
        enterPassword = new JButton("Enter Password");
        password.addActionListener(this);
        enterPassword.addActionListener(this);
        safetyCheck.add(password);
        safetyCheck.add(enterPassword);

        //add main screen to container

        
        selectInterface = new JPanel(new GridBagLayout());
        selectInterface.setLocation(0, 0);
        selectInterface.setSize(800, 400);
        c.add(selectInterface);

        //draw user interface screen

        userInterface = new JButton("Patient");
        userInterface.setFont(new Font(font, Font.PLAIN, 40));
        userInterface.setSize(300,300);
        userInterface.addActionListener(this);
        selectInterface.add(userInterface);

        //draw doctor interface screen

        doctorInterface = new JButton ("Doctor");
        doctorInterface.setFont(new Font(font, Font.PLAIN, 40));
        doctorInterface.setSize(300,300);
        doctorInterface.addActionListener(this);
        selectInterface.add(doctorInterface); 

        checkOutPanel = new JPanel(new GridBagLayout());
        checkOutPanel.setLocation(0, 100);
        checkOutPanel.setSize(800, 400);


        patientManager = new JPanel(new GridBagLayout());
        patientManager.setLocation(0, 100);
        patientManager.setSize(800, 400);

        //make spring layout (to align the objects)

        SpringLayout layoutDoctor = new SpringLayout();
        patientManager.setLayout(layoutDoctor);

        assignAll = new JButton("Assign All");
        assignAll.setFont(new Font(font, Font.PLAIN, 15));
        assignAll.setPreferredSize(new Dimension(200, 50));
        assignAll.addActionListener(this);
        patientManager.add(assignAll);

        assignNext = new JButton("Assign Next");
        assignNext.setFont(new Font(font, Font.PLAIN, 15));
        assignNext.setPreferredSize(new Dimension(200, 50));
        assignNext.addActionListener(this);
        patientManager.add(assignNext);

        checkOut = new JButton("Check Out");
        checkOut.setFont(new Font(font, Font.PLAIN, 15));
        checkOut.setPreferredSize(new Dimension(200, 50));
        checkOut.addActionListener(this);
        patientManager.add(checkOut);

        patientDetails = new JTextArea(10, 20);
        patientDetails.setEditable(false);

        //add scroll panel to view patients in waiting room
        
        scroll = new JScrollPane(patientDetails);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        patientManager.add(scroll);

        numDoctors = new JLabel("Hospital Waiting Room");
        numDoctors.setFont(new Font(font, Font.PLAIN, 20));
        numDoctors.setSize(200, 50);
        numDoctors.setLocation(680, 300);
        numDoctors.setHorizontalAlignment(JLabel.CENTER);
        patientManager.add(numDoctors);

        checkEveryoneOut = new JButton("Check Out All");
        checkEveryoneOut.setFont(new Font(font, Font.PLAIN, 15));
        checkEveryoneOut.setPreferredSize(new Dimension(200, 50));
        checkEveryoneOut.addActionListener(this);
        patientManager.add(checkEveryoneOut);

        //set constraints for spring layout
        

        layoutDoctor.putConstraint(SpringLayout.WEST, assignAll, 50, SpringLayout.WEST, patientManager);
        layoutDoctor.putConstraint(SpringLayout.NORTH, assignAll, 50, SpringLayout.NORTH, patientManager);
        
        layoutDoctor.putConstraint(SpringLayout.WEST, numDoctors, 50, SpringLayout.WEST, patientManager);


        layoutDoctor.putConstraint(SpringLayout.WEST, assignNext, 50, SpringLayout.WEST, patientManager);
        layoutDoctor.putConstraint(SpringLayout.NORTH, assignNext, 50, SpringLayout.NORTH, assignAll);


        layoutDoctor.putConstraint(SpringLayout.WEST, checkOut, 50, SpringLayout.WEST, patientManager);
        layoutDoctor.putConstraint(SpringLayout.NORTH, checkOut, 50, SpringLayout.NORTH, assignNext);
        layoutDoctor.putConstraint(SpringLayout.WEST, checkEveryoneOut, 150, SpringLayout.EAST, checkOut);
        layoutDoctor.putConstraint(SpringLayout.NORTH, checkEveryoneOut, 200, SpringLayout.NORTH, patientManager);

        layoutDoctor.putConstraint(SpringLayout.WEST, scroll, 375, SpringLayout.WEST, patientManager);
        layoutDoctor.putConstraint(SpringLayout.NORTH, scroll, 0, SpringLayout.NORTH, patientManager);


        //create user interface screens



        ticketKiosk = new JPanel(new GridBagLayout());
        ticketKiosk.setLocation(0, 100);
        ticketKiosk.setSize(800, 400);

        SpringLayout layout = new SpringLayout();
        ticketKiosk.setLayout(layout);

    
        patientName = new JTextField("");
        patientName.setPreferredSize(new Dimension(150, 20));
        enterName = new JLabel("Full Name: ");
        patientName.addActionListener(this);
        ticketKiosk.add(patientName);
        ticketKiosk.add(enterName);
        

        patientNumber = new JTextField();
        patientNumber.setPreferredSize(new Dimension(150, 20));
        enterNumber = new JLabel("Phone Number: ");
        patientNumber.addActionListener(this);
        ticketKiosk.add(patientNumber);
        ticketKiosk.add(enterNumber);

        String[] patientIssues = {"Consultation", "Sick", "Injured", "Check-up", "Emergency"};

        patientIssue = new JComboBox<String>(patientIssues);
        patientIssue.setPreferredSize(new Dimension(150, 20));
        patientPriority = new JLabel("Reason for Visit: ");
        patientIssue.addActionListener(this);
        ticketKiosk.add(patientIssue);
        ticketKiosk.add(patientPriority);

        getTicket = new JButton("Submit");
        getTicket.setFont(new Font(font, Font.PLAIN, 15));
        getTicket.setSize(100,20);
        getTicket.addActionListener(this);
        ticketKiosk.add(getTicket);

        //put constraints

        layout.putConstraint(SpringLayout.WEST, enterName, 250, SpringLayout.WEST, ticketKiosk);
        layout.putConstraint(SpringLayout.NORTH, enterName, 50, SpringLayout.NORTH, ticketKiosk);
        layout.putConstraint(SpringLayout.WEST, patientName, 43, SpringLayout.EAST, enterName);
        layout.putConstraint(SpringLayout.NORTH, patientName, 50, SpringLayout.NORTH, ticketKiosk);

        layout.putConstraint(SpringLayout.WEST, enterNumber, 250, SpringLayout.WEST, ticketKiosk);
        layout.putConstraint(SpringLayout.NORTH, enterNumber, 50, SpringLayout.NORTH, enterName);
        layout.putConstraint(SpringLayout.WEST, patientNumber, 15, SpringLayout.EAST, enterNumber);
        layout.putConstraint(SpringLayout.NORTH, patientNumber, 50, SpringLayout.NORTH, patientName);

        layout.putConstraint(SpringLayout.WEST, patientPriority, 250, SpringLayout.WEST, ticketKiosk);
        layout.putConstraint(SpringLayout.NORTH, patientPriority, 50, SpringLayout.NORTH, enterNumber);
        layout.putConstraint(SpringLayout.WEST, patientIssue, 7, SpringLayout.EAST, patientPriority);
        layout.putConstraint(SpringLayout.NORTH, patientIssue, 50, SpringLayout.NORTH, patientNumber);

        layout.putConstraint(SpringLayout.WEST, getTicket, 390, SpringLayout.WEST, ticketKiosk);
        layout.putConstraint(SpringLayout.NORTH, getTicket, 50, SpringLayout.NORTH, patientPriority);
        
        //let user see 

        setVisible(true);
        
      }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource()==mainScreen) { //if you click main screen you go back to main screen 
        title.setText("Hospital Waiting Room");
        ticketKiosk.setVisible(false);
        patientManager.setVisible(false);
        checkOutPanel.setVisible(false);
        thankYou.setVisible(false);
        safetyCheck.setVisible(false);

        selectInterface.setVisible(true);
      }
      
      if (e.getSource() == userInterface){ //if user interface is clicked, take to ticket kiosk 
        selectInterface.setVisible(false);
        title.setText("Ticket Machine");
        c.add(ticketKiosk);
        ticketKiosk.setVisible(true);
        setVisible(true);
      }
      if (e.getSource() == doctorInterface){ //if user interface is clicked, take to password 
        selectInterface.setVisible(false);
        title.setText("Password");
        c.add(safetyCheck);
        safetyCheck.setVisible(true);
        setVisible(true);
        
      }
      if(e.getSource()==enterPassword) { //if password is entered
        if(password.getText().equals("jisunghenhao")) { //if correct password entered, take to patient manager
          password.setText("");
          safetyCheck.setVisible(false);
          title.setText("Patient Manager");
          patientDetails.setText("In Waitng Room: \n" + manageDoctors.inWaitingRoom());
          numDoctors.setText("Doctors Available: " + manageDoctors.getNumDoctors());
          c.add(patientManager);
          patientManager.setVisible(true);
          setVisible(true);
        }
        else if(password.getText().equals("")) {
          JOptionPane.showMessageDialog(safetyCheck,"please enter a password");
        }
        else { //if incorrect password then display JOptionPane error message
          JOptionPane.showMessageDialog(safetyCheck,"incorrect password");
        }
       
      }
      if(e.getSource()==getTicket) { //if you click get ticket
        if(!patientName.getText().equals("") && patientNumber.getText().length()==8) { //if correct fields inputted

          ticketKiosk.setVisible(false); //create new patient object based on priority
          int priority = 3;
          String patientProblem = String.valueOf(patientIssue.getSelectedItem());
          if(patientProblem.equalsIgnoreCase("emergency")) {
            priority = 1;
            ListNode newPatient = new ListNode(new Patient(patientName.getText(), Integer.parseInt(patientNumber.getText()), priority), null);
            estimatedTime = manageDoctors.estimateTime(priority);
            manageDoctors.getPriorityOne().addToQueue(newPatient);
          }
          else if(patientProblem.equalsIgnoreCase("injured")) {
            priority = 2;
            ListNode newPatient = new ListNode(new Patient(patientName.getText(), Integer.parseInt(patientNumber.getText()), priority), null); 
            estimatedTime = manageDoctors.estimateTime(priority);
            manageDoctors.getPriorityTwo().addToQueue(newPatient);
            

          }
          else {
            ListNode newPatient = new ListNode(new Patient(patientName.getText(), Integer.parseInt(patientNumber.getText()), priority), null); 
            estimatedTime = manageDoctors.estimateTime(priority);
            manageDoctors.getPriorityThree().addToQueue(newPatient);
          } 

          //create the ticket confirmation screen with the estimate waiting time

          thankYou = new JPanel(new GridBagLayout());
          thankYou.setLocation(0, 0);
          thankYou.setSize(800, 400);

          estimatedWaitingTime = new JLabel("Estimated Waiting Time: " + estimatedTime + " minutes");
          thankYou.add(estimatedWaitingTime);
          ticketRecieved = new JButton("OK");
          ticketRecieved.setFont(new Font(font, Font.PLAIN, 15));
          ticketRecieved.setPreferredSize(new Dimension(150, 25));
          ticketRecieved.addActionListener(this);
          thankYou.add(ticketRecieved);
          c.add(thankYou);
          thankYou.setVisible(true);
          setVisible(true);

          
         
          System.out.println("test queue 1");
          manageDoctors.getPriorityOne().printPatientQueue();
          System.out.println("test queue 2");
          manageDoctors.getPriorityTwo().printPatientQueue();
          System.out.println("test queue 3");
          manageDoctors.getPriorityThree().printPatientQueue();
        
          } //otherwise, display JOPtionPane error (invalid inputs)
        else if (patientName.getText().equals("")) {
          JOptionPane.showMessageDialog(ticketKiosk,"please enter your name");
          System.out.println("please enter name");
        }
        else {
          JOptionPane.showMessageDialog(ticketKiosk,"please enter a valid number (8 digits; no spaces)");
        }
      }
      if(e.getSource()==ticketRecieved) { //if ticket recieved is confirmed, then go back to ticketing machine screen
        patientName.setText("");
        patientNumber.setText("");
        thankYou.setVisible(false);
        ticketKiosk.setVisible(true);

      }
      if(e.getSource()==assignNext) { //if assign next is clicked
        if(manageDoctors.getNumDoctors()<=0) { //if no doctor available display JOptionPane error message
          JOptionPane.showMessageDialog(ticketKiosk,"no doctor available");
        }
        else {
          manageDoctors.assignNext(); //assign next
        }
        numDoctors.setText("Doctors Available: " + manageDoctors.getNumDoctors()); //update doctors available and waiting room
        patientDetails.setText("In Waitng Room: \n" + manageDoctors.inWaitingRoom());

      }
      if(e.getSource()==assignAll) { //if assign all is clicked
        if(manageDoctors.getNumDoctors()<=0) {
          JOptionPane.showMessageDialog(ticketKiosk,"no doctor available"); 
        }
        else {
          manageDoctors.assignAll(); //assign all
          numDoctors.setText("Doctors Available: " + manageDoctors.getNumDoctors());
          patientDetails.setText("In Waitng Room: \n" + manageDoctors.inWaitingRoom());
        }

      }
      if(e.getSource()==terminate) { //if terminate
        manageDoctors.writeToFile(); //write to file
        dispose(); //exit
        System.exit(0);
      }
      if(e.getSource() == checkEveryoneOut) { //if checkEveryoneOut is pressed, then
        manageDoctors.checkOutAll(); //check everyone out
        numDoctors.setText("Doctors Available: " + manageDoctors.getNumDoctors()); //update number of doctors available

      }
      if(e.getSource()==checkOut) { //if check out is pressed, then go to check out screen
        patientManager.setVisible(false);
        title.setText("Check Out");

        //create check out screen
        checkOutPanel = new JPanel(new GridBagLayout());
        checkOutPanel.setLocation(0, 100);
        checkOutPanel.setSize(800, 400);

        checkOutMenu = new JComboBox<String>(manageDoctors.getAtDoctor());
        checkOutMenu.setPreferredSize(new Dimension(150, 20));
        checkOutPanel.setLocation(40,0);
        confirmCheckOut = new JButton("Check Out");
        confirmCheckOut.setFont(new Font(font, Font.PLAIN, 15));
        confirmCheckOut.setPreferredSize(new Dimension(150, 25));
        confirmCheckOut.addActionListener(this);
        SpringLayout layout = new SpringLayout();
        checkOutPanel.setLayout(layout);

        //create back button

        backButton = new JButton("Back");
        backButton.setFont(new Font(font, Font.PLAIN, 15));
        backButton.setSize(100,25);
        //backButton.setLocation(150, 150);
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.addActionListener(this);
        checkOutPanel.add(backButton);
        
        
        checkOutName = new JLabel("Who are you checking out: ");
        checkOutMenu.addActionListener(this);
        checkOutPanel.add(checkOutName);
        checkOutPanel.add(checkOutMenu);
        checkOutPanel.add(confirmCheckOut);

        //constraints for alignment of layout
        layout.putConstraint(SpringLayout.WEST, checkOutName, 150, SpringLayout.WEST, checkOutPanel);
        layout.putConstraint(SpringLayout.NORTH, checkOutName, 200, SpringLayout.NORTH, checkOutPanel);
        layout.putConstraint(SpringLayout.WEST, checkOutMenu, 10, SpringLayout.EAST, checkOutName);
        layout.putConstraint(SpringLayout.NORTH, checkOutMenu, 200, SpringLayout.NORTH, checkOutPanel);

        layout.putConstraint(SpringLayout.WEST, confirmCheckOut, 330, SpringLayout.WEST, checkOutPanel);
        layout.putConstraint(SpringLayout.NORTH, confirmCheckOut, 50, SpringLayout.NORTH, checkOutMenu);
        layout.putConstraint(SpringLayout.NORTH, backButton, 15, SpringLayout.NORTH, checkOutPanel);
        layout.putConstraint(SpringLayout.WEST, backButton, 680, SpringLayout.WEST, checkOutPanel);
        

        c.add(checkOutPanel);
        checkOutPanel.setVisible(true);
        setVisible(true);
        //manageDoctors.checkOut(patientLeaving);
      }
      if(e.getSource() == confirmCheckOut) { //if confirm check out
        if(checkOutMenu.getSelectedItem()==null) { //if no one selected, give JOPtionPane error handling message
          JOptionPane.showMessageDialog(ticketKiosk,"no one to check out");
        }
        else {
          manageDoctors.checkOut(checkOutMenu.getSelectedIndex()); //check the person out and update manage patients screen (also go back to manage patients screen)
          numDoctors.setText("Doctors Available: " + manageDoctors.getNumDoctors());
          title.setText("Patient Manager");
          checkOutPanel.setVisible(false);
          patientManager.setVisible(true);
        }
        
      
      }

      if(e.getSource() == backButton) { //if back button pressed
        title.setText("Patient Manager");
        checkOutPanel.setVisible(false); //go back to manage patients screen
        patientManager.setVisible(true);      
      }
        
    }
}