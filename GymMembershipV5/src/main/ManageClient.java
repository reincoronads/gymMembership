package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ManageClient {
    
    public void Menu(){
        
    Scanner sc = new Scanner(System.in);
    String response;
    
    do{
            System.out.println("=====================================");
            System.out.println("|           CLIENT MENU             |");
            System.out.println("=====================================");
            System.out.println("| 1. Add Client                     |");
            System.out.println("| 2. View Clients                   |");
            System.out.println("| 3. Update Client                  |");
            System.out.println("| 4. Delete Client                  |");
            System.out.println("| 5. Exit                           |");
            System.out.println("=====================================");
            System.out.print("Select an action (1-5): ");
            int action = sc.nextInt();
            
            ManageClient manage = new ManageClient();
            
            switch(action){
                case 1:
                    manage.addClient();
                    manage.viewClient();
                    break;
                case 2:
                    manage.viewClient();
                    break;
                case 3:
                    manage.viewClient();
                    manage.editClient();
                    manage.viewClient();
                    break;    
                case 4:
                    manage.viewClient();
                    manage.deleteClient();
                    manage.viewClient();
                    break;
                case 5:
                    
                    break;    
            }
            
            System.out.print("Do you want to continue?(Yes/No): ");
            response = sc.next();
            
        }while(response.equalsIgnoreCase("Yes"));
    
    }
    
    public void addClient(){
    
        Scanner sc = new Scanner(System.in);
        
        config conf = new config();
        
        String fname = getValidatedName("Client First Name: ");
        
        String lname = getValidatedName("Client Last Name: ");
       
        String email = getValidatedEmail("Client Email: ");

        String phone = getValidatedPhone("Client Phone Number: ");

        String sql = "INSERT INTO tbl_clients (client_Fname, client_Lname, client_email, client_phone) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, fname, lname, email, phone);

    }
    
    public static void viewClient() {
        config conf = new config();
         
        String query = "SELECT * FROM tbl_clients";
        String[] headers = {"ID", "First Name", "Last Name", "Email", "Phone Number"}; // DISPLAY RANI (DI MO MATTER)
        String[] columns = {"client_id", "client_Fname", "client_Lname", "client_email", "client_phone"}; // SHOULD EXACTLY MATCH UNSAY NAA SA DATABASE

        conf.viewRecords(query, headers, columns);
    }
    
    public static void editClient(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT client_id FROM tbl_clients WHERE client_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Client ID again: ");
            id = sc.nextInt();
        }
        
        String fname = getValidatedName("Client New First Name: ");
        
        String lname = getValidatedName("Client New Last Name: ");
       
        String email = getValidatedEmail("Client New Email: ");

        String phone = getValidatedPhone("Client New Phone Number: ");
        
        String qry = "UPDATE tbl_clients SET client_Fname = ?, client_Lname = ?, client_email = ?, client_phone = ? WHERE client_id = ?";
       
        conf.updateRecord(qry, fname, lname, email, phone, id);
        
    }
    
    public static void deleteClient(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT client_id FROM tbl_clients WHERE client_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Client ID again: ");
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM tbl_clients WHERE client_id = ?";
        conf.deleteRecord(sql, id);

    }
    
    public static String getValidatedName(String prompt) {
        
        Scanner sc = new Scanner(System.in);
        
        String name;
        while (true) {
            System.out.print(prompt);
            name = sc.next();
            if (Pattern.matches("^[A-Za-z]+$", name)) { // Only letters
                break;
            } else {
                System.out.println("Invalid input. Names should contain only letters.");
            }
        }
        return name;
    }
    
    public static String getValidatedEmail(String prompt) {
        
        Scanner sc = new Scanner (System.in);
        
        String email;
        while (true) {
            System.out.print(prompt);
            email = sc.next();
            if (Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) { // Basic email format
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }
        return email;
    }
    
    public static String getValidatedPhone(String prompt) {
        
        Scanner sc = new Scanner (System.in);
        
        String phone;
        while (true) {
            System.out.print(prompt);
            phone = sc.next();
            if (Pattern.matches("^\\d{10,15}$", phone)) { // Only digits, 10-15 characters
                break;
            } else {
                System.out.println("Invalid phone number. Please enter 10-15 digits only.");
            }
        }
        return phone;
    }
}
