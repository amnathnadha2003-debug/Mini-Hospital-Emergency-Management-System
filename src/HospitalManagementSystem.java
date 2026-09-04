import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * HospitalManagementSystem.java
 * Main class: Mini Hospital Emergency Management System.
 * Ties together all four required data structures:
 *   1. PatientBST      - patient records (Binary Search Tree)
 *   2. EmergencyQueue   - emergency waiting list (Queue / FIFO)
 *   3. TreatmentStack   - completed treatment history (Stack / LIFO)
 *   4. VisitLinkedList  - per-patient visit history (Singly Linked List)
 */
public class HospitalManagementSystem {

    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // simple auto-incrementing IDs for visits and treatment records
    private static int nextVisitId = 1;
    private static int nextTreatmentId = 1;

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=========================================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addNewPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.displayInOrder();
                case 5 -> addPatientToQueue();
                case 6 -> dequeueAndTreat();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> treatmentStack.displayStack();
                case 9 -> popTreatmentRecord();
                case 10 -> addVisitToPatient();
                case 11 -> removeVisitFromPatient();
                case 12 -> searchVisitOfPatient();
                case 13 -> displayPatientVisitHistory();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println(" 1.  Register New Patient (BST insert)");
        System.out.println(" 2.  Search Patient by ID (BST search)");
        System.out.println(" 3.  Delete Patient (BST delete)");
        System.out.println(" 4.  Display All Patients - Ascending ID (BST in-order)");
        System.out.println(" 5.  Add Patient to Emergency Queue (Enqueue)");
        System.out.println(" 6.  Call Next Patient for Treatment (Dequeue)");
        System.out.println(" 7.  Display Emergency Waiting Queue");
        System.out.println(" 8.  Display Treatment History (Stack)");
        System.out.println(" 9.  Undo Last Completed Treatment (Pop)");
        System.out.println("10.  Add Visit to Patient History (Linked List add)");
        System.out.println("11.  Remove Visit from Patient History (Linked List remove)");
        System.out.println("12.  Search Visit in Patient History (Linked List search)");
        System.out.println("13.  Display Patient Visit History (Linked List display)");
        System.out.println(" 0.  Exit");
        System.out.println("---------------------------------------------------------");
    }

    // ---------------------- BST operations ----------------------

    private static void addNewPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with ID " + id + " already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully:\n   " + patient);
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Patient found:\n   " + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        patientBST.delete(id);
    }

    // ---------------------- Queue operations ----------------------

    private static void addPatientToQueue() {
        int id = readInt("Enter Patient ID to add to emergency queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Register the patient first (option 1).");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void dequeueAndTreat() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return;

        System.out.println("Now treating: " + patient);
        String details = readString("Enter treatment details for this patient: ");

        TreatmentRecord record = new TreatmentRecord(
                nextTreatmentId++,
                patient.getPatientId(),
                patient.getName(),
                details,
                dateFormat.format(new Date())
        );
        treatmentStack.push(record);
    }

    // ---------------------- Stack operations ----------------------

    private static void popTreatmentRecord() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record:\n   " + record);
        }
    }

    // ---------------------- Linked List (visit history) operations ----------------------

    private static void addVisitToPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment Given: ");

        Visit visit = new Visit(nextVisitId++, dateFormat.format(new Date()), doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to patient " + id + "'s history:\n   " + visit);
    }

    private static void removeVisitFromPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "Visit ID not found.");
    }

    private static void searchVisitOfPatient() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit != null ? "Visit found:\n   " + visit : "Visit ID not found.");
    }

    private static void displayPatientVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        System.out.println("Visit history for " + patient.getName() + " (ID " + id + "):");
        patient.getVisitHistory().displayVisits();
    }

    // ---------------------- Input helpers ----------------------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
