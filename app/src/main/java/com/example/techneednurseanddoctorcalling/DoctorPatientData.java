package com.example.techneednurseanddoctorcalling;

public class DoctorPatientData {
    private String Patient_Name;
    private String Bed_Number;
    private String email;

    public DoctorPatientData() {
    }

    public DoctorPatientData(String patient_Name, String bed_Number, String email) {
        Patient_Name = patient_Name;
        Bed_Number = bed_Number;
        this.email = email;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }

    public String getBed_Number() {
        return Bed_Number;
    }

    public void setBed_Number(String bed_Number) {
        Bed_Number = bed_Number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
