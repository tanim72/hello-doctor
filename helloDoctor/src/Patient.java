public class Patient {
    private String name; //variable that define a person object (name, number, priority)
    private int number;
    private int priority; 
    public Patient(String name, int number, int priority) {
        this.name = name; //set the arguments to the respective variables
        this.number = number;
        this.priority = priority; 

    }
    public Patient() { //default constructor 

    }
    public String getName() { //getter method to get patient name
        return name;
    }
    public int getPriority() { //getter method to get patient priority
        return priority;
    }
    public int getNumber() { //getter method to get patient number
        return number;
    }
}