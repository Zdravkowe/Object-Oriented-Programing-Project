package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFile;
import exceptions.DatabaseException;

public class Open implements commandsinterface.Open {

    private static commandsinterface.Open instance = null;

    private Open(){}

    public static commandsinterface.Open getInstance(){
        if(instance==null)
            instance = new Open();
        return instance;
    }

    @Override
    public void open(String fileName, Database database)  throws DatabaseException {
        DatabaseFile databaseFile = new fileaccess.DatabaseFile();
        try {
            databaseFile.loadFromFile(fileName.replace("\"", ""), database);
        }catch (ClassCastException e){
            throw new DatabaseException("opening table file (use 'import')");
        }
    }
}
