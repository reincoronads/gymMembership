package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Membership {
    
    public void Menu(){
        
    Scanner sc = new Scanner(System.in);
    String response;
    
    do{
            System.out.println("=========================================");
            System.out.println("|           Membership MENU              |");
            System.out.println("=========================================");
            System.out.println("| 1. Add Member                          |");
            System.out.println("| 2. View Members                        |");
            System.out.println("| 3. Update Member                       |");
            System.out.println("| 4. Delete Member                       |");
            System.out.println("| 5. Reports                             |");
            System.out.println("| 6. Exit                                |");
            System.out.println("=========================================");
            System.out.print("Select an action (1-5): ");
            int action = sc.nextInt();
            
            Membership manage = new Membership();
            
            switch(action){
                case 1:
                    manage.addMember();
                    manage.viewMember();
                    break;
                case 2:
                    manage.viewMember();
                    break;
                case 3:
                    
                    break;    
                case 4:
                    
                    break;
                case 5:
                    
                    break;    
                case 6:
                    
                    break;       
            }
            
            System.out.print("Do you want to continue?(Yes/No): ");
            response = sc.next();
            
        }while(response.equalsIgnoreCase("Yes"));
    
    }
    
    public void addMember(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        ManageClient client = new ManageClient();
        client.viewClient();
        System.out.print("Add Client: ");
        int client_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT client_id FROM tbl_clients WHERE client_id = ?", client_id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Client ID again: ");
            client_id = sc.nextInt();
        }
        
        ManageCoach coach = new ManageCoach();
        coach.viewCoach();
        System.out.print("Add Coach: ");
        int coach_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT coach_id FROM tbl_coach WHERE coach_id = ?", coach_id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Coach ID again: ");
            coach_id = sc.nextInt();
        }
        
        ManageProgram program = new ManageProgram();
        program.viewProgram();
        System.out.println("Add Program: ");
        int program_id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT program_id FROM tbl_program WHERE program_id = ?", program_id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Program ID again: ");
            program_id = sc.nextInt();
        }
        
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        String start_date = currdate.format(format);
        String end_date = "Pending";
        String status = "Pending";

        String sql = "INSERT INTO tbl_membership (client_id, coach_id, program_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";

        conf.addRecord(sql, client_id, coach_id, program_id, start_date, end_date, status);

    }
    
    public static void viewMember() {
        config conf = new config();
         
        String query = "SELECT member_id, client_Lname, coach_Fname, program_name, start_date, end_date, status FROM tbl_membership "
                + "LEFT JOIN tbl_clients ON tbl_clients.client_id = tbl_membership.client_id "
                + "LEFT JOIN tbl_coach ON tbl_coach.coach_id = tbl_membership.coach_id "
                + "LEFT JOIN tbl_program ON tbl_program.program_id = tbl_membership.program_id";
        
        String[] headers = {"ID", "Client Name", "Current Coach", "Current Program", "Start Date", "End Date", "Status"}; // DISPLAY RANI (DI MO MATTER)
        String[] columns = {"member_id", "client_Lname", "coach_Fname", "program_name", "start_date", "end_date", "status"}; // SHOULD EXACTLY MATCH UNSAY NAA SA DATABASE

        conf.viewRecords(query, headers, columns);
    }
    
    public static void editMember(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT member_id FROM tbl_membership WHERE member_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Member ID again: ");
            id = sc.nextInt();
        }
    }
    
    public static void deleteMember(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT member_id FROM tbl_membership WHERE member_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Member ID again: ");
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM tbl_membership WHERE member_id = ?";
        conf.deleteRecord(sql, id);

    }
    
}
