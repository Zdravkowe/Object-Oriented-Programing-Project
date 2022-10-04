package commands;

import bg.tu_varna.sit.ColumnType;
import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

import java.util.List;

public class Describe implements commandsinterface.Describe {
    private static commandsinterface.Describe instance = null;

    private Describe(){}

    public static commandsinterface.Describe getInstance(){
        if(instance==null)
            instance = new Describe();
        return instance;
    }

    @Override
    public void describe(Database database, String tableName) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        List<ColumnType> types = table.getColumnTypes();
        List<String> names = table.getColumnNames();
        for (int i = 0; i < table.getColumnCount(); i++) {
            System.out.println((i+1)+". "+names.get(i)+":"+types.get(i));
        }

    }
}
