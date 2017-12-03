package util;
import vo.StockInfo;

import java.io.File;
import java.util.List;

public interface FileHandler {
    StockInfo[] getStockInfoFromFile(String filePath);

}
