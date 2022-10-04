package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface AddColumn {
    void addColumn(Database database, String tableName, String columnName, String type) throws DatabaseException;
}
