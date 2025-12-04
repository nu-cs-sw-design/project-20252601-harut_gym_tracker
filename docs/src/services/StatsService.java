package services;

import persistence.SessionRepository;
import domain.*;
import java.util.List;

public class StatsService {
    private final SessionRepository repo;

    public StatsService(SessionRepository repo) {
        this.repo = repo;
    }

    public int getWeeklyWorkoutCount(User user) {
        List<WorkoutSession> list = repo.findByUser(user);
        return list.size(); // simplified for now
    }
}
