package commands;

import bg.tu_varna.sit.*;
import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;

public class Delete implements commandsinterface.Delete {
    private static commandsinterface.Delete instance = null;

    private Delete(){}

    public static commandsinterface.Delete getInstance(){
        if(instance==null)
            instance = new Delete();
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
    public int delete(Database database, String tableName, String column, String value) throws DatabaseException {
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

        List<Column> columns = new ArrayList<>();
        for (Column i: table.getColumns()) {
            columns.add(new TableColumn(i.getName(), i.getType()));
        }

        int deleteCount = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            if(match(searchValue, searchColumn.getValueAt(i))){
                deleteCount++;
            }else {
                for (int j=0; j< table.getColumnCount(); j++) {
                    columns.get(j).append(table.getRow(i).getValue(j));
                }

            }
        }
        table.setColumns(columns);
        return deleteCount;
    }
}
