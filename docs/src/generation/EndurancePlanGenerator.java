package generation;

import domain.*;

public class EndurancePlanGenerator implements PlanGenerator {
    public Workout generate(User user) {
        WorkoutBuilder b = new WorkoutBuilder().setName("Endurance Plan");
        b.addExercise(new Exercise("Burpees", "Full Body", 3), 3, 20, 60);
        b.addExercise(new Exercise("Jumping Jacks", "Full Body", 2), 3, 30, 30);
        return b.build();
    }
}
