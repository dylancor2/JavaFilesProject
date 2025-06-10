/*
 * Dylan Cortegana
 * Java Files Project
 * 5/29/2025
 * 
 * README
 * This is a book creation/organizer software which utilizes files to save data. A library directory is created to store all of the books for the user.
 * The program uses .txt files to store data that can be edited. The data can also be read back with the program.
 * To run the program, compile and run it with java. If the library directory does not already exist, one will be created. 
 * If you wish to edit a text file, put it into the library directory, then run the program. Using create book or edit book, you can write to the files inside the library folder.
 */



//imports
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Library {
    static ArrayList<File>books = new ArrayList<File>(); //Global arraylist for storing the file data for each book
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Input scanner
        //Searching for an existing library
        System.out.println("Searching for library.");
        File folder = new File("library");
        
        if(folder.exists() && folder.isDirectory()){
            System.out.println("Library found.");
            //Populates the arraylist if the library is found
            for(File book : folder.listFiles())
                books.add(book);
        }
        //Makes the library directory if it is not found
        else{
            folder.mkdir();
            System.out.println("Library not found, new library created.");
        }
        //User menu choices
        while(true){
            System.out.println("\nMenu:\n1)Create New Book\n2)Edit Existing Book\n3)Read Existing Book\n4)Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createNewBook(scanner);
                    break;
                case 2:
                    editExistingBook(scanner);
                    break;

                case 3:
                    readExistingBook(scanner);
                    break;

                case 4:
                    System.out.println("Work saved.\nGoodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    public static void createNewBook(Scanner reply){//Users input scanner from main
        reply.nextLine();//Clears input scanner

        //Recieves title and author information from user
        System.out.print("Title: ");
        String title = reply.nextLine();
        String fileName = title + ".txt";
        File book = new File("library", fileName); //Declares file
        //Handles error if book already exists
        if(book.exists()){
            System.out.println("A book with that title already exists.");
            return;
        }
        System.out.print("Author(s): ");
        String author = reply.nextLine();

        //Writes to the book with printwriter
        PrintWriter output;
        try{ //Catches filenotfound exception
            output = new PrintWriter(book);}
        catch(Exception e){
            System.out.println(e);
            return;
        }
        output.println(title + "\n" + author + "\n");
        System.out.println("Enter content: (enter for new line, \"END\" to finish')\n");
        while(true){
            String content = reply.nextLine();
            if(content.equals("END")){
                break;
            }
            output.println(content);
        }

        //Closes output scaner and adds book to global arraylist
        output.close();
        System.out.println("\nBook Created.");
        books.add(book);
    }

    public static void editExistingBook(Scanner reply){//Users input scanner from main
        reply.nextLine();//Clears input scanner

        //Checks if there are books to edit
        if(books.isEmpty()){
            System.out.println("There are no existing books.");
            return;
        }
        //Receives input from user to choose book
        System.out.println("Choose a book to edit (enter name): ");
        int c = 1;
        for(File book : books){ //Displays each book in the library directory
            System.out.println(c + ") " + book.getName().substring(0, book.getName().length() - 4));
            c++;
        }
        //Declares file
        String name = reply.nextLine();
        File book = new File("library", name + ".txt");
        //Creates reading scanner
        Scanner reader;
        try{reader = new Scanner(book);}
        catch(Exception e){ //Catches filenotfound exception
            System.out.println(e);
            return;
        }   
        System.out.println();
        //Displays the book's contents
        while(reader.hasNextLine()){
            System.out.println(reader.nextLine());
        }
        //appends content as the user needs
        try
            {
            FileWriter writer = new FileWriter("library/" + name + ".txt", true);
            System.out.println("Enter content: (enter for new line, \"END\" to finish')\n");
            while(true){
                String content = reply.nextLine();
                if(content.equals("END")){
                    break;
                }
                writer.write(content);
            }
            writer.close();
            reader.close();
        } catch(Exception e){ //Catches filenotfound exception
            System.out.println(e);
            return;
        }
        
        System.out.println("Book updated.");
    }

    public static void readExistingBook(Scanner reply){
        reply.nextLine();
        //Checks if there are books to read
        if(books.isEmpty()){
            System.out.println("There are no existing books.");
            return;
        }
         //Receives input from user to choose book
        System.out.println("Choose a book to read (enter name): ");
        int c = 1;
        for(File book : books){//Displays each book in the library directory
            System.out.println(c + ") " + book.getName().substring(0, book.getName().length() - 4));
            c++;
        }
        //Declares file
        String name = reply.nextLine();
        File book = new File("library", name + ".txt");
        //Creates reading scanner
        Scanner reader;
        try{reader = new Scanner(book);}
        catch(Exception e){//Catches filenotfound exception
            System.out.println(e);
            return;
        }   
        System.out.println();
        //Displays the book's contents
        while(reader.hasNextLine()){
            System.out.println(reader.nextLine());
        }
        System.out.println("END");
    }
}
