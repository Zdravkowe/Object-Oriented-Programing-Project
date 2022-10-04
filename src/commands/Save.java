package commands;

import commandsinterface.SaveAs;
import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFileNames;
import exceptions.DatabaseException;

public class Save implements commandsinterface.Save {
    private static commandsinterface.Save instance = null;

    private Save(){}

    public static commandsinterface.Save getInstance(){
        if(instance==null)
            instance = new Save();
        return instance;
    }

    @Override
    public void save(Database database) throws DatabaseException {
        DatabaseFileNames databaseFileNames = database.getDatabaseFileNames();
        if(databaseFileNames == null || databaseFileNames.getMainName() == null || databaseFileNames.getMainName().isEmpty())
            throw new DatabaseException("file name not set(use 'saveas')");
        SaveAs saveAs = commands.SaveAs.getInstance();
        saveAs.saveAs(database, databaseFileNames.getMainName()+".xml");
    }
}
