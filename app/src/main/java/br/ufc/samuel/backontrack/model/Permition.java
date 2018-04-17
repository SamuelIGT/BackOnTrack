package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

public class Permition extends SugarRecord{
    private Long id;
    private boolean isUnlocked = false;
    private Grasp grasp;
    private Patient patient;

    public Permition(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public Grasp getGrasp() {
        return grasp;
    }

    public void setGrasp(Grasp grasp) {
        this.grasp = grasp;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
