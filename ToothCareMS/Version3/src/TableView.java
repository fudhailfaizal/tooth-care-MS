import java.util.ArrayList;

public class TableView {
    private ArrayList<String> appointmentList;
    private ArrayList<String> patientList;

    public TableView(ArrayList<String> appointmentList, ArrayList<String> patientList) {
        this.appointmentList = appointmentList;
        this.patientList = patientList;
    }

    public ArrayList<String[]> getTableViewList() {
        ArrayList<String[]> tableViewList = new ArrayList<>();

        for (String appointment : appointmentList) {
            String[] appointmentDetails = appointment.split(", ");
            String patientID = appointmentDetails[1].split(":")[1].trim();

            String patientDetails = findPatientDetails(patientID);

            if (patientDetails != null) {
                String[] combinedDetails = combineArrays(appointmentDetails, patientDetails.split(", "));
                tableViewList.add(combinedDetails);
            }
        }

        return tableViewList;
    }

    private String findPatientDetails(String patientID) {
        for (String patient : patientList) {
            if (patient.contains("Patient ID: " + patientID)) {
                return patient;
            }
        }
        return null;
    }

    private String[] combineArrays(String[] arr1, String[] arr2) {
        String[] result = new String[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }
}
