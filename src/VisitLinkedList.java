/**
 * VisitLinkedList.java
 * A Singly Linked List that stores a patient's previous visit history.
 * Requirement: Add visit, remove visit, search visit, display visit history.
 */
public class VisitLinkedList {

    private class VisitNode {
        Visit visit;
        VisitNode next;
        VisitNode(Visit visit) { this.visit = visit; }
    }

    private VisitNode head;

    /** Add a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    /** Remove a visit by its Visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Search for a visit by Visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /** Display the full visit history in order. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("      No visit history found for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println("      " + current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() { return head == null; }
}
