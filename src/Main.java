import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NoteManager nManager=new NoteManager();
        Scanner input=new Scanner(System.in);

        while (true){
            System.out.println("\n\n-*-*-*-*-Welcome to NoteTaking App-*-*-*-*");
            System.out.println("""
             Enter 1 to add e new Note!
             Enter 2 View All notes!
             Enter 3 to Update a Note!
             Enter 4 to Delete a note!
             Enter 5 to Reset!
             Enter 6 to Exit!!
             """);

            System.out.print("Enter Choice:");
            String choice=input.nextLine();

            switch (choice){
                case "1" ->nManager.createNewNote(input);
                case "2" ->nManager.viewAllnotes();
                case "3" ->nManager.updateNote(input);
                case "4" ->nManager.deleteNote(input);
                case "5" ->nManager.reset();
                case "6" ->{
                    System.out.println("Exiting......!!");
                    input.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }

    }
}