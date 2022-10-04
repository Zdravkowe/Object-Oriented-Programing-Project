package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Close {
    void close(Database database);
}
