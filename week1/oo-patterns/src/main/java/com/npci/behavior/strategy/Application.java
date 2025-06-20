package com.npci.behavior.strategy;

import java.util.ArrayList;
import java.util.List;

class Track {
    private int id;
    private String title;
    private double duration;

    public Track(int id, String title, double duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}

interface PlaylistStrategy {
    Track getNextTrack(List<Track> tracks);
}

class SDFO implements PlaylistStrategy {
    @Override
    public Track getNextTrack(List<Track> tracks) {
        //..
        return null;
    }
}

class LDFO implements PlaylistStrategy {
    @Override
    public Track getNextTrack(List<Track> tracks) {
        //...
        return null;
    }
}

class PlayList {
    List<Track> tracks = new ArrayList<>();
    PlaylistStrategy strategy;
    public void setStrategy(PlaylistStrategy strategy) {
        this.strategy = strategy;
    }
    public PlayList() {
        tracks.add(new Track(1, "Track-1", 4.0));
        tracks.add(new Track(2, "Track-2", 5.0));
        tracks.add(new Track(3, "Track-4", 6.0));
        tracks.add(new Track(4, "Track-3", 7.0));
    }
    public Track getNext() {
        return strategy.getNextTrack(tracks);
    }
}


public class Application {

    public static void main(String[] args) {

        PlayList playList = new PlayList();
        playList.setStrategy(new SDFO());
        System.out.println(playList.getNext());
        System.out.println(playList.getNext());
        playList.setStrategy(new LDFO());
        System.out.println(playList.getNext());
        System.out.println(playList.getNext());

    }

}
