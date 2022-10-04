package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFile;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

public class Import implements commandsinterface.Import {
    private static commandsinterface.Import instance = null;

    private Import(){}

    public static commandsinterface.Import getInstance(){
        if(instance==null)
            instance = new Import();
        return instance;
    }

    @Override
    public void importTable(Database database, String fileName) throws DatabaseException {
        DatabaseFile databaseFile = new fileaccess.DatabaseFile();
        Table table = null;
        try {
            table = databaseFile.importFromFile(fileName);
        }catch (ClassCastException e){
            throw new DatabaseException("importing database file (use 'open')");
        }
        if(database.getTableByName(table.getName()) == null){
            database.insertTable(table);
        }else{
            throw new DatabaseException("table "+table.getName()+" is already loaded");
        }
    }
}
