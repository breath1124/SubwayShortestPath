package util;

import model.Station;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadData {

    public void readFileByLines(String fileName, List<Station> stations) {
        BufferedReader reader = null;
        File file = new File(fileName);
//        List<List<String>> lists = new ArrayList<>();


        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            int line = 1;
            int cnt = 0;
            while ((str = reader.readLine()) != null) {
                Station station = new Station();
                List<String> list = new ArrayList<String>();
                String[] arr = str.split(" ");
                for(int i = 0; i < arr.length; i++)
                    list.add(arr[i]);

                String num = list.get(0);
                list.remove(0);

                station.setStationNum(num);
                station.setStations(list);
                stations.add(station);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader == null)
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
    }

}
