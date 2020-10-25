package model;

import java.util.ArrayList;
import java.util.List;

public class Station {
//    private String name;
    private String stationNum;
    private List<String> stations = new ArrayList<>();

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }
//    private String lineChange;
}
