import javax.swing.*;

import javafx.util.Pair;
import vo.StockInfo;

import java.awt.*;
import java.util.List;

public class Recommender extends JFrame {

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel labelImg;
    private JPanel panel1;


    public Recommender(StockInfo[] stocks, Pair<Integer,Double>[] Marmix, List<String> list){
        super("Recommender");
        setLayout(null);
        setBounds(0,0,750,500);
        label1 = new JLabel(Marmix[0].getKey()+"  "+stocks[Marmix[0].getKey()].getInformation(5));
        label2 = new JLabel(Marmix[1].getKey()+stocks[Marmix[1].getKey()].getInformation(5));
        label3 = new JLabel(Marmix[2].getKey()+"  "+stocks[Marmix[2].getKey()].getInformation(5));
        label4 = new JLabel(Marmix[3].getKey()+"  "+stocks[Marmix[3].getKey()].getInformation(5));
        label5 = new JLabel(Marmix[4].getKey()+"  "+stocks[Marmix[4].getKey()].getInformation(5));
        label6 = new JLabel(Marmix[5].getKey()+"  "+stocks[Marmix[5].getKey()].getInformation(5));
        label7 = new JLabel(Marmix[6].getKey()+"  "+stocks[Marmix[6].getKey()].getInformation(5));
        label8 = new JLabel(Marmix[7].getKey()+"  "+stocks[Marmix[7].getKey()].getInformation(5));
        label9 = new JLabel(Marmix[8].getKey()+"  "+stocks[Marmix[8].getKey()].getInformation(5));
        label10 = new JLabel(Marmix[9].getKey()+"  "+stocks[Marmix[9].getKey()].getInformation(5));
        labelImg = new JLabel();
        label1.setSize((int)(this.getWidth()*0.6),40);
        label2.setSize((int)(this.getWidth()*0.6),40);
        label3.setSize((int)(this.getWidth()*0.6),40);
        label4.setSize((int)(this.getWidth()*0.6),40);;
        label5.setSize((int)(this.getWidth()*0.6),40);
        label6.setSize((int)(this.getWidth()*0.6),40);
        label7.setSize((int)(this.getWidth()*0.6),40);
        label8.setSize((int)(this.getWidth()*0.6),40);
        label9.setSize((int)(this.getWidth()*0.6),40);
        labelImg.setSize(200,200);
        labelImg.setIcon(new ImageIcon(this.getClass().getResource("data.png")));
        label1.setLocation(new Point(10,20));
        label2.setLocation(new Point(10,60));
        label3.setLocation(new Point(10,100));
        label4.setLocation(new Point(10,140));
        label5.setLocation(new Point(10,180));
        label6.setLocation(new Point(10,220));
        label7.setLocation(new Point(10,260));
        label8.setLocation(new Point(10,300));
        label9.setLocation(new Point(10,340));
        label10.setLocation(new Point(10,380));
        labelImg.setLocation(new Point(530,50));
        label1.addMouseListener(new MyMouseListener(stocks[Marmix[0].getKey()],list));
        label2.addMouseListener(new MyMouseListener(stocks[Marmix[1].getKey()],list));
        label3.addMouseListener(new MyMouseListener(stocks[Marmix[2].getKey()],list));
        label4.addMouseListener(new MyMouseListener(stocks[Marmix[3].getKey()],list));
        label5.addMouseListener(new MyMouseListener(stocks[Marmix[4].getKey()],list));
        label6.addMouseListener(new MyMouseListener(stocks[Marmix[5].getKey()],list));
        label7.addMouseListener(new MyMouseListener(stocks[Marmix[6].getKey()],list));
        label8.addMouseListener(new MyMouseListener(stocks[Marmix[7].getKey()],list));
        label9.addMouseListener(new MyMouseListener(stocks[Marmix[8].getKey()],list));
        label10.addMouseListener(new MyMouseListener(stocks[Marmix[9].getKey()],list));
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        add(label10);
        add(labelImg);

    }
}
