package commands;

import bg.tu_varna.sit.*;
import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Select implements commandsinterface.Select {
    private static commandsinterface.Select instance = null;
    private final int rowsPerPage = 5;

    private Select(){}

    public static commandsinterface.Select getInstance(){
        if(instance==null)
            instance = new Select();
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

    private void print(List<Row> rows, int page, String names){
        System.out.println(names+"\n");
        for (int i = page*rowsPerPage; i < (page+1)*rowsPerPage; i++) {
            if(i>=rows.size())
                break;
            System.out.println(rows.get(i));
        }
        System.out.println("Page #"+(page+1));
        System.out.println("1.prev 2.next 3.exit");
    }

    private void display(List<Row> rows, String names){
        System.out.println("Found "+rows.size()+" rows");
        int page = 0;
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            print(rows, page, names);
            do {
                input = scanner.nextLine();
            }while (!(input.equals("1") || input.equals("2") || input.equals("3")));
            if(input.equals("1")) {
                if (page > 0)
                    page--;
            }
            else if(input.equals("2")){
                if((page+1)*rowsPerPage < rows.size())
                    page++;
            }else break;

        }while (true);

    }

    @Override
    public void select(Database database, String searchColumn, String searchValue, String tableName) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");
        int col;
        try {
            col = Integer.parseInt(searchColumn)-1;
        }catch (NumberFormatException e){
            throw new DatabaseException("incorrectly formatted INT");
        }
        if(col<0 || col>=table.getColumnCount())
            throw new DatabaseException("Column #"+(col+1)+" doesn't exist");
        ColumnType type = table.getColumnTypes().get(col);
        StringToType stringToType = new StringToType();
        Object value = stringToType.getValue(type, searchValue);
        Column searchCol = table.getColumns().get(col);

        List<Row> rows = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            if(match(value, searchCol.getValueAt(i))){
                rows.add(table.getRow(i));
            }
        }
        if(rows.size()>=1)
            display(rows, table.namesAsString());
        else
            System.out.println("No rows found");
    }
}
