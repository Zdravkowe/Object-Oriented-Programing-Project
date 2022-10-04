package commandsinterface;

import bg.tu_varna.sit.Database;
import exceptions.DatabaseException;

public interface SaveAs {
    void saveAs(Database database, String name)throws DatabaseException;
}
