package commands;

import bg.tu_varna.sit.Database;
import bg.tu_varna.sit.Table;

import java.util.List;

public class ShowTables implements commandsinterface.ShowTables {
    private static commandsinterface.ShowTables instance = null;

    private ShowTables(){}

    public static commandsinterface.ShowTables getInstance(){
        if(instance==null)
            instance = new ShowTables();
        return instance;
    }

    @Override
    public void showTables(Database database) {
        List<Table> tables = database.getTables();
        if(tables == null || tables.size()==0){
            System.out.println("No tables found");
        }else
            for (Table i:tables) {
                System.out.println(i.getName());
            }
    }
}
