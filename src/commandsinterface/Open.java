package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Open {
    void open(String fileName, Database database) throws DatabaseException;
}
