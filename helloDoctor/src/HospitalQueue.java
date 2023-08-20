public class HospitalQueue {
    private ListNode head; //set the head and tail
    private ListNode tail; 
    private int queueLength = 0; //set queue length
    public HospitalQueue() {

    }
    public HospitalQueue(ListNode head) {
        this.head = head; //if new hospital queue with list node, make it the head and tail
        tail = head;

    }

    public void addToQueue(ListNode nextNode) { 
        if(head==null) { //if head is null
            head = nextNode; //create the head and tail
            tail = nextNode;
            queueLength++; //incrememnt queue length
            return;
        }

        tail.setNext(nextNode); //if head isn't null add to end of LinkedList
        tail = nextNode;
        queueLength++;
    }

    public void addToQueue(ListNode newNode, ListNode afterNode, ListNode beforeNode) { //add at specific location
        if(beforeNode == head) { //if we want to add before head
            newNode.setNext(beforeNode); //set new node to head
            head=newNode;
            queueLength++;
            return;
        }
        if(afterNode==tail) { //if we want to add after tail
            afterNode.setNext(newNode); //set new node to tail
            tail=newNode;
            queueLength++;
            return;
        }
        afterNode.setNext(newNode); //otherwise set the next node of the node we want to be after to the new node
        newNode.setNext(beforeNode); //set the next node of the new node to the node we want to be before
        queueLength++; //incrememnt queue length

    }

    public boolean removeFromQueue() {
        if(head==null) { //if head is null then there do nothing
            queueLength--;
            return false;
        }
        head = head.getNext(); //otherwise set the head to the next value (remove from start of LinkedList)
        queueLength--;
        return true;
    }

    public void removeFromQueue(ListNode removeNode) { //remove at specific node
        if(head==null) return;
        if(head==removeNode) { //if you want to remove head
            head = head.getNext(); //set head to the next node
            queueLength--;
            return;
        }
        ListNode pointer = head; //create pointer
        while(pointer.getNext()!= null && pointer.getNext()!= removeNode) { //iterate until you are before the node you want to remove
            pointer = pointer.getNext();
        }
        pointer.setNext(pointer.getNext().getNext()); //set the point of the previous node (of the removeNode) to the node after the removeNode
        queueLength--; 


    }

    public ListNode getFirstPatient() {
        return head; //getter method that returns first patient in queue
    }

    public int getQueueLength() {
        return queueLength; //getter method that returns the queue length
    }

    public void printPatientQueue() {
        ListNode pointer = head; //print the patient queue by iterating to everyone in the queue
        while(pointer!=null) {
            System.out.println(pointer.getData().getName());
            pointer=pointer.getNext();
        }

    }


    public int findQueueNumber(ListNode findNode) {
        if(head == null) return 0; //if head is null don't do anything
        int count = 1; 
        ListNode pointer = head;
        while(pointer!=null && pointer!=findNode) { //sequential search through the queue and incremenet count until you reach the node you are looking for
            pointer=pointer.getNext();
            count++;
        }
        return count; //return the queue number of the node/patient
    }
}
