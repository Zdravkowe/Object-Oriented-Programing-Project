package bg.tu_varna.sit;

import exceptions.DatabaseException;

public interface DatabaseFile {
    void saveToFile(Database database) throws DatabaseException;
    void loadFromFile(String databaseName, Database database) throws DatabaseException;

    Table importFromFile(String fileName) throws DatabaseException;

    void exportToFile(String fileName, Table table) throws DatabaseException;
}
