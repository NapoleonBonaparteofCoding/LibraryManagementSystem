
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
                "Loan ID cannot be empty."
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
                "Member cannot be null."
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
                "Library item cannot be null."
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
                "Borrow date cannot be null."
            );
        }

        if (borrowDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "Borrow date cannot be in the future."
            );
        }

        if (dueDate != null && dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Due date cannot be before borrow date."
            );
        }

        if (returnDate != null && returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Borrow date cannot be after return date."
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
                "Due date cannot be null."
            );
        }

        if (borrowDate == null || dueDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException(
                "Due date cannot be before borrow date."
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
                "A returned loan cannot be modified."
            );
        }

        if (returnDate != null) {

            if (borrowDate == null
                    || returnDate.isBefore(borrowDate)) {
                throw new IllegalArgumentException(
                    "Return date cannot be before borrow date."
                );
            }

            if (returnDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                    "Return date cannot be in the future."
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
            return "Returned";
        }

        if (isOverdue()) {
            return "Overdue";
        }

        return "Active";
    }

    @Override
    public String toString() {
        return "Loan ID: " + loanId
                + " | Member: " + member.getFullName()
                + " | Item: " + item.getTitle()
                + " | Borrow date: " + borrowDate
                + " | Due date: " + dueDate
                + " | Return date: "
                + (returnDate == null ? "Not returned" : returnDate)
                + " | Status: " + getStatus();
    }
}
