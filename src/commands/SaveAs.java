package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFile;
import bg.tu_varna.sit.DatabaseFileNames;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

import java.util.HashMap;
import java.util.Map;

public class SaveAs implements commandsinterface.SaveAs {
    private static commandsinterface.SaveAs instance = null;

    private SaveAs(){}

    public static commandsinterface.SaveAs getInstance(){
        if(instance==null)
            instance = new SaveAs();
        return instance;
    }

    @Override
    public void saveAs(Database database, String name) throws DatabaseException {
        name = name.replace("\"", "");
        String databaseName = name.split("\\.")[0];
        database.setName(databaseName);
        DatabaseFileNames databaseFileNames = new DatabaseFileNames();
        databaseFileNames.setMainName(databaseName);
        Map<String, String> names = new HashMap<>();
        for (Table i:database.getTables()) {
            names.put(i.getName(), databaseName+"_"+i.getName()+".xml");
        }
        databaseFileNames.setTableFileName(names);
        database.setDatabaseFileNames(databaseFileNames);
        DatabaseFile databaseFile = new fileaccess.DatabaseFile();
        databaseFile.saveToFile(database);
    }
}
