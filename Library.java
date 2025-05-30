import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Manga> library = new ArrayList<>();

        System.out.print("How many manga would you like to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("Entry " + (i + 1) + ": Choose type (1 = AOT, 2 = Naruto)");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.print("Enter volume number: ");
                int volume = scanner.nextInt();
                scanner.nextLine();
                library.add(new AOT(volume));
            } else if (choice == 2) {
                System.out.print("Enter arc name: ");
                String arc = scanner.nextLine();
                library.add(new Naruto(arc));
            } else {
                System.out.println("Invalid choice, please try this entry again.");
                i--;
            }
        }

        System.out.println("\nYour Library:");
        for (Manga m : library) {
            System.out.println(m);
        }

        scanner.close();
    }
}

class Manga {
    int pages;
    String title;
    String author;

    Manga(int pages, String title, String author) {
        this.pages = pages;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author + " - " + pages + " pages";
    }
}

class AOT extends Manga {
    int volume;

    AOT(int volume) {
        super(150, "Attack on Titan", "Isayama Hajime");
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() + " (Volume " + volume + ")";
    }
}

class Naruto extends Manga {
    String arc;

    Naruto(String arc) {
        super(200, "Naruto", "Kishimoto Masashi");
        this.arc = arc;
    }

    @Override
    public String toString() {
        return super.toString() + " (Arc: " + arc + ")";
    }
}