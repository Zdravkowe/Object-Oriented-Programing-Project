package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFile;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

public class Export implements commandsinterface.Export {
    private static commandsinterface.Export instance = null;

    private Export(){}

    public static commandsinterface.Export getInstance(){
        if(instance==null)
            instance = new Export();
        return instance;
    }


    @Override
    public void export(Database database, String tableName, String fileName) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        DatabaseFile databaseFile = new fileaccess.DatabaseFile();
        databaseFile.exportToFile(fileName, table);
    }
}
