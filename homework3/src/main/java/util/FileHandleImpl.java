package util;

import javafx.util.Pair;
import vo.StockInfo;


import java.io.*;

public class FileHandleImpl implements FileHandler{
    public StockInfo[] getStockInfoFromFile(String filePath) {
        StockInfo[] dataStock = new StockInfo[61];

        try {
            BufferedReader myBuff = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
//            BufferedReader myBuff = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            String s = null;
            for(int a = 0;a<61;a++){
                dataStock[a] = new StockInfo();
            }
            int i = 0;
            while((s = myBuff.readLine())!= null){
                String tempS[] = s.split("\t");
                for(int d = 0; d<tempS.length;d++){
                    if(tempS[d].equals(" ")){
                        dataStock[i].setInformation(d,"nothing");
                    }else{
                        dataStock[i].setInformation(d,tempS[d]);
                    }
                    System.out.println(dataStock[i].getInformation(d)+"\t");
                }
                System.out.println();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataStock;
    }

}
