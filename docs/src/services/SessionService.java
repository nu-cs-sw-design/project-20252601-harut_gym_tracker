package services;

import persistence.SessionRepository;
import domain.*;
import java.util.Date;
import java.util.List;

public class SessionService {

    private final SessionRepository repo;

    public SessionService(SessionRepository repo) {
        this.repo = repo;
    }

    public WorkoutSession startSession(Workout w) {
        return new WorkoutSession(w, new Date());
    }

    public void finishSession(WorkoutSession session, User user) {
        session.setOwner(user);
        repo.save(session);
    }

    public List<WorkoutSession> getHistory(User user) {
        return repo.findByUser(user);
    }
}
