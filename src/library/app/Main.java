
package library.app;

import library.model.Book;
import library.model.Magazine;
import library.model.Member;
import library.service.LibraryService;
import library.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();

        loadSampleData(libraryService);

        ConsoleUI consoleUI = new ConsoleUI(libraryService);

        consoleUI.run();
    }

    // Load initial application data

    private static void loadSampleData(LibraryService service) {

        // Sample books

        service.addItem(new Book(
            "B001",
            "Rat i mir",
            2008,
            "Lav Tolstoj",
            "978-0132350884"
        ));

        service.addItem(new Book(
            "B002",
            "Kockar",
            2018,
            "Fjodor Dostojevski",
            "978-0134685991"
        ));

        // Sample magazines

        service.addItem(new Magazine(
            "M001",
            "Plava grobnica",
            2024,
            "Milutin Bojic",
            1
        ));

        service.addItem(new Magazine(
            "M002",
            "Prokleta avlija",
            2023,
            "Ivo Andric",
            5
        ));

        // Sample members

        service.addMember(new Member(
            "U001",
            "Marko",
            "Jack",
            "marko.jack@example.com",
            "+381606464646"
        ));

        service.addMember(new Member(
            "U002",
            "Lara",
            "Croft",
            "lara.croft@ex0ample.com",
            "+381678696690"
        ));
    }
}
