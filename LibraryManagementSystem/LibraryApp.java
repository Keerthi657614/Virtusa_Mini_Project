package LibraryManagementSystem;

import java.util.Scanner;

import LibraryManagementSystem.model.Book;
import LibraryManagementSystem.model.User;
import LibraryManagementSystem.service.BookService;
import LibraryManagementSystem.service.UserService;
import LibraryManagementSystem.service.TransactionService;
import LibraryManagementSystem.service.FineService;

public class LibraryApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        UserService userService = new UserService();
        FineService fineService = new FineService();
        TransactionService transactionService =new TransactionService(bookService, userService, fineService);
        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Register User");
            System.out.println("4. View Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Transactions");
            System.out.println("8. Search Book by Title");
            System.out.println("9. Search Book by Author");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    Book book = new Book(bookId, title, author, isbn, quantity);
                    bookService.addBook(book);
                    break;

                case 2:
                    bookService.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    User user = new User(userId, name, email, phone);
                    userService.addUser(user);
                    break;

                case 4:
                    userService.viewUsers();
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    int uId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bId = sc.nextInt();

                    transactionService.issueBook(uId, bId);
                    break;

                case 6:
                    System.out.print("Enter Transaction ID: ");
                    int tId = sc.nextInt();

                    transactionService.returnBook(tId);
                    break;

                case 7:
                    transactionService.viewTransactions();
                    break;

                case 8:
                    System.out.print("Enter title to search: ");
                    String searchTitle = sc.nextLine();
                    bookService.searchBookByTitle(searchTitle);
                    break;

                case 9:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = sc.nextLine();
                    bookService.searchBookByAuthor(searchAuthor);
                    break;

                case 0:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}