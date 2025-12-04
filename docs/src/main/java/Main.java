package main.java;

import presentation.*;
import services.*;
import persistence.*;
import domain.*;

public class Main {

    public static void main(String[] args) {
        // Repositories
        WorkoutRepository workoutRepo = new InMemoryWorkoutRepository();
        SessionRepository sessionRepo = new InMemorySessionRepository();

        // Services
        GeneratorFactory factory = new GeneratorFactory();
        WorkoutService workoutService = new WorkoutService(workoutRepo, factory);
        SessionService sessionService = new SessionService(sessionRepo);
        StatsService statsService = new StatsService(sessionRepo);

        // View
        ConsoleView view = new ConsoleView();

        // Controller
        AppController controller =
                new AppController(workoutService, sessionService, statsService, view);

        controller.run();
    }
}
