package persistence;

import domain.*;
import java.util.ArrayList;
import java.util.List;

public class InMemorySessionRepository implements SessionRepository {

    private final List<WorkoutSession> sessions = new ArrayList<>();

    @Override
    public void save(WorkoutSession session) {
        sessions.add(session);
    }

    @Override
    public List<WorkoutSession> findByUser(User user) {
        List<WorkoutSession> out = new ArrayList<>();
        for (WorkoutSession s : sessions) {
            if (s.getOwner() != null && s.getOwner().getId().equals(user.getId())) {
                out.add(s);
            }
        }
        return out;
    }
}
