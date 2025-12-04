package persistence;

import domain.*;
import java.util.List;

public interface SessionRepository {
    void save(WorkoutSession session);
    List<WorkoutSession> findByUser(User user);
}
