import java.util.ArrayList;
import java.io.*;
import java.util.InputMismatchException;

public class Doctor {
   // intialize variable 
   private int numDoctors = 5; //numDoctors available
   private HospitalQueue priorityOne = new HospitalQueue(); 
   private HospitalQueue priorityTwo = new HospitalQueue();
   private HospitalQueue priorityThree = new HospitalQueue(); //priority queues (linked list)

   private ArrayList<Patient> atDoctor = new ArrayList<Patient>();
   private ArrayList<Patient> checkedOut = new ArrayList<Patient>(); //array list for at doctor and checked out patients
   private int numPriorityOne = 3;
   private int numPriorityTwo = 2;
   private int numPriorityThree = 1; //ratio of how priority should be taken

   public Doctor() {

   } 

   public int getNumDoctors() {
      return numDoctors; //getter method #doctors
   }

   public HospitalQueue getPriorityOne() {
      return priorityOne; //getter method priority 1 linked list
   }

   public HospitalQueue getPriorityTwo() {
      return priorityTwo; //getter method priority 2 linked list
   }

   public HospitalQueue getPriorityThree() {
      return priorityThree; //getter method priority 3 linked list
   }

   public void assignNext() {
      //if 3 people from priority one have not been admitted 
      if(numPriorityOne%3<3) {
         if(priorityOne.getFirstPatient()!=null){ //if there are people in priority one
            atDoctor.add(priorityOne.getFirstPatient().getData()); //admit them to doctor
            priorityOne.removeFromQueue(); //remove from queue
            numPriorityOne--; //now admit two more people
            numDoctors--; //decrement doctors
            return;
         }
      }
      //if 2 people from priority one have not been admitted 
      if (numPriorityTwo%2<2) {
         if(priorityTwo.getFirstPatient()!=null) { //if there are people in priority two
            atDoctor.add(priorityTwo.getFirstPatient().getData()); 
            priorityTwo.removeFromQueue(); 
            numPriorityTwo--;
            numDoctors--;
            return;
         }

      }
       //if 1 person from priority one have not been admitted 
      if(numPriorityThree%1<1){
         if(priorityThree.getFirstPatient()!=null) { //if there are people in priority one
            atDoctor.add(priorityThree.getFirstPatient().getData());
            priorityThree.removeFromQueue();
            numPriorityThree--;
            numDoctors--;
            return;
         }
      }
      if(priorityOne.getFirstPatient()==null && priorityTwo.getFirstPatient()==null && priorityThree.getFirstPatient()==null) {
         System.out.println("no patients waiting"); //if there is no one waiting, then don't do anything
         return;
      }
      numPriorityThree=3; //if there are people waiting but the 3:2:1 ratio has been fufilled, reset the ratio
      numPriorityTwo=2;
      numPriorityOne=1;
      assignNext(); //call the method again (recursion)
      }

   public void assignAll() { 
      

      while(numDoctors>0 ) { //while there are still doctors available
         if(priorityOne.getFirstPatient()==null && priorityTwo.getFirstPatient()==null && priorityThree.getFirstPatient()==null) {
            System.out.println("no patients waiting"); //if no patients in queue, do nothing 
            return;
         }
         assignNext(); //assign next patient
      }
   }

   public void checkOut(int patientLeaving) {
      Patient temp = atDoctor.get(patientLeaving); 
      atDoctor.remove(patientLeaving); //remove from atDoctor ArrayList
      checkedOut.add(temp); //add to the checkOut ArrayList
      numDoctors++; //increase # of doctors

   }
   public void checkOutAll() {
      for(Patient c : atDoctor) { //for every patient at the doctor
         checkedOut.add(c); // add them to the check out ArrayList
         numDoctors++; //increase # of doctors
      
      } 
      atDoctor =  new ArrayList<Patient>(); //reset atDoctor ArrayList
   }

   public String[] getAtDoctor() { //get a String array of the patients in the doctor (to use in the check out menu box)
      String[] atDoctorNames = new String[atDoctor.size()]; 
      for(int i = 0; i<atDoctor.size();i++) { //for loop to add to array list
         atDoctorNames[i] = atDoctor.get(i).getName();

      }
      return atDoctorNames;

   }

   public int estimateTime(int priority) { //estimate the wait time for the patient
      int temp;
      int estimatedTime = 10;
      int priorityOneLength = priorityOne.getQueueLength();
      int priorityTwoLength = priorityTwo.getQueueLength();
      int priorityThreeLength = priorityThree.getQueueLength(); //the length of the queues
      System.out.println(priorityOneLength); //the length of the queue the patient is in
      if(priority==1) 
         temp = priorityOneLength;
      else if(priority==2) 
         temp = priorityTwoLength;
      else 
         temp = priorityThreeLength;
      System.out.println(temp);
      System.out.println(priority);
      while(temp>=0) { //while we haven't reached the patient in the queue 
            for(int i =3; i>0; i--) { //three people in priority one are admitted until patient is reached
               if(priority==1) {
                  if(temp<=0) {
                     estimatedTime -= getNumDoctors()*10;
                     if(estimatedTime<0) return 0;
                     return estimatedTime;
                  }
                  temp--;
               }
               if(priorityOneLength>0) { //add to estimated time
               priorityOneLength--;
               estimatedTime+=10;
               }
            }
         for(int i =2; i>0; i--) { //two people in priority two are admitted until patient is reached
            if(priority==2) {
               if(temp<=0) {
                  estimatedTime -= getNumDoctors()*10;
                  if(estimatedTime<0) return 0;
                  return estimatedTime;
               }
               temp--;
            }
            if(priorityTwoLength>0) { //add to estimated time
            priorityTwoLength--;
            estimatedTime+=10;
            }
      }
         for(int i =1; i>0; i--) {  //one person in priority three is admitted until patient is reached 
            if(priority==3) {
               if(temp<=0) {
                  estimatedTime -= getNumDoctors()*10;
                  if(estimatedTime<0) return 0;
                  return estimatedTime;
               }
               temp--;
            }
            if(priorityThreeLength>0) { //add to estimated time
            priorityThreeLength--;
            estimatedTime+=10;
            }
         }
      }
      estimatedTime -= getNumDoctors()*10; //substract time based on number of doctors available
      if(estimatedTime<0) return 0;
      return estimatedTime; //return the estimated patient waiting time
   }

   public String inWaitingRoom() {
      String waitingRoom = ""; //create the waiting room string
      ListNode temp = getPriorityOne().getFirstPatient();
      while(temp!=null) { //for every person in priority queue 1
         waitingRoom += temp.getData().getName() + ", " + temp.getData().getNumber() + ", " + temp.getData().getPriority();
         waitingRoom+="\n"; //add their name, number, and priority to the waiting room string
         temp=temp.getNext();
      }
      temp=getPriorityTwo().getFirstPatient();
      while(temp!=null) { //for every person in priority queue 2
         waitingRoom += temp.getData().getName() + ", " + temp.getData().getNumber() + ", " + temp.getData().getPriority();
         waitingRoom+="\n"; //add their name, number, and priority to the waiting room string
         temp=temp.getNext(); 
      }
      temp=getPriorityThree().getFirstPatient();
      while(temp!=null) {  //for every person in priority queue 3
         waitingRoom += temp.getData().getName() + ", " + temp.getData().getNumber() + ", " + temp.getData().getPriority();
         waitingRoom+="\n"; //add their name, number, and priority to the waiting room string
         temp=temp.getNext();
      }
      return waitingRoom; //return the string
   }

  

   public void printCheckedOut() { 
      for(Patient c : checkedOut) { //for every patient in the check out ArrayList
         System.out.println(c.getName()); //print the patients name
      }

   }
   public void printAtDoctor() {
      for(Patient c : atDoctor) { //for every patient in the at doctor ArrayList
         System.out.println(c.getName()); //print the patients name
      }
   }

   public void writeToFile() { //write to the file
      try{
         FileWriter outfile = new FileWriter("patientForDay.csv", false); //create new file (or rewrite)
         FileWriter outfileTwo = new FileWriter("patientsAllRecords.csv", true); //append to file
         for(Patient c : checkedOut) {
            outfile.write(c.getName() + ", " + c.getNumber() + ", " + c.getPriority() + "\n"); //add person details
            outfileTwo.write(c.getName() + ", " + c.getNumber() + ", " + c.getPriority() + "\n");
         }
         outfile.close(); //close file
         outfileTwo.close();
      }
      catch(IOException ex) {
         System.out.println("Error writing in a file."); //exception handling
   }
      catch(InputMismatchException ex) {
         System.out.println("Error writing in a file.");
      }
   }






}
