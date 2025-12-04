package persistence;

import domain.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryWorkoutRepository implements WorkoutRepository {

    private final List<Workout> workouts = new ArrayList<>();

    @Override
    public void save(Workout workout) {
        workouts.add(workout);
    }

    @Override
    public List<Workout> findByUser(User user) {
        List<Workout> out = new ArrayList<>();
        for (Workout w : workouts) {
            if (w.getOwner() != null && w.getOwner().getId().equals(user.getId())) {
                out.add(w);
            }
        }
        return out;
    }
}
