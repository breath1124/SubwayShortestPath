package model;


import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private static final int max = 99999;
    private int[][] path;
    private int[][] distance;


    //Floyd算法求最短路径
    public Floyd(int[][] Graph){
        this.path = new int[Graph.length][Graph.length];
        this.distance = new int[Graph.length][Graph.length];
        for(int i = 0; i < Graph.length; i++)
            for(int j = 0; j < Graph.length; j++) {
                this.path[i][j] = j;
                this.distance[i][j] = Graph[i][j];
                this.distance[j][i] = Graph[j][i];
            }


        for(int k = 0; k < Graph.length; k++)
            for(int i = 0; i < Graph.length; i++)
                for(int j = 0; j < Graph.length; j++)
                    if(this.distance[i][j] > this.distance[i][k] + this.distance[k][j]) {
                        this.distance[i][j] = this.distance[i][k] + this.distance[k][j];
                        this.path[i][j] = this.path[i][k];
                    }

    }

    //查找最小距离
    public int SearchMin(int i, int j){
        return this.distance[i][j];
    }

//    public void print(int[][] distance){
//        this.distance = distance;
//        int len = distance.length;
//        for(int i = 0; i < 10; i++){
//            for(int j = 0; j < len; j++) {
//                System.out.print(distance[i][j]+"  ");
//            }
//            System.out.println();
//        }
//    }

    public static int getMax() {
        return max;
    }

    public int[][] getPath() {
        return path;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public int[][] getDistance() {
        return distance;
    }

    public void setDistance(int[][] distance) {
        this.distance = distance;
    }


    //将最短距离的站点下标加入到列表中
    public List<Integer> printPath(int i, int j){
        List<Integer> list = new ArrayList<>();
        while(i != j){
            list.add(i);
            i = this.path[i][j];
//            list.add(i);
        }
        list.add(i);
        return list;
    }


}
