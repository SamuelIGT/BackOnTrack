package br.ufc.samuel.backontrack.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;

import static android.content.ContentValues.TAG;

public class Recommendation extends SugarRecord{

    private Long recomendationId;
    @Ignore
    private List<Serie> serie; //TODO: Porque serie é uma lista?
    private String serializedSeries;

    public Recommendation(){
    }

    public Long getRecomendationId() {
        return recomendationId;
    }

    public void setRecomendationId(Long recomendationId) {
        this.recomendationId = recomendationId;
    }

    public List<Serie> getSerie() {
        return new Gson().fromJson(serializedSeries, new TypeToken<List<Serie>>(){}.getType());
    }

    public String getSerializedSeries() {
        return serializedSeries;
    }

    public void setSerie(List<Serie> serie) {
        this.serie = serie;
    }

    @Override
    public long save() {
        serializedSeries = new Gson().toJson(serie);
        Log.d(TAG, "save: " + serializedSeries);
        return super.save();
    }
}
