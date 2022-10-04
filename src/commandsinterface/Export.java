package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Export {
    void export(Database database, String tableName, String fileName)throws DatabaseException;
}
