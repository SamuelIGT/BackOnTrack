package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;

public class Midia extends SugarRecord {
    private Long id;
    private String pathVideo;
    private String title;

    public Midia(){}

    public Midia(String pathVideo, String title){
        this.pathVideo = pathVideo;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
