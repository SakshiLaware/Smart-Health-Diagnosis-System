package com.hospital.model;

public class Rule {

    private String disease;
    private String treatment;
    private String doctor;
    private String severity;

    public Rule(String disease, String treatment,
                String doctor, String severity) {
        this.disease = disease;
        this.treatment = treatment;
        this.doctor = doctor;
        this.severity = severity;
    }

    public String getDisease() {
        return disease;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getSeverity() {
        return severity;
    }
}