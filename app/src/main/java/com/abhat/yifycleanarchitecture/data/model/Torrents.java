package com.abhat.yifycleanarchitecture.data.model;

import java.util.Date;

/**
 * Created by Anirudh Uppunda on 3/9/17.
 */

public class Torrents {
    private String url;
    private String hash;
    private int seeds;
    private int peers;
    private String quality;

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    private String size;
    private double size_bytes;
    private String date_uploaded;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getSize_bytes() {
        return size_bytes;
    }

    public void setSize_bytes(double size_bytes) {
        this.size_bytes = size_bytes;
    }

    public String getDate_uploaded() {
        return date_uploaded;
    }

    public void setDate_uploaded(String date_uploaded) {
        this.date_uploaded = date_uploaded;
    }
}
