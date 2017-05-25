package com.example.gruppe3.myapplication.eventclasses;

import com.example.gruppe3.myapplication.eventclasses.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 25.05.2017.
 */

public class Points {

    private List<Point> pointList;

    public Points () {
        pointList = new ArrayList<>();

    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public void add(Point point) {
        pointList.add(point);
    }
}
