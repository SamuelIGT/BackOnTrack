package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report extends SugarRecord{

	private Long reportId;
	private Permition permition;
	private String status;
	private int time;
	private int effortLevel;
	private String date;
	private String message;
	private int sets;
	private int repetitions; //TODO:Remover esse atributo no servidor.

	public Report(){

	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Permition getPermition() { return permition; }

	public void setPermition(Permition permition) {
		this.permition = permition;
	}

	public String getStatus() { return status; }

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getEffortLevel() {
		return effortLevel;
	}

	public void setEffortLevel(int effortLevel) {
		this.effortLevel = effortLevel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
		String dateString = ""+date.getTime();
		this.date = dateString;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSets() { return sets; }

	public void setSets(int sets) { this.sets = sets; }

	public int getRepetitions() { return repetitions; }

	public void setRepetitions(int repetitions) { this.repetitions = repetitions; }

    @Override
    public long save() {
        permition.save();
	    return super.save();
    }
}
