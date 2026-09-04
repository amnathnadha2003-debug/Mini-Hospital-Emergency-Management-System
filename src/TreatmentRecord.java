/**
 * TreatmentRecord.java
 * Represents a completed treatment record. Stored on the TreatmentStack
 * once a patient's treatment has been finished.
 */
public class TreatmentRecord {
    private int treatmentId;
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionDate;

    public TreatmentRecord(int treatmentId, int patientId, String patientName,
                            String treatmentDetails, String completionDate) {
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionDate = completionDate;
    }

    public int getTreatmentId() { return treatmentId; }
    public int getPatientId() { return patientId; }

    @Override
    public String toString() {
        return "Treatment ID: " + treatmentId +
               " | Patient ID: " + patientId +
               " | Patient: " + patientName +
               " | Details: " + treatmentDetails +
               " | Completed: " + completionDate;
    }
}
