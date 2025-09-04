package com.PetCareReminder;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App 
    {
    private static final Scanner sc = new Scanner(System.in);
    private static final PetReminderDAO dao = new PetReminderDAO();

	public static void main(String[] args)throws Exception {
        while (true) {
            printMenu();
            int ch = readInt("Enter choice: ");
            switch (ch) {
            case 1:
                addReminderFlow();
                break;
            case 2:
                listRemindersFlow();
                break;
            case 3:
                updateReminderFlow();
                break;
            case 4:
                deleteReminderFlow();
                break;
            case 5:
                System.out.println("Bye!");
                return; // exits main()
            default:
                System.out.println("Invalid choice.\n");
                break;
        }
            }
        }
    private static void printMenu() {
        System.out.println("\n=== Pet Care Reminder ===");
        System.out.println("1. Add reminder");
        System.out.println("2. View all reminders");
        System.out.println("3. Update a reminder");
        System.out.println("4. Delete a reminder");
        System.out.println("5. Exit");
    }

    private static void addReminderFlow() {
        System.out.println("\n-- Add Reminder --");
        String name = readLine("Pet name         : ");
        String type = readLine("Pet type (dog..) : ");
        String Type = readLine("Reminder Type      : ");
        LocalDate date = readDate("Reminder date (yyyy-MM-dd): ");
      

        int newId = dao.addReminder(new PetReminder(name, type, Type,date));
        System.out.println(newId > 0 ? "Added with ID " + newId : "Add failed");
    }

    private static void listRemindersFlow() {
        System.out.println("\n-- All Reminders --");
        List<PetReminder> all = dao.getAllReminders();
        if (all.isEmpty()) {
            System.out.println("(no reminders yet)");
        } else {
            all.forEach(System.out::println);
        }
    }

    private static void updateReminderFlow() {
        System.out.println("\n-- Update Reminder --");
        int id = readInt("Enter reminder ID to update: ");
        PetReminder existing = dao.getById(id);
        if (existing == null) {
            System.out.println("No reminder found for ID " + id);
            return;
        }
        System.out.println("Current: " + existing);

        String name = readLine("New pet name (blank=keep): ");
        if (!name.isBlank()) existing.setPetName(name);

        String type = readLine("New pet type (blank=keep): ");
        if (!type.isBlank()) existing.setPetType(type);
        
        String Type = readLine("New reminder type (blank=keep): ");
        if (!Type.isBlank()) existing.setRemindertype(Type);

        String dateStr = readLine("New date yyyy-MM-dd (blank=keep): ");
        if (!dateStr.isBlank()) existing.setReminderDate(LocalDate.parse(dateStr));

        

        boolean ok = dao.updateReminder(existing);
        System.out.println(ok ? "Updated." : "Update failed.");
    }

    private static void deleteReminderFlow() {
        System.out.println("\n-- Delete Reminder --");
        int id = readInt("Enter reminder ID to delete: ");
        boolean ok = dao.deleteReminder(id);
        System.out.println(ok ? "Deleted." : "Delete failed or ID not found.");
    }

    // ---------- input helpers ----------
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine();
                return Integer.parseInt(s.trim());
            } catch (Exception e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return LocalDate.parse(sc.nextLine().trim()); // expects yyyy-MM-dd
            } catch (Exception e) {
                System.out.println("Please use format yyyy-MM-dd (e.g., 2025-09-10).");
            }
        }
    }
}
