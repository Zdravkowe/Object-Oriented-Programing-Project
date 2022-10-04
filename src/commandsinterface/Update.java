package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Update {
    int update(Database database, String tableName, String searchColumn, String searchValue, String targetColumn, String targetValue)
            throws DatabaseException;
}
