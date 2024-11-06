package main;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ManageProgram {
    
    public void Menu(){
        
    Scanner sc = new Scanner(System.in);
    String response;
    
    do{
            System.out.println("=====================================");
            System.out.println("|           PROGRAM MENU              |");
            System.out.println("=====================================");
            System.out.println("| 1. Add Program                      |");
            System.out.println("| 2. View Program                     |");
            System.out.println("| 3. Update Program                   |");
            System.out.println("| 4. Delete Program                   |");
            System.out.println("| 5. Exit                           |");
            System.out.println("=====================================");
            System.out.print("Select an action (1-5): ");
            int action = sc.nextInt();
            
            ManageProgram manage = new ManageProgram();
            
            switch(action){
                case 1:
                    manage.addProgram();
                    manage.viewProgram();
                    break;
                case 2:
                    manage.viewProgram();
                    break;
                case 3:
                    manage.viewProgram();
                    manage.editProgram();
                    manage.viewProgram();
                    break;    
                case 4:
                    manage.viewProgram();
                    manage.deleteProgram();
                    manage.viewProgram();
                    break;
                case 5:
                    
                    break;    
            }
            
            System.out.print("Do you want to continue?(Yes/No): ");
            response = sc.next();
            
        }while(response.equalsIgnoreCase("Yes"));
    
    }
    
    public void addProgram(){
    
        Scanner sc = new Scanner(System.in);
        
        config conf = new config();
        
        System.out.print("Program Name: ");
        String program_name = sc.nextLine();
        
        System.out.print("Program Description: ");
        String program_desc = sc.nextLine();
        
        System.out.print("Program Duration: ");
        String program_dur = sc.nextLine();

        String sql = "INSERT INTO tbl_program (program_name, program_desc, program_duration) VALUES (?, ?, ?)";

        conf.addRecord(sql, program_name, program_desc, program_dur);

    }
    
    public static void viewProgram() {
        config conf = new config();
         
        String query = "SELECT * FROM tbl_program";
        String[] headers = {"ID", "Program Name", "Program Description", "Program Duration"}; // DISPLAY RANI (DI MO MATTER)
        String[] columns = {"program_id", "program_name", "program_desc", "program_duration"}; // SHOULD EXACTLY MATCH UNSAY NAA SA DATABASE

        conf.viewRecords(query, headers, columns);
    }
    
    public static void editProgram(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        while(conf.getSingleValue("SELECT program_id FROM tbl_program WHERE program_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Program ID again: ");
            id = sc.nextInt();
        }
        
        System.out.print("Program New Name: ");
        String program_name = sc.nextLine();
        
        System.out.print("Program New Description: ");
        String program_desc = sc.nextLine();
        
        System.out.print("Program New Duration: ");
        String program_dur = sc.nextLine();
        
        String qry = "UPDATE tbl_program SET program_name = ?, program_desc = ?, program_duration = ? WHERE program_id = ?";
       
        conf.updateRecord(qry, program_name, program_desc, program_dur, id);
        
    }
    
    public static void deleteProgram(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT program_id FROM tbl_program WHERE program_id = ?", id) == 0){
            System.out.println("Selected ID does not exist!");
            System.out.print("Select Program ID again: ");
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM tbl_program WHERE program_id = ?";
        conf.deleteRecord(sql, id);

    }
    
}
