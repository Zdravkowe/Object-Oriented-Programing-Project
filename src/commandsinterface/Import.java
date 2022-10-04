package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Import {
    void importTable(Database database, String fileName) throws DatabaseException;
}
