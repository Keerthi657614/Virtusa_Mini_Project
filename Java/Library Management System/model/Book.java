package LibraryManagementSystem.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    public Book(int bookId, String title, String author, String isbn, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = (quantity >= 0) ? quantity : 0; 
    }
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.trim().isEmpty()) {
            this.isbn = isbn;
        }
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void increaseQuantity() {
        quantity++;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }
    @Override
    public String toString() {
        return "Book ID: " + bookId +
               " | Title: " + title +
               " | Author: " + author +
               " | ISBN: " + isbn +
               " | Quantity: " + quantity;
    }
    
}
