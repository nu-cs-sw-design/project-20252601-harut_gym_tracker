package presentation;

import java.util.List;
import java.util.Scanner;

import domain.*;
import services.WorkoutService;
import services.SessionService;
import services.StatsService;

public class AppController {

    private final WorkoutService workoutService;
    private final SessionService sessionService;
    private final StatsService statsService;
    private final ConsoleView view;

    // For now we just have one user hard-coded
    private final User currentUser = new User("1", "User", GoalType.STRENGTH);

    public AppController(WorkoutService workoutService,
                         SessionService sessionService,
                         StatsService statsService,
                         ConsoleView view) {
        this.workoutService = workoutService;
        this.sessionService = sessionService;
        this.statsService = statsService;
        this.view = view;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            view.showMenu(currentUser);
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> listWorkouts();
                case "2" -> createWorkout(sc);
                case "3" -> generateWorkout();
                case "4" -> startSession(sc);
                case "5" -> viewHistory();
                case "6" -> changeGoal(sc);
                case "0" -> running = false;
                default -> view.showMessage("Invalid option.");
            }
        }

        sc.close();
        view.showMessage("Goodbye!");
    }

    private void listWorkouts() {
        List<Workout> workouts = workoutService.listWorkouts(currentUser);
        view.displayWorkouts(workouts);
    }

    private void createWorkout(Scanner sc) {
        WorkoutBuilder builder = workoutService.createCustomWorkout();

        view.showMessage("Enter workout name: ");
        builder.setName(sc.nextLine().trim());

        while (true) {
            view.showMessage("Exercise name (empty to finish): ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) break;

            view.showMessage("Muscle group: ");
            String mg = sc.nextLine().trim();

            int sets = readInt(sc, "Sets: ", 3);
            int reps = readInt(sc, "Reps: ", 10);
            int rest = readInt(sc, "Rest seconds: ", 60);

            // difficulty is not used strongly right now, so we just pass 1
            builder.addExercise(new Exercise(name, mg, 1), sets, reps, rest);
        }

        Workout workout = builder.build();
        workoutService.saveWorkout(workout, currentUser);
        view.showMessage("Workout saved!");
    }

    private void generateWorkout() {
        Workout workout = workoutService.generateWorkout(currentUser);
        view.showMessage("Generated workout created: " + workout.getName());
    }

    private void startSession(Scanner sc) {
        List<Workout> workouts = workoutService.listWorkouts(currentUser);
        if (workouts.isEmpty()) {
            view.showMessage("No workouts available.");
            return;
        }

        view.displayWorkouts(workouts);
        int index = readInt(sc, "Select workout number: ", -1) - 1;

        if (index < 0 || index >= workouts.size()) {
            view.showMessage("Invalid workout number.");
            return;
        }

        Workout selected = workouts.get(index);
        WorkoutSession session = sessionService.startSession(selected);

        view.showMessage("Starting: " + selected.getName());

        for (WorkoutExercise we : selected.getExercises()) {
            view.showMessage("Complete " + we.getExercise().getName() + "? (y/n): ");
            String ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                // For now we log prescribed reps and 0 weight as a placeholder
                session.addEntry(new SessionEntry(we, we.getReps(), 0));
            }
        }

        sessionService.finishSession(session, currentUser);
        view.showMessage("Session saved!");
    }

    private void viewHistory() {
        List<WorkoutSession> history = sessionService.getHistory(currentUser);
        view.displayHistory(history);

        int weeklyCount = statsService.getWeeklyWorkoutCount(currentUser);
        view.showMessage("Total sessions (simplified weekly count): " + weeklyCount);
    }

    private void changeGoal(Scanner sc) {
        view.showMessage("New goal (1=Strength, 2=Hypertrophy, 3=Endurance, 4=Fat Loss): ");
        int g = readInt(sc, "> ", 1);
        currentUser.setGoal(GoalType.fromInt(g));
        view.showMessage("Goal updated to: " + currentUser.getGoal());
    }

    private int readInt(Scanner sc, String prompt, int fallback) {
        view.showMessage(prompt);
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}
