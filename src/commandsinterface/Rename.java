package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface Rename {
    void rename(Database database, String oldName, String newName) throws DatabaseException;
}
