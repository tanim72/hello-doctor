public class ListNode {
    private Patient data; //patient data
    private ListNode next; //listnode next ()

    public ListNode() {
        data = null; //set both to null (default constructor)
        next = null;

    }
    public ListNode(Patient data, ListNode next) {
        this.data=data; //set patient argument to data variable
        this.next=next; //set listnode argument to the next variable
    }
    public Patient getData() { //getter method to get patient object
        return data;
    }
    public ListNode getNext() { //getter method to get next ListNode
        return next;
    }
    public void setData(Patient p) { //setter method to set patient object
        data = p;
    }
    public void setNext(ListNode nextNode) { //setter method to set next ListNode
        next = nextNode;
    }
}
