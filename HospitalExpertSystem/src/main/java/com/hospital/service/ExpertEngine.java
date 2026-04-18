package com.hospital.service;

import java.util.*;
import com.hospital.model.Rule;

public class ExpertEngine {

    class DiseaseRule {
        String name, treatment, doctor, severity;
        Set<String> symptoms;
        int weight;

        DiseaseRule(String name, String treatment, String doctor, String severity, int weight, String... sym) {
            this.name = name;
            this.treatment = treatment;
            this.doctor = doctor;
            this.severity = severity;
            this.weight = weight;
            this.symptoms = new HashSet<>(Arrays.asList(sym));
        }
    }

    List<DiseaseRule> rules = new ArrayList<>();

    public ExpertEngine() {

        // ---------------- FEVER GROUP ----------------
        rules.add(new DiseaseRule("Viral Fever",
                "Take proper rest and drink plenty of fluids. Use paracetamol if needed and monitor your temperature.",
                "General Physician","Mild",2,"fever","cold","cough"));

        rules.add(new DiseaseRule("Flu (Influenza)",
                "Stay hydrated and take adequate rest. Avoid cold exposure and consult a doctor if symptoms worsen.",
                "General Physician","Mild",2,"fever","cough","sore throat"));

        rules.add(new DiseaseRule("Dengue",
                "Get a blood test done immediately and avoid self-medication. Consult a doctor as soon as possible.",
                "General Physician","Severe",5,"fever","body pain","fatigue","joint pain"));

        rules.add(new DiseaseRule("Typhoid",
                "Consult a doctor for proper medication and avoid outside food. Maintain hygiene and drink clean water.",
                "General Physician","Moderate",4,"fever","vomiting","stomach pain"));

        rules.add(new DiseaseRule("COVID-19",
                "Get tested and isolate yourself to prevent spread. Monitor oxygen levels and consult a doctor.",
                "General Physician","Severe",5,"fever","cough","loss of smell"));

        // ---------------- RESPIRATORY ----------------
        rules.add(new DiseaseRule("Common Cold",
                "Do steam inhalation and drink warm fluids. Take rest and avoid cold environments.",
                "General Physician","Mild",2,"cold","cough"));

        rules.add(new DiseaseRule("Bronchitis",
                "Consult a pulmonologist and avoid dust or smoke. Take prescribed medication and proper rest.",
                "Pulmonologist","Moderate",3,"cough","breathing problem"));

        rules.add(new DiseaseRule("Pneumonia",
                "Seek immediate medical care and do not ignore symptoms. Follow doctor’s advice strictly.",
                "Pulmonologist","Severe",5,"cough","chest pain","fever"));

        rules.add(new DiseaseRule("Asthma",
                "Use inhaler as prescribed and avoid triggers like dust. Seek medical help if breathing worsens.",
                "Pulmonologist","Severe",5,"breathing problem","cough"));

        // ---------------- DIGESTIVE ----------------
        rules.add(new DiseaseRule("Food Poisoning",
                "Drink ORS and stay hydrated. Avoid outside food and eat light meals until recovery.",
                "General Physician","Moderate",3,"vomiting","diarrhea"));

        rules.add(new DiseaseRule("Gastroenteritis",
                "Maintain hydration and avoid oily food. Consult a doctor if symptoms continue.",
                "General Physician","Moderate",3,"stomach pain","diarrhea","vomiting"));

        rules.add(new DiseaseRule("Gastritis",
                "Avoid spicy and oily food. Take antacids if required and maintain a simple diet.",
                "General Physician","Mild",2,"stomach pain"));

        rules.add(new DiseaseRule("Appendicitis",
                "Seek immediate medical attention as surgery may be required. Do not delay treatment.",
                "Surgeon","Severe",5,"stomach pain","vomiting","fever"));

        // ---------------- HEART ----------------
        rules.add(new DiseaseRule("Heart Disease",
                "Consult a cardiologist immediately and avoid physical exertion. ECG and tests are required.",
                "Cardiologist","Severe",5,"chest pain","breathing problem"));

        rules.add(new DiseaseRule("Hypertension",
                "Monitor blood pressure regularly and reduce salt intake. Follow a healthy lifestyle.",
                "Cardiologist","Moderate",3,"high bp"));

        // ---------------- METABOLIC ----------------
        rules.add(new DiseaseRule("Diabetes",
                "Check blood sugar levels and follow a proper diet. Take medication as prescribed.",
                "Endocrinologist","Severe",5,"high sugar"));

        rules.add(new DiseaseRule("Anemia",
                "Increase iron intake through diet and supplements. Consult a doctor for proper diagnosis.",
                "General Physician","Moderate",3,"fatigue","dizziness"));

        // ---------------- NEURO ----------------
        rules.add(new DiseaseRule("Migraine",
                "Take rest in a calm and dark environment. Avoid stress and loud noise.",
                "Neurologist","Moderate",3,"headache","dizziness"));

        rules.add(new DiseaseRule("Stress Headache",
                "Take proper rest and reduce screen time. Stay hydrated and manage stress levels.",
                "General Physician","Mild",2,"headache"));

        // ---------------- BONES ----------------
        rules.add(new DiseaseRule("Arthritis",
                "Avoid heavy activity and take prescribed pain relief. Consult orthopedic specialist.",
                "Orthopedic","Moderate",3,"joint pain"));

        rules.add(new DiseaseRule("Muscle Strain",
                "Rest the affected area and avoid heavy lifting. Apply hot or cold compress if needed.",
                "Orthopedic","Mild",2,"back pain","body pain"));

        // ---------------- SKIN ----------------
        rules.add(new DiseaseRule("Allergy",
                "Avoid allergens and take antihistamines. Keep skin clean and consult doctor if needed.",
                "Dermatologist","Moderate",3,"skin rash"));

        rules.add(new DiseaseRule("Chickenpox",
                "Avoid contact with others and maintain hygiene. Take medication as prescribed by doctor.",
                "Dermatologist","Moderate",3,"fever","skin rash"));

        // ---------------- EYE / EAR ----------------
        rules.add(new DiseaseRule("Conjunctivitis",
                "Avoid touching eyes and maintain hygiene. Use prescribed eye drops.",
                "Ophthalmologist","Moderate",3,"eye redness"));

        rules.add(new DiseaseRule("Ear Infection",
                "Avoid inserting objects in ear and consult doctor. Use prescribed ear drops.",
                "ENT Specialist","Moderate",3,"ear pain"));

        // -------- AUTO COMBINATIONS (increase coverage) --------
        String[] base = {"fever","cough","fatigue","headache","dizziness","vomiting","stomach pain","joint pain"};

        for(int i=0;i<base.length;i++){
            for(int j=i+1;j<base.length;j++){
                rules.add(new DiseaseRule(
                        "General Infection ("+base[i]+" + "+base[j]+")",
                        "Monitor symptoms and take proper rest. Consult a doctor if condition worsens.",
                        "General Physician",
                        "Moderate",
                        2,
                        base[i], base[j]
                ));
            }
        }
    }

    public Rule diagnose(String[] symptoms, int days) {

        Set<String> user = new HashSet<>(Arrays.asList(symptoms));

        DiseaseRule best = null;
        int maxScore = -1;

        for (DiseaseRule d : rules) {

            int score = 0;

            // symptom match
            for (String s : d.symptoms) {
                if (user.contains(s)) score += 3;
            }

            // weight importance
            score += d.weight;

            // exact match bonus
            if (user.containsAll(d.symptoms)) score += 5;

            // severe priority
            if ((user.contains("chest pain") || user.contains("breathing problem"))
                    && d.severity.equals("Severe")) {
                score += 6;
            }

            // days logic
            if (days >= 5 && d.severity.equals("Severe")) score += 3;
            if (days == 1 && d.severity.equals("Mild")) score += 1;

            if (score > maxScore) {
                maxScore = score;
                best = d;
            }
        }

        if (best == null) {
            return new Rule("General Checkup Recommended",
                    "Consult a doctor for proper evaluation and avoid ignoring symptoms.",
                    "General Physician", "Moderate");
        }

        return new Rule(best.name, best.treatment, best.doctor, best.severity);
    }
}