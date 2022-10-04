package bg.tu_varna.sit;

import commandsinterface.*;
import exceptions.DatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//команден ред
public class CLI {
    private static CLI instance=null;
    private Database database;
    private CLI(){
        database = new Database("",new ArrayList<>(), null);
    }
    public static CLI getInstance(){
        if(instance == null)
            instance = new CLI();
        return instance;
    }

    private void showTables(){
        ShowTables showTables = commands.ShowTables.getInstance();
        showTables.showTables(database);
    }

    private void addColumn(String[] strings) throws DatabaseException {
        if(strings.length!=4)
            throw new DatabaseException("Incorrect number of parameters");
        AddColumn addColumn = commands.AddColumn.getInstance();
        addColumn.addColumn(database, strings[1], strings[2], strings[3]);
        System.out.println("column added");
    }

    private void saveAs(String[] strings) throws DatabaseException{
        if(strings.length != 2)
            throw new DatabaseException("Incorrect number of parameters");
        SaveAs saveAs = commands.SaveAs.getInstance();
        saveAs.saveAs(database, strings[1].replace("\"",""));
        System.out.println("File saved");
    }

    public void save()throws DatabaseException{
        Save save = commands.Save.getInstance();
        save.save(database);
        System.out.println("File saved");
    }

    private void open(String[] strings)throws DatabaseException{
        if(strings.length != 2)
            throw new DatabaseException("Incorrect number of parameters");
        Open open = commands.Open.getInstance();
        open.open(strings[1], database);
        System.out.println("File opened");
    }

    private void close(){
        Close close = commands.Close.getInstance();
        close.close(database);
        System.out.println("File closed");
    }

    private void help(){
        Help help = commands.Help.getInstance();
        help.help();
    }

    private void insert(String[] strings) throws DatabaseException{
        if(strings.length < 3)
            throw new DatabaseException("Incorrect number of parameters");
        Insert insert = commands.Insert.getInstance();
        insert.insert(database, strings);
        System.out.println("Row inserted");
    }

    private void describe(String[] strings) throws DatabaseException{
        if(strings.length != 2)
            throw new DatabaseException("Incorrect number of parameters");
        Describe describe = commands.Describe.getInstance();
        describe.describe(database, strings[1]);
    }

    private void print(String[] strings) throws DatabaseException{
        if(strings.length != 2)
            throw new DatabaseException("Incorrect number of parameters");
        Print print = commands.Print.getInstance();
        print.print(strings[1], database);
    }

    private void export(String[] strings) throws  DatabaseException{
        if(strings.length != 3)
            throw new DatabaseException("Incorrect number of parameters");
        Export export = commands.Export.getInstance();
        export.export(database, strings[1], strings[2]);
        System.out.println("Table exported");
    }

    private void importTable(String[] strings) throws DatabaseException{
        if (strings.length != 2)
            throw new DatabaseException("Incorrect number of parameters");
        Import importTables = commands.Import.getInstance();
        importTables.importTable(database, strings[1]);
        System.out.println("Table imported");
    }

    private void rename(String[] strings) throws DatabaseException{
        if(strings.length != 3)
            throw new DatabaseException("Incorrect number of parameters");
        Rename rename = commands.Rename.getInstance();
        rename.rename(database, strings[1], strings[2]);
        System.out.println("Table "+strings[1]+" renamed to "+strings[2]);
    }

    private void update(String[] strings) throws DatabaseException{
        if(strings.length != 6)
            throw new DatabaseException("Incorrect number of parameters");
        Update update = commands.Update.getInstance();
        int count = update.update(database, strings[1], strings[2], strings[3], strings[4], strings[5]);
        System.out.println("Updated "+count+" rows");
    }

    private void delete(String[] strings) throws DatabaseException{
        if(strings.length != 4)
            throw new DatabaseException("Incorrect number of parameters");
        Delete delete = commands.Delete.getInstance();
        int count = delete.delete(database, strings[1], strings[2], strings[3]);
        System.out.println("Deleted "+count+" rows");
    }

    private void count(String[] strings) throws DatabaseException{
        if(strings.length != 4)
            throw new DatabaseException("Incorrect number of parameters");
        Count count = commands.Count.getInstance();
        int counted = count.count(database, strings[1], strings[2], strings[3]);
        System.out.println("Found "+counted+" rows");
    }

    private void select(String[] strings) throws DatabaseException{
        if(strings.length != 4)
            throw new DatabaseException("Incorrect number of parameters");
        Select select = commands.Select.getInstance();
        select.select(database, strings[1], strings[2], strings[3]);
    }

    private void command(String[] strings) throws DatabaseException{
        switch (strings[0]){
            case "help":
                help();
                break;
            case "showtables":
                showTables();
                break;
            case "addcolumn":
                addColumn(strings);
                break;
            case "saveas":
                saveAs(strings);
                break;
            case "save":
                save();
                break;
            case "open":
                open(strings);
                break;
            case "close":
                close();
                break;
            case "insert":
                insert(strings);
                break;
            case "describe":
                describe(strings);
                break;
            case "print":
                print(strings);
                break;
            case "export":
                export(strings);
                break;
            case "import":
                importTable(strings);
                break;
            case "rename":
                rename(strings);
                break;
            case "update":
                update(strings);
                break;
            case "delete":
                delete(strings);
                break;
            case "count":
                count(strings);
                break;
            case "select":
                select(strings);
                break;
            default:
                throw new DatabaseException("Command not found");
        }
    }

    //разделяне на командата на параметри
    private String[] split(String input){
        List<String> strings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;// флаг за "
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ' ' && !flag){
                strings.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }else if(input.charAt(i) == '\\'){
                i++;
                if(i < input.length())
                    stringBuilder.append(input.charAt(i));
            }else if(input.charAt(i) == '"'){
                flag = !flag;
            }else {
                stringBuilder.append(input.charAt(i));
            }
        }
        strings.add(stringBuilder.toString());
        String[] result = new String[strings.size()];
        return (String[]) strings.toArray(result);
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        String[] input;
        do{
            System.out.print('>');
            input = split(scanner.nextLine().trim());
            if(input[0].equals("exit"))
                return;

            try {
                command(input);
            }catch (DatabaseException e){
                System.out.println("Error:"+e.getMessage());
            }


        }while (true);

    }

}
