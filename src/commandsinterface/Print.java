package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Print {
    void print(String tableName, Database database) throws DatabaseException;
}
