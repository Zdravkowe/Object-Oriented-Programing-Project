package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Count {
    int count(Database database, String tableName, String column, String value) throws DatabaseException;
}
