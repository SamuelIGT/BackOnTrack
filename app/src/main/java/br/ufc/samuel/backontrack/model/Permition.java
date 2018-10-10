package br.ufc.samuel.backontrack.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Permition extends SugarRecord{

    private Long permitionId;
    private boolean isUnlocked = false;
    private Grasp grasp;
    private Patient patient;

    public Permition(){}

    public Permition(Long permitionId, boolean isUnlocked, Grasp grasp, Patient patient) {
        this.permitionId = permitionId;
        this.isUnlocked = isUnlocked;
        this.grasp = grasp;
        this.patient = patient;
    }

    @SerializedName("id")
    public Long getPermitionId() {
        return permitionId;
    }

    @SerializedName("id")
    public void setPermitionId(Long permitionId) {
        this.permitionId = permitionId;
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

    @Override
    public long save() {
        grasp.save();
        patient.save();
        return super.save();
    }
}
