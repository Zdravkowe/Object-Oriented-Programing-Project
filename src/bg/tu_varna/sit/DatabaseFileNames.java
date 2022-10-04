package bg.tu_varna.sit;

import java.util.Map;


//имената на файловете съответстващи на таблиците
public class DatabaseFileNames {
    private Map<String,String> tableFileName;
    private String mainName;

    public DatabaseFileNames(Map<String, String> tableFileName) {
        this.tableFileName = tableFileName;
    }

    public DatabaseFileNames() {
    }

    public Map<String, String> getTableFileName() {
        return tableFileName;
    }

    public void setTableFileName(Map<String, String> tableFileName) {
        this.tableFileName = tableFileName;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }
}
