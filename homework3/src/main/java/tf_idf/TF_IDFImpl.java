package tf_idf;

import javafx.util.Pair;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import util.WordCloudBuilder;
import vo.StockInfo;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TF_IDFImpl implements TF_IDF {
    private List<String> sameWordsList;

    public String getsameWordsList(int index) {
        return sameWordsList.get(index).toString();
    }

    public List<String> getsameWordsList() {
        return sameWordsList;
    }

    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {
        Set<String> set = new LinkedHashSet<String>();
        set.addAll(words);
        int count = set.size();
        int all = words.size();
        Pair<String,Double> pairs[] = new Pair[count];
        Iterator setIt = set.iterator();
//        Iterator wordIt = words.iterator();
        int i = 0;
        while(setIt.hasNext()){
            Iterator wordIt = words.iterator();
            String temp = setIt.next().toString();
            double elementCount = 0;
            while(wordIt.hasNext()){
                String tempW = wordIt.next().toString();
                if(temp.equals(tempW)){
                    elementCount++;
//                    System.out.println(temp);
                }
            }
            pairs[i] = new Pair<String, Double>(temp,elementCount/all);
            i++;
        }


        for(int a = 0; a<pairs.length - 1; a++){
            for(int b = 0;b<pairs.length-1-a;b++){
                if((pairs[b].getValue()<pairs[b+1].getValue())){
                    Pair<String,Double> temp = pairs[b];
                    pairs[b] = pairs[b+1];
                    pairs[b+1] = temp;
                }
            }
        }
//        Pair<String,Double>[] keyWords = new Pair[20];
//        for(int a = 0;a<20;a++){
//            keyWords[a] = new Pair<String, Double>(pairs[a].getKey(),pairs[a].getValue());
//        }

        return pairs;
    }

    public Pair<Integer,Double>[] CompareSentence(StockInfo[] stocks, Pair[] pairs, String s) {
        Result result = ToAnalysis.parse(s);
        List<String> searchList = new LinkedList<String>();
        sameWordsList = new ArrayList<String>();
        List<Term> termList = result.getTerms();
        for(int j = 0; j<termList.size();j++){
            searchList.add(termList.get(j).getName());
        }
        int searchKey[] = new int[pairs.length];

        for(int j = 0;j<pairs.length;j++){
            int elementCount = 0;
            Iterator searchNext = searchList.iterator();
            while(searchNext.hasNext()){
                String tempS = searchNext.next().toString();
                if(pairs[j].getKey().equals(tempS)){
                    elementCount++;
                    sameWordsList.add(tempS);
                }
            }
            searchKey[j]= elementCount;
        }
        Pair<Integer,Double>[] Marmix = new Pair[60];
        for(int i = 0;i<60;i++){
            Result resultStock = ToAnalysis.parse(stocks[i+1].getInformation(7)+stocks[i+1].getInformation(5)+stocks[i+1].getInformation(6));
            int key[] = new int[pairs.length];
            int numerator = 0,demonator1 = 0,demonator2 =0;
            List<String> inputList = new LinkedList<String>();
            List<Term> terms = resultStock.getTerms();
            for(int j = 0; j<terms.size();j++){
                inputList.add(terms.get(j).getName());
            }
            for(int k = 0;k<pairs.length;k++){
                int elementCount = 0;
                Iterator inputIt = inputList.iterator();
                while(inputIt.hasNext()){
                    if(pairs[k].getKey().equals(inputIt.next().toString())){
                        elementCount++;
                    }
                }
                key[k] = elementCount;
            }

            for(int l = 0;l<pairs.length;l++){
               numerator +=searchKey[l]*key[l];
               demonator1 +=(searchKey[l]*searchKey[l]);
               demonator2 +=(key[l]*key[l]);
            }
            double d = numerator/(Math.sqrt(demonator1*demonator2));
            Marmix[i] = new Pair<Integer, Double>(i,d);
        }

        for(int a = 0;a<Marmix.length-1;a++){
            for(int b = 0; b<Marmix.length-1-a; b++){
                if(Marmix[b].getValue()<Marmix[b+1].getValue()){
                    Pair<Integer,Double>temp = Marmix[b];
                    Marmix[b] = Marmix[b+1];
                    Marmix[b+1] = temp;
                }
            }
        }
        Color[] colors = new Color[10];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new Color(
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128,
                    (new Double(Math.random() * 128)).intValue() + 128);
        }

        WordCloudBuilder.buildWordCouldByWords(200,200,4,30,5,sameWordsList,new Color(-1),this.getClass().getClassLoader().getResource(".").getPath() + "data.png",colors);
        System.out.println("mlgb");
        return Marmix;
    }
}
