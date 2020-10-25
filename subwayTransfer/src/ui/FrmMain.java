package ui;

import model.SubwayLine;
import model.Station;
import util.ReadData;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Floyd;

public class FrmMain extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JTextArea display = new JTextArea("");

    private JButton btnSearch = new JButton("查询");

    private JLabel labelStart = new JLabel("起点站 ：");
    private JLabel labelEnd = new JLabel("终点站：");
    private JLabel labelFinal = new JLabel();
    private JTextField edtStart = new JTextField(20);
    private JTextField edtEnd = new JTextField(20);

    private Object tblLineTitle[] = {"地铁站"};
    private Object tblLineData[][];
    DefaultTableModel tabLineModel = new DefaultTableModel();
    private JTable dataTableLine = new JTable(tabLineModel);

    private Object tblStationTitle[] = {"站点名"};
    private Object tblStationData[][];
    DefaultTableModel tabStationModel=new DefaultTableModel();
    private JTable dataTableStation=new JTable(tabStationModel);

    private List<Station> stations = new ArrayList<>();         //存储data.txt中的所有数据
    private List<String> list1 = new ArrayList<>();             //存储经过单个站点的地铁线的名字，以列表储存
    private List<List<String>> lists = new ArrayList<>();       //存储经过所有站点的地铁线的名字，将list1依次添加进lists中
    private List<Integer> passStation = new ArrayList<>();      //储存经过站点在数组中的下标

    private List<String> stationName = new ArrayList<String>();
    private List<Station> line = new ArrayList<Station>();
    private SubwayLine graph;
    private Floyd floyd;


    //打印所有地铁线路名称
    private void reloadSubwayLine(){

        tblLineData =  new Object[stations.size()][1];
        for(int i = 0; i < stations.size(); i++){
            for(int j = 0; j < 1; j++)
                tblLineData[i][j] = stations.get(i).getStationNum();
        }
        tabLineModel.setDataVector(tblLineData,tblLineTitle);
        this.dataTableLine.validate();
        this.dataTableLine.repaint();
    }

    //打印指定地铁线路里的所有站点名称
    private void reloadSubwayName(int LineNum){
        if(LineNum < 0)
            return;
        stationName = stations.get(LineNum).getStations();

        tblStationData = new Object[stationName.size()][1];
        for(int i = 0; i < stationName.size(); i++)
            for(int j = 0; j < 1; j++)
                tblStationData[i][j] = stationName.get(i);

        tabStationModel.setDataVector(tblStationData,tblStationTitle);
        this.dataTableStation.validate();
        this.dataTableStation.repaint();
    }

    public FrmMain() {
        //读取数据到stations中
        ReadData readData = new ReadData();
        readData.readFileByLines("F:\\IntelliJ IDEA 2020.2.1\\subway\\subwayTransfer\\src\\data.txt",stations);

        workPane.add(labelStart);
        workPane.add(edtStart);
        workPane.add(labelEnd);
        workPane.add(edtEnd);
        workPane.add(btnSearch);
        this.getContentPane().add(workPane, BorderLayout.NORTH);

        display.add(labelFinal);
        this.getContentPane().add(display, BorderLayout.CENTER);

        this.getContentPane().add(new JScrollPane(this.dataTableLine), BorderLayout.WEST);

        this.dataTableLine.addMouseListener(new MouseAdapter (){

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmMain.this.dataTableLine.getSelectedRow();
                if(i < 0) {
                    return;
                }
                FrmMain.this.reloadSubwayName(i);
            }

        });

        this.getContentPane().add(new JScrollPane(this.dataTableStation), BorderLayout.EAST);
        this.reloadSubwayLine();


        this.setSize(1600, 800);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.btnSearch.addActionListener(this);
        this.validate();

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        this.setVisible(true);


        for(int i = 0; i < stations.size(); i++){
            for(int j = 0; j < stations.get(i).getStations().size(); j++){
                String name = stations.get(i).getStations().get(j);
                if(!stationName.contains(name))
                    stationName.add(name);
            }
        }


        //将所有站点初始化到邻接矩阵中
		graph = new SubwayLine(stationName);


        //初始化各个站点之间的距离（默认相邻站点间距离为1）
		for(int i = 0; i < stations.size(); i++){
		    for(int j = 0; j < stations.get(i).getStations().size()-1; j++)
		        graph.relation(stations.get(i).getStations().get(j), stations.get(i).getStations().get(j+1));
        }

        //求最短路径
        floyd = new Floyd(graph.getSubwayMatrix());
    }


    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String start = edtStart.getText();
        String end = edtEnd.getText();

        if (e.getSource() == this.btnSearch) {
            //每次查询前将上次查询的结果清空
            display.setText("");

            //处理各种输入有误的情况
            if(start.equals("") || end.equals(""))
                try{
                    throw new Exception();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "请输入站点！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            else if(start.equals(end))
                try{
                    throw new Exception();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "起点站与终点站相同！","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            else if(!stationName.contains(start))
                try{
                    throw new Exception();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "地图中不包括该起点站，请检查输入！","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            else if(!stationName.contains(end))
                try{
                    throw new Exception();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "地图中不包括该终点站，请检查输入！","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }

            int startSub = graph.getSite(start);
            int endSub = graph.getSite(end);

            //求经过站点数（最小距离）
            int minDis = floyd.SearchMin(startSub, endSub);

            //处理两站点无法通过地铁到达的情况
            if(minDis == 99999)
                try{
                    throw new Exception();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "两地铁之间无法通过换乘到达！","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }

            minDis += 1;
            display.append(start +" 到 "+end+" 最少经过站点为："+minDis+" 个（包含起点与终点）\n");

            //存储最短路径到passStation中
            passStation = floyd.printPath(startSub, endSub);

            for(int i = 0; i < passStation.size(); i++){
                List<String> list = new ArrayList<>();
                //打印出该站点以及经过该站点的所有线路
                display.append(graph.getName(passStation.get(i))+" ( ");
                for(Station station: stations){
                    int flag = 0;
                    for(String name: station.getStations()){
                        if(graph.getName(passStation.get(i)).equals(name)) {
                            display.append(station.getStationNum() + " ");
                            list.add(station.getStationNum());
                            if(!list1.contains(name)) {
                                list1.add(name);
                                flag = 1;
                            }
                        }
                    }
                    if(flag == 1)
                        lists.add(list);
                }
                display.append(")");
                display.append("\n");
            }

            //存储换乘站点
            List<String> transfer = new ArrayList<>();

            //求换乘站点
            for(int i = 2; i < lists.size(); i++){
                int flag = 0;
                for(int j = 0; j < lists.get(i).size(); j++){
                    for(int k = 0; k < lists.get(i-2).size(); k++){
                        if(lists.get(i-2).get(k).equals(lists.get(i).get(j))){
                            flag = 1;
                            break;
                        }
                    }
                }
                if(flag == 0)
                    if(!transfer.contains(list1.get(i-1))) {
                        transfer.add(list1.get(i - 1));
                    }
            }


            display.append("\n");
            //打印换乘站点
            display.append("需要换乘 "+transfer.size()+" 个站点：");
            for(int i = 0; i < transfer.size(); i++)
                display.append(transfer.get(i)+"  ");
            display.append("\n");
            this.repaint();
            this.validate();

        }
    }
}
