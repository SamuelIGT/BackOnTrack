package br.ufc.samuel.backontrack.model;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Patient extends SugarRecord{

	private Long patientId;
	private String registration;
	private String name;
	private String phone;
	private String parent;
	private String phoneParent;
	private int progress;
	private Responsible responsible;

	public Patient(){

	}
	
	public Patient(String registration, String name, String phone, String parent, String phoneParent){
		this.registration = registration;
		this.name = name;
		this.phone = phone;
		this.parent = parent;
		this.phoneParent = phoneParent;
		this.progress = 1;

	}

	@SerializedName("id")
	public Long getPatientId() {
		return patientId;
	}

	@SerializedName("id")
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPhoneParent() {
		return phoneParent;
	}

	public void setPhoneParent(String phoneParent) {
		this.phoneParent = phoneParent;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

    @Override
    public long save() {
		if(responsible != null)
        	responsible.save();
	    return super.save();
    }
}
