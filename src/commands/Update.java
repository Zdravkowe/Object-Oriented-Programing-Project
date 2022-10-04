package commands;

import bg.tu_varna.sit.*;
import exceptions.DatabaseException;

public class Update implements commandsinterface.Update {
    private static commandsinterface.Update instance = null;

    private Update(){}

    public static commandsinterface.Update getInstance(){
        if(instance==null)
            instance = new Update();
        return instance;
    }

    @Override
    public int update(Database database, String tableName, String searchColumn, String searchValue, String targetColumn, String targetValue) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        int search = Integer.parseInt(searchColumn)-1;
        int target = Integer.parseInt(targetColumn)-1;

        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        if(search>= table.getColumnCount())
            throw new DatabaseException("column #"+(searchColumn+1)+" doesn't exist");
        if(target>= table.getColumnCount())
            throw new DatabaseException("column #"+(targetColumn+1)+" doesn't exist");

        StringToType stringToType = new StringToType();
        ColumnType typeS = table.getColumnTypes().get(search);
        ColumnType typeT = table.getColumnTypes().get(target);
        Object targetVal = stringToType.getValue(typeT, targetValue);
        Object searchVal = stringToType.getValue(typeS, searchValue);


        return update(table, table.getColumns().get(search),  searchVal, table.getColumns().get(target), targetVal);
    }

    private boolean match(Object columnValue, Object searchValue){
        if(columnValue == null){
            return searchValue == null;
        }else {
            return columnValue.equals(searchValue);
        }
    }

    private int update(Table table, Column search, Object searchValue, Column target, Object targetValue) {

        int updateCounter = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            Object value = search.getValueAt(i);
            if(match(value, searchValue)){
                updateCounter++;
                target.setValueAt(i, targetValue);
            }
        }

        return updateCounter;
    }


}
