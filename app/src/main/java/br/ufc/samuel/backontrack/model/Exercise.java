package br.ufc.samuel.backontrack.model;
import com.orm.SugarRecord;
import java.util.List;

public class Exercise extends SugarRecord{
	private transient Long id;
	private String title;
	private String description;
	private Midia midia;
	private List<Object> objects;

	public Exercise(){

	}
	public Exercise(String title){
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

}
