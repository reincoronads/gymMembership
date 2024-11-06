package main;

import java.util.Scanner;

public class GymMembershipV5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String response;
        boolean exit = true;

        do {
            System.out.println("========================================");
            System.out.println("|   WELCOME TO ONLINE GYM MEMBERSHIP   |");
            System.out.println("========================================");
            System.out.println("|  1. Manage Clients                   |");
            System.out.println("|  2. Manage Coaches                   |");
            System.out.println("|  3. Manage Programs                  |");
            System.out.println("|  4. Membership                       |");
            System.out.println("|  5. Exit                             |");
            System.out.println("========================================");
            System.out.print("Please select an action (1-5): ");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    ManageClient client = new ManageClient();
                    client.Menu();
                    break;
                case 2:
                    ManageCoach coach = new ManageCoach();
                    coach.Menu();
                    break;
                case 3:
                    ManageProgram program = new ManageProgram();
                    program.Menu();
                    break;
                case 4:
                    Membership member = new Membership();
                    member.Menu();
                    break;
                case 5:
                    System.out.println("Exit Selected... Types 'Yes' to continue: ");
                    response = sc.next();
                    if (response.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
            }

        } while (exit);

    }

}
