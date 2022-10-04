package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Describe {
    void describe(Database database, String tableName) throws DatabaseException;
}
