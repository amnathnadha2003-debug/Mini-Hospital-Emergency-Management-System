/**
 * PatientBST.java
 * Binary Search Tree that stores Patient records keyed by Patient ID.
 * Requirement: insert, search, delete, in-order traversal.
 */
public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;
        Node(Patient patient) { this.patient = patient; }
    }

    private Node root;

    /** Insert a new patient into the BST using Patient ID as the key. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) return new Node(patient);

        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return node;
    }

    /** Search for a patient by Patient ID. Returns null if not found. */
    public Patient search(int patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(Node node, int patientId) {
        if (node == null) return null;
        if (patientId == node.patient.getPatientId()) return node.patient;
        return patientId < node.patient.getPatientId()
                ? searchRec(node.left, patientId)
                : searchRec(node.right, patientId);
    }

    /** Delete a patient by Patient ID. */
    public void delete(int patientId) {
        if (search(patientId) == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return;
        }
        root = deleteRec(root, patientId);
        System.out.println("Patient ID " + patientId + " deleted successfully.");
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) return null;

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Two children: replace with in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Display all patients in ascending order of Patient ID (in-order traversal). */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("   No patient records found.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println("   " + node.patient);
        inOrderRec(node.right);
    }

    public boolean isEmpty() { return root == null; }
}
