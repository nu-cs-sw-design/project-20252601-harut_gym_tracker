import java.util.*;

public class Main {

    // "Database"
    public static List<Workout> workouts = new ArrayList<>();
    public static List<WorkoutSession> history = new ArrayList<>();

    public static String currentGoal = "Strength";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        seedData();

        while (true) {
            System.out.println("\n=== FitForge (Messy Prototype) ===");
            System.out.println("Current goal: " + currentGoal);
            System.out.println("1. List workouts");
            System.out.println("2. Create workout");
            System.out.println("3. Auto-generate workout");
            System.out.println("4. Start workout session");
            System.out.println("5. View workout history");
            System.out.println("6. Change goal");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String input = scanner.nextLine();

            if (input.equals("1")) listWorkouts();
            else if (input.equals("2")) createWorkout(scanner);
            else if (input.equals("3")) autoGenerateWorkout();
            else if (input.equals("4")) startWorkoutSession(scanner);
            else if (input.equals("5")) viewHistory();
            else if (input.equals("6")) changeGoal(scanner);
            else if (input.equals("0")) break;
        }

        System.out.println("Goodbye!");
    }

    private static void seedData() {
        Workout push = new Workout();
        push.name = "Example Push Day";
        push.exercises.add(new Exercise("Bench Press", "Chest", 4, 8, 90));
        push.exercises.add(new Exercise("Shoulder Press", "Shoulders", 3, 10, 90));
        workouts.add(push);
    }

    private static void listWorkouts() {
        if (workouts.isEmpty()) {
            System.out.println("No workouts yet.");
            return;
        }

        System.out.println("\nYour Workouts:");
        for (int i = 0; i < workouts.size(); i++) {
            System.out.println((i+1) + ". " + workouts.get(i).name);
        }
    }

    private static void createWorkout(Scanner sc) {
        System.out.print("Workout name: ");
        String name = sc.nextLine();
        Workout w = new Workout();
        w.name = name;

        while (true) {
            System.out.print("Exercise name (leave empty to stop): ");
            String exName = sc.nextLine();
            if (exName.isEmpty()) break;

            System.out.print("Muscle group: ");
            String mg = sc.nextLine();

            System.out.print("Sets: ");
            int sets = parseInt(sc.nextLine(), 3);

            System.out.print("Reps: ");
            int reps = parseInt(sc.nextLine(), 10);

            System.out.print("Rest (in seconds): ");
            int rest = parseInt(sc.nextLine(), 60);

            Exercise e = new Exercise(exName, mg, sets, reps, rest);
            w.exercises.add(e);
        }

        workouts.add(w);
        System.out.println("Workout created!");
    }

    private static void autoGenerateWorkout() {
        Workout w = new Workout();
        w.name = currentGoal + " Workout";

        if (currentGoal.equalsIgnoreCase("Strength")) {
            w.exercises.add(new Exercise("Squat", "Legs", 5, 5, 180));
            w.exercises.add(new Exercise("Bench Press", "Chest", 5, 5, 180));
            w.exercises.add(new Exercise("Deadlift", "Back", 3, 5, 180));
        } else if (currentGoal.equalsIgnoreCase("Endurance")) {
            w.exercises.add(new Exercise("Burpees", "Full Body", 3, 20, 60));
            w.exercises.add(new Exercise("Jumping Jacks", "Full Body", 3, 30, 30));
            w.exercises.add(new Exercise("Mountain Climbers", "Core", 3, 20, 30));
        } else if (currentGoal.equalsIgnoreCase("Hypertrophy")) {
            w.exercises.add(new Exercise("Lat Pulldown", "Back", 4, 12, 90));
            w.exercises.add(new Exercise("Chest Fly", "Chest", 4, 12, 90));
            w.exercises.add(new Exercise("Leg Press", "Legs", 4, 12, 120));
        } else {
            w.exercises.add(new Exercise("Push Ups", "Chest", 3, 15, 60));
            w.exercises.add(new Exercise("Pull Ups", "Back", 3, 10, 90));
        }

        workouts.add(w);

        System.out.println("Generated workout: " + w.name);
    }

    private static void startWorkoutSession(Scanner sc) {
        if (workouts.isEmpty()) {
            System.out.println("No workouts to start.");
            return;
        }

        listWorkouts();
        System.out.print("Choose a workout number: ");
        int index = parseInt(sc.nextLine(), -1) - 1;

        if (index < 0 || index >= workouts.size()) {
            System.out.println("Invalid workout number.");
            return;
        }

        Workout w = workouts.get(index);

        WorkoutSession session = new WorkoutSession();
        session.workoutName = w.name;
        session.date = new Date();

        System.out.println("\nStarting: " + w.name);
        for (Exercise e : w.exercises) {
            System.out.println("\nExercise: " + e.name);
            System.out.println(e.sets + " sets of " + e.reps + " reps");
            System.out.print("Mark as complete? (y/n): ");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                session.completedExercises.add(e.name);
            }
        }

        history.add(session);
        System.out.println("Workout completed and saved!");
    }

    private static void viewHistory() {
        if (history.isEmpty()) {
            System.out.println("No history recorded.");
            return;
        }

        System.out.println("\nWorkout History:");
        for (WorkoutSession s : history) {
            System.out.println("Workout: " + s.workoutName + " on " + s.date);
            System.out.println("Completed: " + s.completedExercises);
        }
    }

    private static void changeGoal(Scanner sc) {
        System.out.print("Enter new goal (Strength / Endurance / Hypertrophy / Fat Loss): ");
        String g = sc.nextLine();
        if (!g.isEmpty()) {
            currentGoal = g;
            System.out.println("Goal updated!");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private static int parseInt(String txt, int fallback) {
        try {
            return Integer.parseInt(txt);
        } catch (Exception e) {
            return fallback;
        }
    }
}
