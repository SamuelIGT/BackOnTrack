package br.ufc.samuel.backontrack.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Midia extends SugarRecord {

    private Long midiaId;
    private String pathVideo;
    private String title;

    public Midia(){}

    public Midia(String pathVideo, String title){
        this.pathVideo = pathVideo;
        this.title = title;
    }

    public Long getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(Long midiaId) {
        this.midiaId = midiaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
