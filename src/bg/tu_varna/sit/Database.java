package bg.tu_varna.sit;

import java.util.List;

//базата данни съдържаща всички таблици
public class Database {
    private DatabaseFileNames databaseFileNames;
    private String name;
    private List<Table> tables;


    public Database(String name, List<Table> tables, DatabaseFileNames databaseFileNames) {
        this.name = name;
        this.tables = tables;
        this.databaseFileNames = databaseFileNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public DatabaseFileNames getDatabaseFileNames() {
        return databaseFileNames;
    }

    public void setDatabaseFileNames(DatabaseFileNames databaseFileNames) {
        this.databaseFileNames = databaseFileNames;
    }

    public Table getTableByName(String name){
        for (Table i: tables) {
            if(i.getName().equals(name))
                return i;
        }
        return null;
    }

    public void insertTable(Table table){
        tables.add(table);
    }

}
