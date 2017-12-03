package segmenter;
import vo.StockInfo;
import java.util.List;
public interface ChineseSegmenter {
    List<String> getWordsFromInput(StockInfo[] stocks);
}
