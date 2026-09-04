/**
 * TreatmentStack.java
 * A linked-list based Stack (LIFO) that stores completed treatment records.
 * Requirement: push, pop, display, handle empty stack.
 */
public class TreatmentStack {

    private class SNode {
        TreatmentRecord record;
        SNode next;
        SNode(TreatmentRecord record) { this.record = record; }
    }

    private SNode top;
    private int size;

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        SNode newNode = new SNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record (ID " + record.getTreatmentId()
                + ") for Patient " + record.getPatientId() + " pushed to treatment history.");
    }

    /** Pop (remove) the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** Display all treatment records, most recent first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("   No treatment records found.");
            return;
        }
        SNode current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() { return top == null; }
    public int getSize() { return size; }
}
