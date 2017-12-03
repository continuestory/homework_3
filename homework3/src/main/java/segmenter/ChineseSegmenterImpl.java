package segmenter;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import vo.StockInfo;

import java.util.LinkedList;
import java.util.List;

public class ChineseSegmenterImpl implements ChineseSegmenter {
    public List<String> getWordsFromInput(StockInfo[] stocks) {

        List<String> inputList = new LinkedList<String>();

        for(int i=0;i<stocks.length;i++){
            Result result = ToAnalysis.parse(stocks[i+1].getInformation(7)+stocks[i+1].getInformation(5)+stocks[i+1].getInformation(6));
            List<Term> terms = result.getTerms();
            for(int j = 0; j<terms.size();j++){
                inputList.add(terms.get(j).getName());
            }
        }
        System.out.println("mlgb");
        return inputList;
    }
}
