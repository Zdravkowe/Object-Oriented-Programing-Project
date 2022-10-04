package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.Row;
import bg.tu_varna.sit.Table;
import exceptions.DatabaseException;

import java.util.Scanner;

public class Print implements commandsinterface.Print {
    private static commandsinterface.Print instance = null;
    private final int rowsPerPage = 5;

    private Print(){}

    public static commandsinterface.Print getInstance(){
        if(instance==null)
            instance = new Print();
        return instance;
    }

    private void printPage(int pageNum, Table table){
        Row row;
        System.out.println(table.namesAsString()+"\n");
        for (int i = pageNum*rowsPerPage; i < (pageNum+1)*rowsPerPage; i++) {
            row = table.getRow(i);
            if(row == null)break;
            System.out.println(row.toString());
        }
        System.out.println("Page #"+(pageNum+1));
        System.out.println("1.prev 2.next 3.exit");
    }

    @Override
    public void print(String tableName, Database database) throws DatabaseException {
        Table table = database.getTableByName(tableName);
        if(table == null)
            throw new DatabaseException("table "+tableName+" doesn't exist");

        int pageNum = 0;
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            printPage(pageNum, table);
            do {
                input = scanner.nextLine();
            }while (!(input.equals("1") || input.equals("2") || input.equals("3")));
            if(input.equals("1")) {
                if (pageNum > 0)
                    pageNum--;
            }
            else if(input.equals("2")){
                if((pageNum+1)*rowsPerPage < table.getRowCount())
                    pageNum++;
            }else break;
            System.out.println("\n");
        }while (true);
        System.out.println("closing dialog");
    }
}
