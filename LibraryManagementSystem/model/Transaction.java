package LibraryManagementSystem.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {

    private int transactionId;
    private int userId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private int fineAmount;

    public Transaction(int transactionId, int userId, int bookId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(7); 
        this.returnDate = null;
        this.fineAmount = 0;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getFineAmount() {
        return fineAmount;
    }
    public boolean isReturned() {
        return returnDate != null;
    }
    public void markAsReturned() {
        if (!isReturned()) {
            this.returnDate = LocalDate.now();
            this.fineAmount = calculateFine();
        }
    }

    private boolean isOverdue() {
        return returnDate != null && returnDate.isAfter(dueDate);
    }
    private int calculateFine() {
        if (!isOverdue()) {
            return 0;
        }
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return (int) daysLate * 10; 
    }
    public String toString() {
        return "Transaction ID: " + transactionId +
               " | User ID: " + userId +
               " | Book ID: " + bookId +
               " | Issued: " + issueDate +
               " | Due: " + dueDate +
               " | Returned: " + (returnDate != null ? returnDate : "Not Returned") +
               " | Fine: ₹" + fineAmount;
    }

   
}