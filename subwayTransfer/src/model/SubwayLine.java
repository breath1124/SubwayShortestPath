package model;

import model.Station;

import java.util.ArrayList;
import java.util.List;

public class SubwayLine {
    private int[][] subwayMatrix;
    private List<String> stationName;
    private int[][] path;
    private static final int max = 99999;


    //初始化邻接矩阵
    public SubwayLine(List<String> stationName){
        this.stationName = stationName;
        this.subwayMatrix = new int[stationName.size()][stationName.size()];
        this.path = new int[stationName.size()][stationName.size()];
        for(int i = 0; i < stationName.size(); i++){
            for(int j = 0; j < stationName.size(); j++){
                if(i == j)
                    subwayMatrix[i][j] = 0;
                else {
                    subwayMatrix[i][j] = max;
                    subwayMatrix[j][i] = max;
                }
            }
        }
    }

    public int getSite(String station){
        return stationName.indexOf(station);
    }

    public String getName(int site){
        return stationName.get(site);
    }


    public String judgeEqualLine(int s1, int s2, List<Station> stations){
        String line = "";
        String start = getName(s1);
        String end = getName(s2);

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        for(Station station: stations){
            for(String name: station.getStations()){
                if(start.equals(name))
                    list1.add(station.getStationNum());
                if(end.equals(name))
                    list2.add(station.getStationNum());
            }
        }

        for(int i = 0; i < list1.size(); i++){
            for(int j = 0; j < list2.size(); j++){
                if(list1.get(i).equals(list2.get(j)))
                    line = list1.get(i);
            }
        }


        return line;
    }
//
//    public void print(int[][] subwayMatrix){
//        this.subwayMatrix = subwayMatrix;
//        int len = subwayMatrix.length;
//        for(int i = 0; i < 10; i++){
//            for(int j = 0; j < 10; j++) {
//                System.out.println(subwayMatrix[i][j]);
//                System.out.println(i);
//                System.out.println(j);
//                System.out.println(getName(i));
//                System.out.println(getName(j));
//                System.out.println(subwayMatrix[i][j]);
//            }
//        }
//    }

    //构造邻接矩阵
    public void relation(String start, String end){
        int i = getSite(start);
        int j = getSite(end);
//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(i);
//        System.out.println(j);
        this.subwayMatrix[i][j] = 1;
        this.subwayMatrix[j][i] = 1;
    }

    public int[][] getSubwayMatrix() {
        return subwayMatrix;
    }

    public void setSubwayMatrix(int[][] subwayMatrix) {
        this.subwayMatrix = subwayMatrix;
    }

    public List<String> getStationName() {
        return stationName;
    }

    public void setStationName(List<String> stationName) {
        this.stationName = stationName;
    }

    public int[][] getPath() {
        return path;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public static int getMax() {
        return max;
    }

}
