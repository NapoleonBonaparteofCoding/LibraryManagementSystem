
package library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import library.model.LibraryItem;
import library.model.Member;
import library.model.Loan;

public class LibraryService {

    private static final int LOAN_PERIOD_DAYS = 14;

    private final ArrayList<LibraryItem> items;
    private final ArrayList<Member> members;
    private final ArrayList<Loan> loans;

    private int nextLoanNumber;

    // Constructor

    public LibraryService() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.nextLoanNumber = 1;
    }

    // Item management

    public void addItem(LibraryItem item) {

        if (item == null) {
            throw new IllegalArgumentException(
                "Library item ne može biti null."
            );
        }

        if (findItemById(item.getId()) != null) {
            throw new IllegalArgumentException(
                "Item sa ovim ID već postoji."
            );
        }

        items.add(item);
    }

    public LibraryItem findItemById(String itemId) {

        validateId(itemId, "Item ID");

        for (LibraryItem item : items) {

            if (item.getId().equalsIgnoreCase(itemId.trim())) {
                return item;
            }
        }

        return null;
    }

    public ArrayList<LibraryItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public ArrayList<LibraryItem> getAvailableItems() {

        ArrayList<LibraryItem> availableItems = new ArrayList<>();

        for (LibraryItem item : items) {

            if (item.isAvailable()) {
                availableItems.add(item);
            }
        }

        return availableItems;
    }

    public ArrayList<LibraryItem> searchItemsByTitle(String title) {

        ArrayList<LibraryItem> results = new ArrayList<>();

        if (title == null || title.isBlank()) {
            return results;
        }

        String searchQuery = title.trim().toLowerCase(Locale.ROOT);

        for (LibraryItem item : items) {

            if (item.getTitle().toLowerCase(Locale.ROOT)
                    .contains(searchQuery)) {

                results.add(item);
            }
        }

        return results;
    }

    // Member management

    public void addMember(Member member) {

        if (member == null) {
            throw new IllegalArgumentException(
                "Član ne može biti null."
            );
        }

        if (findMemberById(member.getMemberId()) != null) {
            throw new IllegalArgumentException(
                "Član sa ovim ID več postoji."
            );
        }

        for (Member existingMember : members) {

            if (existingMember.getEmail()
                    .equalsIgnoreCase(member.getEmail())) {

                throw new IllegalArgumentException(
                    "Član sa ovim Emailom več postoji."
                );
            }
        }

        members.add(member);
    }

    public Member findMemberById(String memberId) {

        validateId(memberId, "Member ID");

        for (Member member : members) {

            if (member.getMemberId()
                    .equalsIgnoreCase(memberId.trim())) {

                return member;
            }
        }

        return null;
    }

    public ArrayList<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    // Loan management

    public boolean borrowItem(String memberId, String itemId) {

        Member member = findMemberById(memberId);

        if (member == null) {
            throw new IllegalArgumentException(
                "Član nije pronadjen."
            );
        }

        LibraryItem item = findItemById(itemId);

        if (item == null) {
            throw new IllegalArgumentException(
                "Stavka nije pronadjena."
            );
        }

        if (!item.isAvailable()) {
            return false;
        }

        LocalDate borrowDate = LocalDate.now();

        LocalDate dueDate = borrowDate.plusDays(LOAN_PERIOD_DAYS);

        String loanId = generateLoanId();

        Loan loan = new Loan(
            loanId,
            member,
            item,
            borrowDate,
            dueDate
        );

        if (!item.borrow()) {
            return false;
        }

        loans.add(loan);

        return true;
    }

    public boolean returnItem(String loanId) {

        Loan loan = findLoanById(loanId);

        if (loan == null || !loan.isActive()) {
            return false;
        }

        if (!loan.getItem().returnItem()) {
            return false;
        }

        return loan.returnLoan();
    }

    public Loan findLoanById(String loanId) {

        validateId(loanId, "Loan ID");

        for (Loan loan : loans) {

            if (loan.getLoanId()
                    .equalsIgnoreCase(loanId.trim())) {

                return loan;
            }
        }

        return null;
    }

    public ArrayList<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }

    public ArrayList<Loan> getActiveLoans() {

        ArrayList<Loan> activeLoans = new ArrayList<>();

        for (Loan loan : loans) {

            if (loan.isActive()) {
                activeLoans.add(loan);
            }
        }

        return activeLoans;
    }

    public ArrayList<Loan> getOverdueLoans() {

        ArrayList<Loan> overdueLoans = new ArrayList<>();

        for (Loan loan : loans) {

            if (loan.isOverdue()) {
                overdueLoans.add(loan);
            }
        }

        return overdueLoans;
    }

    // Utility methods

    private String generateLoanId() {
        return "LN-" + nextLoanNumber++;
    }

    private void validateId(String id, String fieldName) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                fieldName + " Ne može da bude prazno."
            );
        }
    }
}
