package commands;

import bg.tu_varna.sit.*;
import exceptions.DatabaseException;

public class Count implements commandsinterface.Count {
    private static commandsinterface.Count instance = null;

    private Count(){}

    public static commandsinterface.Count getInstance(){
        if(instance==null)
            instance = new Count();
        return instance;
    }

    private boolean match(Object columnValue, Object searchValue){
        if(columnValue == null){
            if(searchValue == null)
                return true;
            else
                return false;
        }else {
            return columnValue.equals(searchValue);
        }
    }

    @Override
    public int count(Database database, String tableName, String column, String value) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        int col;
        try {
            col = Integer.parseInt(column)-1;
        }catch (NumberFormatException e){
            throw new DatabaseException("incorrectly formatted INT");
        }
        if(col<0 || col>=table.getColumnCount())
            throw new DatabaseException("Column #"+(col+1)+" doesn't exist");
        ColumnType type = table.getColumnTypes().get(col);
        StringToType stringToType = new StringToType();
        Object searchValue = stringToType.getValue(type, value);
        Column searchColumn = table.getColumns().get(col);

        int count = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            if(match(searchValue, searchColumn.getValueAt(i))){
                count++;
            }
        }

        return count;
    }
}
