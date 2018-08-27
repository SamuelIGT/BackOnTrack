package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Midia extends SugarRecord {
    @Ignore
    private transient Long id;
    private String pathVideo;
    private String title;

    public Midia(){}

    public Midia(String pathVideo, String title){
        this.pathVideo = pathVideo;
        this.title = title;
    }

    public String getPathVideo() {
        return pathVideo;
    }

    public void setPathVideo(String pathVideo) {
        this.pathVideo = pathVideo;
    }

    public String getPathTitle() {
        return title;
    }

    public void setPathTitle(String pathAudio) {
        this.title = title;
    }
}
