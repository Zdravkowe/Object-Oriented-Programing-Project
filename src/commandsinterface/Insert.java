package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Insert {
    void insert(Database database, String[] strings)throws DatabaseException;
}
