package br.ufc.samuel.backontrack.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

public class Recommendation extends SugarRecord{
    @Ignore
    private transient Long id;
    @Ignore
    private List<Serie> serie; //TODO: Porque serie é uma lista?
    private String serializedSeries;

    public Recommendation(){
    }

    public List<Serie> getSerie() {
        return new Gson().fromJson(serializedSeries, new TypeToken<List<Serie>>(){}.getType());
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }

    @Override
    public long save() {
        serializedSeries = new Gson().toJson(serie);
        return super.save();
    }
}
