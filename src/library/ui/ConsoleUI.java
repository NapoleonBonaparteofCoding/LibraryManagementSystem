
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
        System.out.println("SISTEM ZA UPRAVLJANJE BIBLIOTEKOM");
        System.out.println("=====================================");

        while (running) {

            displayMenu();

            int option = readInt("Izaberite opciju: ");

            try {
                handleMenuOption(option);

            } catch (IllegalArgumentException | IllegalStateException e) {

                System.out.println("Greška: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private void displayMenu() {

        System.out.println("-------------------------------------");
        System.out.println("1. Prikaz svih stavki iz biblioteke");
        System.out.println("2. Prikaz dostupnih stavki");
        System.out.println("3. Dodavanje nove knjige");
        System.out.println("4. Dodavanje novog časopisa");
        System.out.println("5. Registracija novog člana");
        System.out.println("6. Prikaz svih članova");
        System.out.println("7. Pozajmljivanje stavke");
        System.out.println("8. Vraćanje stavke");
        System.out.println("9. Prikaz svih pozajmica");
        System.out.println("10. Prikaz aktivnih pozajmica");
        System.out.println("11. Pretraga stavki po naslovu");
        System.out.println("12. Prikaz prekoračenih pozajmica");
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
                System.out.println("Hvala što koristite sistem!");
                System.out.println("Dovidjenja!");
                break;

            default:
                System.out.println("Nevažeća opcija. Molim vas pokušajte opet.");
        }
    }

    // Item display methods

    private void displayAllItems() {

        System.out.println("\n--- SVE STAVKE U BIBLIOTECI ---");

        displayItems(
            libraryService.getAllItems(),
            "Nisu pronađene stavke u biblioteci."
        );
    }

    private void displayAvailableItems() {

        System.out.println("\n--- DOSTUPNE STAVKE ---");

        displayItems(
            libraryService.getAvailableItems(),
            "Nisu pronađene dostupne stavke."
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

        System.out.println("\n--- DODAJ NOVU KNJIGU ---");

        String id = readLine("Book ID: ");
        String title = readLine("Naslov: ");
        int year = readInt("Godina izdavanja: ");
        String author = readLine("Autor: ");
        String isbn = readLine("ISBN: ");

        Book book = new Book(
            id,
            title,
            year,
            author,
            isbn
        );

        libraryService.addItem(book);

        System.out.println("Knjiga je uspešno dodana!");
    }

    // Add magazine

    private void addMagazine() {

        System.out.println("\n--- DODAJ NOVI MAGAZIN ---");

        String id = readLine("Magazine ID: ");
        String title = readLine("Naslov: ");
        int year = readInt("Godina izdavanja: ");
        String publisher = readLine("Izdavač: ");
        int issueNumber = readInt("Broj izdanja: ");

        Magazine magazine = new Magazine(
            id,
            title,
            year,
            publisher,
            issueNumber
        );

        libraryService.addItem(magazine);

        System.out.println("Magazin je uspešno dodat!");
    }

    // Member management

    private void registerMember() {

        System.out.println("\n--- REGISTRUJ NOVOG ČLANA ---");

        String id = readLine("Member ID: ");
        String firstName = readLine("Ime: ");
        String lastName = readLine("Prezime: ");
        String email = readLine("Email: ");
        String phone = readLine("Broj telefona: ");

        Member member = new Member(
            id,
            firstName,
            lastName,
            email,
            phone
        );

        libraryService.addMember(member);

        System.out.println("Član je uspešno registrovan!");
    }

    private void displayAllMembers() {

        System.out.println("\n--- Svi članovi ---");

        List<Member> members = libraryService.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("Članovi nisu pronadjeni.");
            return;
        }

        for (Member member : members) {

            System.out.println(member);
        }
    }

    // Borrow an item

    private void borrowItem() {

        System.out.println("\n--- POZAJMI STAVKU ---");

        String memberId = readLine("Member ID: ");
        String itemId = readLine("Item ID: ");

        boolean success = libraryService.borrowItem(
            memberId,
            itemId
        );

        if (success) {
            System.out.println("Stavka uspešno pozajmljena!");
        } else {
            System.out.println(
                "Stavka trenutno nije dostupna."
            );
        }
    }

    // Return an item

    private void returnItem() {

        System.out.println("\n--- VRATI STAVKU ---");

        String loanId = readLine("Loan ID: ");

        boolean success = libraryService.returnItem(loanId);

        if (success) {
            System.out.println("Stavka uspešno vraćena!");
        } else {
            System.out.println(
                "Pozajmnica nije pronađena ili je predmet već vraćen."
            );
        }
    }

    // Loan display methods

    private void displayAllLoans() {

        System.out.println("\n--- SVE POZAJMNICE ---");

        displayLoans(
            libraryService.getAllLoans(),
            "Pozajmnice nisu pronadjene."
        );
    }

    private void displayActiveLoans() {

        System.out.println("\n--- AKTIVNE POZAJMNICE ---");

        displayLoans(
            libraryService.getActiveLoans(),
            "Aktivne pozajmince nisu pronadjene."
        );
    }

    private void displayOverdueLoans() {

        System.out.println("\n--- POZAJMNICE SA ISTEKLIM ROKOM ---");

        displayLoans(
            libraryService.getOverdueLoans(),
           "Pozajmince sa isteklim rokom nisu pronadjene."
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

        System.out.println("\n--- POTRAŽI STAVKE ---");

        String title = readLine("Unesi naslov za pretragu: ");

        List<LibraryItem> results =
            libraryService.searchItemsByTitle(title);

        if (results.isEmpty()) {

            System.out.println("Nisu pronadjene odgovarjauće stavke.");
            return;
        }

        System.out.println("Rezultati pretrage:");

        displayItems(results, "Nisu pronadjene odgovarjauće stavke.");
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
                    "Nevažeći unos. Molim vas unesite validan broj."
                );
            }
        }
    }
}
