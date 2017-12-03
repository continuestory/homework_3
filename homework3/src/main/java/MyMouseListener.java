import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import util.WordCloudBuilder;
import vo.StockInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class MyMouseListener extends java.awt.event.MouseAdapter {
    private static boolean flag=false;//用来判断是否已经执行双击事件
    private static int clickNum=0;//用来判断是否该执行双击事件
    private JLabel label;
    private StockInfo stock;
    private List<String> keyWords;
    public MyMouseListener(StockInfo oStock,List<String> list){
       this.stock = oStock;
       keyWords = list;
    }

    public void mouseClicked(MouseEvent e) {
        final MouseEvent me=e;//事件源

        this.flag=false;//每次点击鼠标初始化双击事件执行标志为false

        if (this.clickNum == 1) {//当clickNum==1时执行双击事件
            this.mouseDoubleClicked(me,stock);//执行双击事件
            this.clickNum=0;//初始化双击事件执行标志为0
            this.flag=true;//双击事件已执行,事件标志为true
            return;
        }

        //定义定时器
        java.util.Timer timer=new java.util.Timer();

        //定时器开始执行,延时0.2秒后确定是否执行单击事件
        timer.schedule(new java.util.TimerTask() {
            private int n=0;//记录定时器执行次数
            public void run() {
                if(MyMouseListener.flag){//如果双击事件已经执行,那么直接取消单击执行
                    n=0;
                    MyMouseListener.clickNum=0;
                    this.cancel();
                    return;
                }
                if (n == 1) {//定时器等待0.2秒后,双击事件仍未发生,执行单击事件
                    mouseSingleClicked(me);//执行单击事件
                    MyMouseListener.flag = true;
                    MyMouseListener.clickNum=0;
                    n=0;
                    this.cancel();
                    return;
                }
                clickNum++;
                n++;
            }
        },new java.util.Date(),500);
    }

    /**
     * 鼠标单击事件
     * @param e 事件源参数
     */
    public void mouseSingleClicked(MouseEvent e){
        System.out.println("Single Clicked!");
    }

    /**
     * 鼠标双击事件
     * @param e 事件源参数
     */
    public void mouseDoubleClicked(MouseEvent e,StockInfo stock){
        JFrame frameNB = new JFrame();
        frameNB.setLayout(null);

        Result result = ToAnalysis.parse(stock.getInformation(5)+stock.getInformation(6)+stock.getInformation(7));
        List<Term> terms = result.getTerms();
        List<String> sList = new LinkedList<String>();
        for(int i = 0;i<terms.size();i++){
            sList.add(terms.get(i).getName());
        }
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }

        WordCloudBuilder.buildWordCouldByWords(300,300,4,30,5,sList,new Color(-1),this.getClass().getClassLoader().getResource(".").getPath() + "mlgb.png",colors);

        String s = new String();

            String str[] = stock.getInformation(5).split(keyWords.get(0).toString());
            for(int i = 0; i< str.length-1;i++){
                s+=str[i]+"<font color =\"red\">"+keyWords.get(0).toString()+"</font>";
            }
            s+=str[str.length-1];
            for(int t= 1;t<keyWords.size();t++){
                String strt[] = s.split(keyWords.get(t).toString());
                s = "";
                for(int i = 0; i< strt.length-1;i++){
                    s+=strt[i]+"<font color =\"red\">"+keyWords.get(t).toString()+"</font>";
                }
                s+=strt[strt.length-1];
            }

        JLabel tempLabel = new JLabel("<html><body><p>"+s+"</p></body></html>");
        JLabel imgLabel = new JLabel();
        imgLabel.repaint();
        imgLabel.updateUI();
        imgLabel.setText("<html><img src=\""+this.getClass().getResource("mlgb.png")+"\"></html>");
        tempLabel.setSize(350,550);
        tempLabel.setLocation(new Point(25,20));
        imgLabel.setSize(200,200);
        imgLabel.setLocation(new Point(380,20));
        frameNB.add(tempLabel);
        frameNB.add(imgLabel);
        frameNB.setSize(800,600);
        frameNB.setVisible(true);
        System.out.println("Doublc Clicked!");
    }
}
