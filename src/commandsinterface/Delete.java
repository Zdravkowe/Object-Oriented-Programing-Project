package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Delete {
    int delete(Database database, String tableName, String column, String value) throws DatabaseException;
}
