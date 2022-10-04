package commands;

public class Help implements commandsinterface.Help {
    private static commandsinterface.Help instance = null;

    private Help(){}

    public static commandsinterface.Help getInstance(){
        if(instance==null)
            instance = new Help();
        return instance;
    }

    @Override
    public void help() {
        System.out.println("The following commands are supported:");
        System.out.println("open <file> -> opens <file>");
        System.out.println("close -> closes currently opened file");
        System.out.println("save -> saves the currently open file");
        System.out.println("saveas <file> -> saves the currently open file in <file>");
        System.out.println("help -> prints this information");
        System.out.println("exit -> exists the program");
        System.out.println("import <file name> -> loads table form <file name>");
        System.out.println("showtables -> prints all table names");
        System.out.println("describe <name> -> prints table fields");
        System.out.println("print <name> -> prints rows for table <name>");
        System.out.println("export <name> <file name> -> saves table <name> to file <file name>");
        System.out.println("select <column-n> <value> <table name> -> prints rows where <column-n> == <value>");
        System.out.println("addcolumn <table name> <column name> <column type> -> adds a new column to a table, creates the table if it doesn't exist");
        System.out.println("update <table name> <search column n> <search value> <target column n> <target value> -> changes <target column n> with value <target value> where <search column n> == <search value>");
        System.out.println("insert <table name> <column 1> … <column n> -> inserts a new row in <table name>");
        System.out.println("rename <old name> <new name> -> renames a table");
        System.out.println("count <table name> <search column n> <search value> -> counts rows in table <table name> where <search column n> == <search value>");
        System.out.println("delete <table name> <search column n> <search value> -> deletes rows from table where <search column n> == <search value>");
    }
}
