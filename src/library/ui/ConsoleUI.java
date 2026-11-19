
package library.ui;

import java.util.List;
import java.util.Scanner;

import library.model.Book;
import library.model.Magazine;
import library.model.LibraryItem;
import library.model.Member;
import library.model.Loan;
import library.service.LibraryService;

public class ConsoleUI {

    private final LibraryService libraryService;
    private final Scanner scanner;
    private boolean running;

    // Constructor

    public ConsoleUI(LibraryService libraryService) {

        if (libraryService == null) {
            throw new IllegalArgumentException(
                "Library service cannot be null."
            );
        }

        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    // Application menu

    public void run() {

        System.out.println("=====================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=====================================");

        while (running) {

            displayMenu();

            int option = readInt("Choose an option: ");

            try {
                handleMenuOption(option);

            } catch (IllegalArgumentException | IllegalStateException e) {

                System.out.println("Operation failed: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void displayMenu() {

        System.out.println("-------------------------------------");
        System.out.println("1. Display all library items");
        System.out.println("2. Display available items");
        System.out.println("3. Add a new book");
        System.out.println("4. Add a new magazine");
        System.out.println("5. Register a new member");
        System.out.println("6. Display all members");
        System.out.println("7. Borrow an item");
        System.out.println("8. Return an item");
        System.out.println("9. Display all loans");
        System.out.println("10. Display active loans");
        System.out.println("11. Search items by title");
        System.out.println("12. Display overdue loans");
        System.out.println("0. Exit");
        System.out.println("-------------------------------------");
    }

    // Menu option handler

    private void handleMenuOption(int option) {

        switch (option) {

            case 1:
                displayAllItems();
                break;

            case 2:
                displayAvailableItems();
                break;

            case 3:
                addBook();
                break;

            case 4:
                addMagazine();
                break;

            case 5:
                registerMember();
                break;

            case 6:
                displayAllMembers();
                break;

            case 7:
                borrowItem();
                break;

            case 8:
                returnItem();
                break;

            case 9:
                displayAllLoans();
                break;

            case 10:
                displayActiveLoans();
                break;

            case 11:
                searchItems();
                break;

            case 12:
                displayOverdueLoans();
                break;

            case 0:
                running = false;
                System.out.println("Thank you for using the system!");
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Item display methods

    private void displayAllItems() {

        System.out.println("\n--- ALL LIBRARY ITEMS ---");

        displayItems(
            libraryService.getAllItems(),
            "No library items found."
        );
    }

    private void displayAvailableItems() {

        System.out.println("\n--- AVAILABLE ITEMS ---");

        displayItems(
            libraryService.getAvailableItems(),
            "No available items found."
        );
    }

    private void displayItems(
            List<LibraryItem> items,
            String emptyMessage) {

        if (items.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (LibraryItem item : items) {

            System.out.println(item.getDetails());
        }
    }

    // Add book

    private void addBook() {

        System.out.println("\n--- ADD A NEW BOOK ---");

        String id = readLine("Book ID: ");
        String title = readLine("Title: ");
        int year = readInt("Publication year: ");
        String author = readLine("Author: ");
        String isbn = readLine("ISBN: ");

        Book book = new Book(
            id,
            title,
            year,
            author,
            isbn
        );

        libraryService.addItem(book);

        System.out.println("Book successfully added!");
    }

    // Add magazine

    private void addMagazine() {

        System.out.println("\n--- ADD A NEW MAGAZINE ---");

        String id = readLine("Magazine ID: ");
        String title = readLine("Title: ");
        int year = readInt("Publication year: ");
        String publisher = readLine("Publisher: ");
        int issueNumber = readInt("Issue number: ");

        Magazine magazine = new Magazine(
            id,
            title,
            year,
            publisher,
            issueNumber
        );

        libraryService.addItem(magazine);

        System.out.println("Magazine successfully added!");
    }

    // Member management

    private void registerMember() {

        System.out.println("\n--- REGISTER A NEW MEMBER ---");

        String id = readLine("Member ID: ");
        String firstName = readLine("First name: ");
        String lastName = readLine("Last name: ");
        String email = readLine("Email: ");
        String phone = readLine("Phone number: ");

        Member member = new Member(
            id,
            firstName,
            lastName,
            email,
            phone
        );

        libraryService.addMember(member);

        System.out.println("Member successfully registered!");
    }

    private void displayAllMembers() {

        System.out.println("\n--- ALL MEMBERS ---");

        List<Member> members = libraryService.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        for (Member member : members) {

            System.out.println(member);
        }
    }

    // Borrow an item

    private void borrowItem() {

        System.out.println("\n--- BORROW AN ITEM ---");

        String memberId = readLine("Member ID: ");
        String itemId = readLine("Item ID: ");

        boolean success = libraryService.borrowItem(
            memberId,
            itemId
        );

        if (success) {
            System.out.println("Item successfully borrowed!");
        } else {
            System.out.println(
                "Item is currently unavailable."
            );
        }
    }

    // Return an item

    private void returnItem() {

        System.out.println("\n--- RETURN AN ITEM ---");

        String loanId = readLine("Loan ID: ");

        boolean success = libraryService.returnItem(loanId);

        if (success) {
            System.out.println("Item successfully returned!");
        } else {
            System.out.println(
                "Loan not found or item has already been returned."
            );
        }
    }

    // Loan display methods

    private void displayAllLoans() {

        System.out.println("\n--- ALL LOANS ---");

        displayLoans(
            libraryService.getAllLoans(),
            "No loans found."
        );
    }

    private void displayActiveLoans() {

        System.out.println("\n--- ACTIVE LOANS ---");

        displayLoans(
            libraryService.getActiveLoans(),
            "No active loans found."
        );
    }

    private void displayOverdueLoans() {

        System.out.println("\n--- OVERDUE LOANS ---");

        displayLoans(
            libraryService.getOverdueLoans(),
            "No overdue loans found."
        );
    }

    private void displayLoans(
            List<Loan> loans,
            String emptyMessage) {

        if (loans.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        for (Loan loan : loans) {

            System.out.println(loan);
        }
    }

    // Search items

    private void searchItems() {

        System.out.println("\n--- SEARCH ITEMS ---");

        String title = readLine("Enter title to search: ");

        List<LibraryItem> results =
            libraryService.searchItemsByTitle(title);

        if (results.isEmpty()) {

            System.out.println("No matching items found.");
            return;
        }

        System.out.println("Search results:");

        displayItems(results, "No matching items found.");
    }

    // Input methods

    private String readLine(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid input. Please enter a valid number."
                );
            }
        }
    }
}
