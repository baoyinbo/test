package com.baoyb.gittest.model;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;

/**
 * Created by baoyb on 2017/3/31.
 */

public class BybNBATrModel extends SectionEntity {
    private String time; // 03/30 07:00
    private String player1; //老鹰
    private String player2; //76人
    private String player1logo; //
    private String player2logo; //
    private String player1logobig;
    private String player2logobig;
    private String player1url;
    private String player2url;
    private String link1url;
    private String m_link1url;
    private String link2text;   //技术统计
    private String link2url;
    private String m_link2url;
    private String status;  //2
    private String score;   //99-92 || VS
    private String link1text;   //视频集锦

    public BybNBATrModel(boolean isHeader, String header) {
        super(isHeader, header);

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getPlayer1logo() {
        return player1logo;
    }

    public void setPlayer1logo(String player1logo) {
        this.player1logo = player1logo;
    }

    public String getPlayer2logo() {
        return player2logo;
    }

    public void setPlayer2logo(String player2logo) {
        this.player2logo = player2logo;
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

    public String getLink1url() {
        return link1url;
    }

    public void setLink1url(String link1url) {
        this.link1url = link1url;
    }

    public String getM_link1url() {
        return m_link1url;
    }

    public void setM_link1url(String m_link1url) {
        this.m_link1url = m_link1url;
    }

    public String getLink2text() {
        return link2text;
    }

    public void setLink2text(String link2text) {
        this.link2text = link2text;
    }

    public String getLink2url() {
        return link2url;
    }

    public void setLink2url(String link2url) {
        this.link2url = link2url;
    }

    public String getM_link2url() {
        return m_link2url;
    }

    public void setM_link2url(String m_link2url) {
        this.m_link2url = m_link2url;
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

    public String getLink1text() {
        return link1text;
    }

    public void setLink1text(String link1text) {
        this.link1text = link1text;
    }

}
