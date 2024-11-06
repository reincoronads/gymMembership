package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ManageCoach {
    
    public void Menu(){
        
    Scanner sc = new Scanner(System.in);
    String response;
    
    do{
            System.out.println("=====================================");
            System.out.println("|           COACH MENU              |");
            System.out.println("=====================================");
            System.out.println("| 1. Add Coach                      |");
            System.out.println("| 2. View Coach                     |");
            System.out.println("| 3. Update Coach                   |");
            System.out.println("| 4. Delete Coach                   |");
            System.out.println("| 5. Exit                           |");
            System.out.println("=====================================");
            System.out.print("Select an action (1-5): ");
            int action = sc.nextInt();
            
            ManageCoach manage = new ManageCoach();
            
            switch(action){
                case 1:
                    manage.addCoach();
                    manage.viewCoach();
                    break;
                case 2:
                    manage.viewCoach();
                    break;
                case 3:
                    manage.viewCoach();
                    manage.editCoach();
                    manage.viewCoach();
                    break;    
                case 4:
                    manage.viewCoach();
                    manage.deleteCoach();
                    manage.viewCoach();
                    break;
                case 5:
                    
                    break;    
            }
            
            System.out.print("Do you want to continue?(Yes/No): ");
            response = sc.next();
            
        }while(response.equalsIgnoreCase("Yes"));
    
    }
    
    public void addCoach(){
    
        Scanner sc = new Scanner(System.in);
        
        config conf = new config();
        
        String fname = getValidatedName("Coach First Name: ");
        
        String lname = getValidatedName("Coach Last Name: ");
       
        String email = getValidatedEmail("Coach Email: ");

        String phone = getValidatedPhone("Coach Phone Number: ");
        
        String specialization = getValidatedName("Coach Specialization: ");

        String sql = "INSERT INTO tbl_coach (coach_Fname, coach_Lname, coach_email, coach_phone, specialization) VALUES (?, ?, ?, ?, ?)";


        conf.addRecord(sql, fname, lname, email, phone, specialization);

    }
    
    public static void viewCoach() {
        config conf = new config();
         
        String query = "SELECT * FROM tbl_coach";
        String[] headers = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Specialization"}; // DISPLAY RANI (DI MO MATTER)
        String[] columns = {"coach_id", "coach_Fname", "coach_Lname", "coach_email", "coach_phone", "specialization"}; // SHOULD EXACTLY MATCH UNSAY NAA SA DATABASE

        conf.viewRecords(query, headers, columns);
    }
    
    public static void editCoach(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT coach_id FROM tbl_coach WHERE coach_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Coach ID again: ");
            id = sc.nextInt();
        }
        
        String fname = getValidatedName("Coach New First Name: ");
        
        String lname = getValidatedName("Coach New Last Name: ");
       
        String email = getValidatedEmail("Coach New Email: ");

        String phone = getValidatedPhone("Coach New Phone Number: ");
        
        String specialization = getValidatedName("Coach New Specialization: ");
        
        String qry = "UPDATE tbl_coach SET coach_Fname = ?, coach_Lname = ?, coach_email = ?, coach_phone = ?, specialization = ? WHERE coach_id = ?";
       
        conf.updateRecord(qry, fname, lname, email, phone, specialization, id);
        
    }
    
    public static void deleteCoach(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT coach_id FROM tbl_coach WHERE coach_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Coach ID again: ");
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM tbl_coach WHERE coach_id = ?";
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