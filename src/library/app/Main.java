
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
            "Clean Code",
            2008,
            "Robert C. Martin",
            "978-0132350884"
        ));

        service.addItem(new Book(
            "B002",
            "Effective Java",
            2018,
            "Joshua Bloch",
            "978-0134685991"
        ));

        // Sample magazines

        service.addItem(new Magazine(
            "M001",
            "National Geographic",
            2024,
            "National Geographic Partners",
            1
        ));

        service.addItem(new Magazine(
            "M002",
            "Smithsonian",
            2023,
            "Smithsonian Institution",
            5
        ));

        // Sample members

        service.addMember(new Member(
            "U001",
            "John",
            "Doe",
            "john.doe@example.com",
            "+381641112223"
        ));

        service.addMember(new Member(
            "U002",
            "Jane",
            "Smith",
            "jane.smith@example.com",
            "+381641112224"
        ));
    }
}
