package bg.tu_varna.sit;


public class Main {
    public static void main(String[] args) {
        CLI cli = CLI.getInstance();
        cli.start();
        System.out.println("Exiting the program ...");
    }
}