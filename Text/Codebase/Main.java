
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Run run = new Run();
        String input= "";

        while (input.compareTo("end") != 0) {

            System.out.println("\nRun: ");
            input = scan.nextLine().toLowerCase();

            try {
                if ((input.substring(0, 11).compareTo("java pepasm") == 0)) {

                    String cmd = input.substring(12, input.length());

                    if(!run.command(cmd)){
                        System.err.println("command not recognized");
                    }
                }
            }
            catch(Exception e){}
        }
    }
}

