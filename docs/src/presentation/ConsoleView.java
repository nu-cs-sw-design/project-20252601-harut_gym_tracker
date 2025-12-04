package presentation;

import java.util.List;
import domain.*;

public class ConsoleView {

    public void showMenu(User user) {
        System.out.println("\n=== FitForge (Clean Version) ===");
        System.out.println("Current goal: " + user.getGoal());
        System.out.println("""
            1. List Workouts
            2. Create Workout
            3. Auto Generate Workout
            4. Start Workout Session
            5. View History
            6. Change Goal
            0. Exit
            """);
        System.out.print("Choose option: ");
    }

    public void displayWorkouts(List<Workout> workouts) {
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
            return;
        }
        for (int i = 0; i < workouts.size(); i++) {
            System.out.println((i+1) + ". " + workouts.get(i).getName());
        }
    }

    public void displayHistory(List<WorkoutSession> history) {
        if (history.isEmpty()) {
            System.out.println("No history found.");
            return;
        }
        for (WorkoutSession s : history) {
            System.out.println(s);
        }
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
