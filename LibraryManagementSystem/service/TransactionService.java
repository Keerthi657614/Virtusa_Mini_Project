package LibraryManagementSystem.service;

import LibraryManagementSystem.model.Book;
import LibraryManagementSystem.model.Transaction;
import LibraryManagementSystem.model.User;

import java.util.ArrayList;

public class TransactionService {

    private ArrayList<Transaction> transactions;
    private BookService bookService;
    private UserService userService;
    private FineService fineService;

    public TransactionService(BookService bookService, UserService userService, FineService fineService) {
        this.transactions = new ArrayList<>();
        this.bookService = bookService;
        this.userService = userService;
        this.fineService = fineService;
    }

    public void issueBook(int userId, int bookId) {

        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        Book book = bookService.findBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is out of stock.");
            return;
        }

        if (!user.canBorrow()) {
            System.out.println("User has reached borrowing limit.");
            return;
        }

        int transactionId = transactions.size() + 1;
        Transaction transaction = new Transaction(transactionId, userId, bookId);

        transactions.add(transaction);
        book.decreaseQuantity();
        user.borrowBook();

        System.out.println("Book issued successfully. Transaction ID: " + transactionId);
    }

    public void returnBook(int transactionId) {

        Transaction transaction = findTransactionById(transactionId);

        if (transaction == null) {
            System.out.println("Transaction not found.");
            return;
        }

        if (transaction.isReturned()) {
            System.out.println("Book already returned.");
            return;
        }
        transaction.markAsReturned();
        int fine = fineService.calculateFine(
                transaction.getDueDate(),
                transaction.getReturnDate()
        );
        Book book = bookService.findBookById(transaction.getBookId());
        if (book != null) {
            book.increaseQuantity();
        }
        User user = userService.findUserById(transaction.getUserId());
        if (user != null) {
            user.returnBook();
        }

        System.out.println("Book returned successfully. Fine: ₹" + fine);
    }
    public Transaction findTransactionById(int transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }
    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}