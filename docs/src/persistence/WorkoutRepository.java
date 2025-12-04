package persistence;

import domain.*;
import java.util.List;

public interface WorkoutRepository {
    void save(Workout workout);
    List<Workout> findByUser(User user);
}
