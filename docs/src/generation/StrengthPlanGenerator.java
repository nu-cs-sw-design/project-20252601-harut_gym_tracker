package generation;

import domain.*;

public class StrengthPlanGenerator implements PlanGenerator {

    public Workout generate(User user) {
        WorkoutBuilder b = new WorkoutBuilder().setName("Strength Plan");
        b.addExercise(new Exercise("Squat", "Legs", 2), 5, 5, 180);
        b.addExercise(new Exercise("Bench Press", "Chest", 2), 5, 5, 180);
        b.addExercise(new Exercise("Deadlift", "Back", 3), 5, 5,180);
        return b.build();
    }
}
