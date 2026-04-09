package LibraryManagementSystem.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;
    private int maxBorrowLimit = 5; 
    private int currentBorrowedBooks = 0;
    public User(int userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }
    public int getCurrentBorrowedBooks() {
        return currentBorrowedBooks;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }
    public void setEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        }
    }
    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        }
    }
    public boolean canBorrow() {
        return currentBorrowedBooks < maxBorrowLimit;
    }
    public void borrowBook() {
        if (canBorrow()) {
            currentBorrowedBooks++;
        }
    }
    public void returnBook() {
        if (currentBorrowedBooks > 0) {
            currentBorrowedBooks--;
        }
    }
    public String toString() {
        return "User ID: " + userId +
               " | Name: " + name +
               " | Email: " + email +
               " | Phone: " + phone +
               " | Borrowed Books: " + currentBorrowedBooks;
    }
}
