package br.ufc.samuel.backontrack.model;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Responsible extends SugarRecord{

	private Long resposableId;
	private String email;
	private String password;
	private String name;
	
	public Responsible(){
		
	}
	
	public Responsible(String email, String password, String name){
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public Long getResposableId() {
		return resposableId;
	}

	public void setResposableId(Long resposableId) {
		this.resposableId = resposableId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
