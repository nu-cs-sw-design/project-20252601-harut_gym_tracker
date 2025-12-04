package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WorkoutSession {
    private final String id;
    private final Workout workout;
    private final Date date;
    private User owner;
    private final List<SessionEntry> entries = new ArrayList<>();

    public WorkoutSession(Workout workout, Date date) {
        this.id = UUID.randomUUID().toString();
        this.workout = workout;
        this.date = date;
    }

    public void addEntry(SessionEntry entry) {
        entries.add(entry);
    }

    public List<SessionEntry> getEntries() {
        return entries;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Date getDate() {
        return date;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() { return owner; }

    @Override
    public String toString() {
        return "Session[" + workout.getName() + "] on " + date + " (" + entries.size() + " exercises)";
    }
}
