package LibraryManagementSystem.service;
import LibraryManagementSystem.model.User;
import java.util.ArrayList;
public class UserService {
    private ArrayList<User>users;
    public UserService(){
        this.users=new ArrayList<>();
    }
    public void addUser(User user){
        for(User u:users){
            if(u.getUserId()==user.getUserId()){
                System.out.println("User with this ID already exists.");
                return;
            }
        }
        users.add(user);
        System.out.println("User added successfully.");
    }
    public void viewUsers(){
        if(users.isEmpty()){
            System.out.println("No users available.");
            return;
        }
        for(User user:users){
            System.out.println(user);
        }
    }
    public User findUserById(int userId){
        for(User user:users){
            if(user.getUserId()==userId){
                return user;
            }
        }
        return null;
    }
    public void deleteUser(int userId){
        User user=findUserById(userId);
        if(user!=null){
            users.remove(user);
            System.out.printf("User deleted successfully.", user.getName());
        }else{
            System.out.println("User not found.");
        }
    }
    public void updateUser(int userId,String name,String email,String phone){
        User user=findUserById(userId);
        if(user!=null){
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            System.out.println("User updated successfully.");
            System.out.println(user);
        }else{
            System.out.println("User not found.");
        }
    }

}
