package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Save {
    void save(Database database) throws DatabaseException;
}
