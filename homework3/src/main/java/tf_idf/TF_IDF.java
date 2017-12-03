package tf_idf;
import javafx.util.Pair;
import vo.StockInfo;

import java.util.List;

public interface TF_IDF {
    List<String> getsameWordsList();
    Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos);
    Pair<Integer,Double>[] CompareSentence(StockInfo[] stocks,Pair[] keyWords,String s);
}
