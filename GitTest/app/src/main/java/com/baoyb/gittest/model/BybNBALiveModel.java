package com.baoyb.gittest.model;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBALiveModel implements Serializable {
    private String title;   //10:30开赛 第4节进行中
    private String player1;
    private String player2;
    private String player1info; //胜51负23西部3名
    private String player2info;

    private String player1logobig;
    private String player2logobig;
    private String player1url;
    private String player2url;
    private String player1location; //(客)
    private String player2location; //"(主)",
    private String status;  //1
    private String score;   //"94-99"
    private String liveurl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer1info() {
        return player1info;
    }

    public void setPlayer1info(String player1info) {
        this.player1info = player1info;
    }

    public String getPlayer2info() {
        return player2info;
    }

    public void setPlayer2info(String player2info) {
        this.player2info = player2info;
    }

    public String getPlayer1logobig() {
        return player1logobig;
    }

    public void setPlayer1logobig(String player1logobig) {
        this.player1logobig = player1logobig;
    }

    public String getPlayer2logobig() {
        return player2logobig;
    }

    public void setPlayer2logobig(String player2logobig) {
        this.player2logobig = player2logobig;
    }

    public String getPlayer1url() {
        return player1url;
    }

    public void setPlayer1url(String player1url) {
        this.player1url = player1url;
    }

    public String getPlayer2url() {
        return player2url;
    }

    public void setPlayer2url(String player2url) {
        this.player2url = player2url;
    }

    public String getPlayer1location() {
        return player1location;
    }

    public void setPlayer1location(String player1location) {
        this.player1location = player1location;
    }

    public String getPlayer2location() {
        return player2location;
    }

    public void setPlayer2location(String player2location) {
        this.player2location = player2location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLiveurl() {
        return liveurl;
    }

    public void setLiveurl(String liveurl) {
        this.liveurl = liveurl;
    }
}
