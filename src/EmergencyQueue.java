/**
 * EmergencyQueue.java
 * A linked-list based Queue (FIFO) that manages patients waiting
 * in the emergency unit.
 * Requirement: enqueue, dequeue, display waiting patients, handle empty queue.
 */
public class EmergencyQueue {

    private class QNode {
        Patient patient;
        QNode next;
        QNode(Patient patient) { this.patient = patient; }
    }

    private QNode front, rear;
    private int size;

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QNode newNode = new QNode(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getPatientId() + " (" + patient.getName()
                + ") added to the emergency waiting queue.");
    }

    /** Remove and return the next patient to be treated (front of the queue). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patients waiting.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return patient;
    }

    /** Display all patients currently waiting, in order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("   No patients currently waiting in the emergency queue.");
            return;
        }
        QNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("   " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() { return front == null; }
    public int getSize() { return size; }
}
