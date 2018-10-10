package br.ufc.samuel.backontrack.model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Exercise extends SugarRecord{
    //@Ignore
    private /*transient*/ Long exerciseId;
	private String title;
	private String description;
	private Midia midia;

	@Ignore
	private List<Object> objects;
	private String serializedObjectsList;

	public Exercise(){

	}
	public Exercise(String title){
		this.title = title;
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

	public void setExerciseId(Long id) {
		this.exerciseId = id;
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public List<Object> getObjects() {
		if(serializedObjectsList != null){
		    new Gson().fromJson(serializedObjectsList, new TypeToken<List<Object>>(){}.getType());
        }
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

    @Override
    public long save() {
        serializedObjectsList = new Gson().toJson(objects);
        midia.save();
	    return super.save();
    }
}
