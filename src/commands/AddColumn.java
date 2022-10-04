package commands;

import bg.tu_varna.sit.ColumnType;
import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.StringToType;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class AddColumn implements commandsinterface.AddColumn {
    private static commandsinterface.AddColumn instance = null;

    private AddColumn(){}

    public static commandsinterface.AddColumn getInstance(){
        if(instance==null)
            instance = new AddColumn();
        return instance;
    }

    @Override
    public void addColumn(Database database, String tableName, String columnName, String type) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        StringToType stringToType = new StringToType();
        ColumnType columnType = stringToType.getType(type);

        if(table == null){
            List<String> columns = new ArrayList<>();
            List<ColumnType> types = new ArrayList<>();
            columns.add(columnName);
            types.add(columnType);
            database.getTables().add(new Table(tableName, columns, types));
        }else {
            table.addColumn(columnName, columnType);
        }
    }
}
