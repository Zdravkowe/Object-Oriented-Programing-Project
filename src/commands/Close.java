package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFileNames;
import exceptions.DatabaseException;

import java.util.ArrayList;

public class Close implements commandsinterface.Close {
    private static commandsinterface.Close instance = null;

    private Close(){}

    public static commandsinterface.Close getInstance(){
        if(instance==null)
            instance = new Close();
        return instance;
    }

    @Override
    public void close(Database database) {
        database.setName("");
        database.setDatabaseFileNames(null);
        database.setTables(new ArrayList<>());
    }
}
