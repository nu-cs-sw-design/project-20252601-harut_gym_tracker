package domain;

public class User {
    private final String id;
    private final String name;
    private GoalType goal;

    public User(String id, String name, GoalType goal) {
        this.id = id;
        this.name = name;
        this.goal = goal;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public GoalType getGoal() { return goal; }

    public void setGoal(GoalType goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return name + " (Goal: " + goal + ")";
    }
}
