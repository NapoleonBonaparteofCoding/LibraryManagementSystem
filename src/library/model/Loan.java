
package library.model;

import java.time.LocalDate;

public class Loan {

    private String loanId;
    private Member member;
    private LibraryItem item;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Constructor

    public Loan(
            String loanId,
            Member member,
            LibraryItem item,
            LocalDate borrowDate,
            LocalDate dueDate) {

        setLoanId(loanId);
        setMember(member);
        setItem(item);
        setBorrowDate(borrowDate);
        setDueDate(dueDate);

        this.returnDate = null;
    }

    // Getters and setters

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        if (loanId == null || loanId.isBlank()) {
            throw new IllegalArgumentException(
                "Loan ID ne može da bude prazan."
            );
        }

        this.loanId = loanId.trim();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                "Član ne može biti prazan."
            );
        }

        this.member = member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public void setItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException(
                "Library item ne može da bude null."
            );
        }

        this.item = item;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        if (borrowDate == null) {
            throw new IllegalArgumentException(
                "Datum pozajmljivanja ne može biti null."
            );
        }

        if (borrowDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Datum pozajmljivanja ne može biti u budućnosti."
            );
        }

        if (dueDate != null && dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Rok vraćanja ne može biti pre datuma pozajmljivanja."
            );
        }

        if (returnDate != null && returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Datum pozajmljivanja ne može biti posle datuma vraćanja."
            );
        }

        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null) {
            throw new IllegalArgumentException(
                "Rok vraćanja ne može biti null."
            );
        }

        if (borrowDate == null || dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Rok vraćanja ne može biti pre datuma pozajmljivanja."
            );
        }

        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {

        if (this.returnDate != null
                && !this.returnDate.equals(returnDate)) {
            throw new IllegalStateException(
                "Vraćena pozajmica se ne može izmeniti."
            );
        }

        if (returnDate != null) {

            if (borrowDate == null
                    || returnDate.isBefore(borrowDate)) {
                throw new IllegalArgumentException(
                    "Datum vraćanja ne može biti pre datuma pozajmljivanja."
                );
            }

            if (returnDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                    "Datum vraćanja ne može biti u budućnosti."
                );
            }
        }

        this.returnDate = returnDate;
    }

    // Loan management methods

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isActive() {
        return !isReturned();
    }

    public boolean isOverdue() {
        return isActive()
                && LocalDate.now().isAfter(dueDate);
    }

    public boolean returnLoan() {
        return returnLoan(LocalDate.now());
    }

    public boolean returnLoan(LocalDate date) {

        if (isReturned()) {
            return false;
        }

        setReturnDate(date);

        return true;
    }

    public String getStatus() {

        if (isReturned()) {
            return "Vraćeno";
        }

        if (isOverdue()) {
            return "Zakasnelo";
        }

        return "Aktivno";
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId
                + " | Član: " + member.getFullName()
                + " | Item: " + item.getTitle()
                + " | Datum pozajmljivanja: " + borrowDate
                + " | Rok vraćanja: " + dueDate
                + " | Datum vraćanja: "
                + (returnDate == null ? "Nije vraćeno" : returnDate)
                + " | Status: " + getStatus();
    }
}
