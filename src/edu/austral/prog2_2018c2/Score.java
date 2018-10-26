package edu.austral.prog2_2018c2;

import java.io.Serializable;
import java.util.SplittableRandom;

public class Score {

    String name;
    int point;
    String date;

    public Score() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Serializable next) {

    }
}
