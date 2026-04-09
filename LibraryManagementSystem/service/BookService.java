package LibraryManagementSystem.service;

import java.util.ArrayList;
import LibraryManagementSystem.model.Book;

public class BookService {

    private ArrayList<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getBookId() == book.getBookId()) {
                System.out.println("Book with this ID already exists.");
                return;
            }
        }
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBookByTitle(String title) {
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with title: " + title);
        }
    }

    public void searchBookByAuthor(String author) {
        boolean found = false;

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found by author: " + author);
        }
    }

    public void updateBook(int bookId, String title, String author, String isbn, int quantity) {
        Book book = findBookById(bookId);

        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setQuantity(quantity);

            System.out.println("Book updated successfully.");
            System.out.println(book);
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public void deleteBook(int bookId) {
        Book book = findBookById(bookId);

        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully: " + book.getTitle());
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}