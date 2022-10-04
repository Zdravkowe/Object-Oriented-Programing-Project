package fileaccess;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.DatabaseFileNames;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//четене и запис на xml
public class DatabaseFile implements bg.tu_varna.sit.DatabaseFile {
    @Override
    public void saveToFile(Database database) throws DatabaseException {

        DatabaseFileNames databaseFileNames = database.getDatabaseFileNames();
        List<Table> tables = database.getTables();
        String mainFileName = database.getName()+".xml";
        Map<String, String> fileNames = databaseFileNames.getTableFileName();
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(mainFileName)));
            encoder.writeObject(databaseFileNames);
            encoder.close();
            for (Table i:tables) {
                encoder= new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileNames.get(i.getName()))));
                encoder.writeObject(i);
                encoder.close();
            }

        }catch (FileNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String databaseName, Database database) throws DatabaseException {
        DatabaseFileNames databaseFileNames;
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(databaseName)));
            databaseFileNames = (DatabaseFileNames) decoder.readObject();
            decoder.close();
            List<Table> tables = new ArrayList<>();
            for (Map.Entry<String, String> i : databaseFileNames.getTableFileName().entrySet()){
                decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(i.getValue())));
                Table table = (Table) decoder.readObject();
                tables.add(table);
                decoder.close();
            }
            database.setTables(tables);
            database.setDatabaseFileNames(databaseFileNames);
            database.setName(databaseFileNames.getMainName());

        }catch (FileNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Table importFromFile(String fileName) throws DatabaseException {
        try {
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
            Table table = (Table) decoder.readObject();
            decoder.close();
            return table;
        }catch (FileNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public void exportToFile(String fileName, Table table) throws DatabaseException {
        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
            encoder.writeObject(table);
            encoder.close();
        }catch (FileNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
