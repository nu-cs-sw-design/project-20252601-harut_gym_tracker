package domain;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBuilder {

    private String name;
    private final List<WorkoutExercise> items = new ArrayList<>();

    public WorkoutBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WorkoutBuilder addExercise(Exercise ex, int sets, int reps, int rest) {
        items.add(new WorkoutExercise(ex, sets, reps, rest));
        return this;
    }

    public Workout build() {
        Workout w = new Workout(name);
        for (WorkoutExercise we : items)
            w.addItem(we);
        return w;
    }
}
