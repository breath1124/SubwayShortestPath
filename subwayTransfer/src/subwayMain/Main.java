package subwayMain;

import ui.*;

public class Main {
    public static void main(String[] args) {
        new FrmMain();
//        Scanner in = new Scanner(System.in);
//
//        List<Station> stations = new ArrayList<>();
//        List<String> list1 = new ArrayList<>();             //存储经过单个站点的地铁线的名字，以列表储存
//        List<List<String>> lists = new ArrayList<>();       //存储经过所有站点的地铁线的名字，将list1依次添加进lists中
//        List<Integer> passStation = new ArrayList<>();      //储存经过站点在数组中的下标
//
//        //读取数据
//        ReadData readData = new ReadData();
//        readData.readFileByLines("F:\\IntelliJ IDEA 2020.2.1\\subway\\subwayTransfer\\src\\data.txt",stations);
//
//
//        //存储所有站点名字
//        List<String> stationName = new ArrayList<String>();
//        for(int i = 0; i < stations.size(); i++){
//            for(int j = 0; j < stations.get(i).getStations().size(); j++){
//                String name = stations.get(i).getStations().get(j);
//                if(!stationName.contains(name))
//                    stationName.add(name);
//            }
//        }
//
//		System.out.println(stationName.size());
//
//        //将所有站点初始化到邻接矩阵中
//		SubwayLine graph = new SubwayLine(stationName);
//
//
//        //初始化各个站点之间的距离（默认相邻站点间距离为1）
//		for(int i = 0; i < stations.size(); i++){
//		    for(int j = 0; j < stations.get(i).getStations().size()-1; j++)
//		        graph.relation(stations.get(i).getStations().get(j), stations.get(i).getStations().get(j+1));
//        }
//
//        //求最短路径
//        Floyd floyd = new Floyd(graph.getSubwayMatrix());
//
//		System.out.print("请输入起始站点：");
//		String start = in.nextLine();
//        int startSub = graph.getSite(start);
//		System.out.print("请输入目标站点：");
//		String stop = in.nextLine();
//        int endSub = graph.getSite(stop);
//
//        //求经过站点数（最小距离）
//        int minDis = floyd.SearchMin(startSub, endSub);
//        minDis += 1;
//        System.out.println("最少经过站点为："+minDis+" 个（包含起点与终点）");
//
//        //存储最短路径到passStation中
//        passStation = floyd.printPath(startSub, endSub);
//
//        for(int i = 0; i < passStation.size(); i++){
//            List<String> list = new ArrayList<>();
//
//            //打印出该站点以及经过该站点的所有线路
//            System.out.print(graph.getName(passStation.get(i))+" ( ");
//            for(Station station: stations){
//                int flag = 0;
//                for(String name: station.getStations()){
//                    if(graph.getName(passStation.get(i)).equals(name)) {
//                        System.out.print(station.getStationNum() + " ");
//                        list.add(station.getStationNum());
//                        if(!list1.contains(name)) {
//                            list1.add(name);
//                            flag = 1;
//                        }
//                    }
//                }
//                if(flag == 1)
//                    lists.add(list);
//            }
//            System.out.println(")");
//        }
//
//        //存储换乘站点
//        List<String> transfer = new ArrayList<>();
//
//        //求换乘站点
//        for(int i = 2; i < lists.size(); i++){
//            int flag = 0;
//            for(int j = 0; j < lists.get(i).size(); j++){
//                for(int k = 0; k < lists.get(i-2).size(); k++){
//                    if(lists.get(i-2).get(k).equals(lists.get(i).get(j))){
//                        flag = 1;
//                        break;
//                    }
//                }
//            }
//            if(flag == 0)
//                if(!transfer.contains(list1.get(i-1))) {
//                    transfer.add(list1.get(i - 1));
//                }
//        }
//
//
//        //打印换乘站点
//        System.out.println("需要换乘 "+transfer.size()+" 个站点：");
//        for(int i = 0; i < transfer.size(); i++)
//            System.out.println(transfer.get(i));


//		if (!graph.vertex.contains(start) || !graph.vertex.contains(stop)) {
//			System.out.println("地图中不包含该站点！");
//			return;
//		}


    }

}
