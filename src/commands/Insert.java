package commands;

import bg.tu_varna.sit.*;
import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class Insert implements commandsinterface.Insert {
    private static commandsinterface.Insert instance = null;

    private Insert(){}

    public static commandsinterface.Insert getInstance(){
        if(instance==null)
            instance = new Insert();
        return instance;
    }

    @Override
    public void insert(Database database, String[] strings) throws DatabaseException {
        String tableName = strings[1];
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        List<ColumnType> types = table.getColumnTypes();
        if(types.size() != strings.length-2)
            throw new DatabaseException("Incorrect number of parameters");

        List<Object> data = new ArrayList<>();
        StringToType stringToType = new StringToType();
        for (int i = 2; i < strings.length; i++) {
            Object o;
            o = stringToType.getValue(types.get(i-2), strings[i]);

            data.add(o);
        }
        Row row = new Row(data, types);
        table.insert(row);
    }
}
