package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Select {
    void select(Database database, String searchColumn, String searchValue, String tableName) throws DatabaseException;
}
