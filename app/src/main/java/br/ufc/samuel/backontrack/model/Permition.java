package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Permition extends SugarRecord{
    @Ignore
    private transient Long id;
    private boolean isUnlocked = false;
    private Grasp grasp;
    private Patient patient;

    public Permition(){}

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

    @Override
    public long save() {
        grasp.save();
        patient.save();
        return super.save();
    }
}
