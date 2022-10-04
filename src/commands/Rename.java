package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

public class Rename implements commandsinterface.Rename {
    private static commandsinterface.Rename instance = null;

    private Rename(){}

    public static commandsinterface.Rename getInstance(){
        if(instance==null)
            instance = new Rename();
        return instance;
    }

    @Override
    public void rename(Database database, String oldName, String newName) throws DatabaseException {
        if(database.getTableByName(newName) == null){
            Table table = database.getTableByName(oldName);
            if(table == null)
                throw new DatabaseException("table "+oldName+" doesn't exist");
            table.setName(newName);
        }else{
            throw new DatabaseException("table with name "+newName+" already exists");
        }
    }
}
