package domain;

public enum GoalType {
    STRENGTH,
    ENDURANCE,
    FAT_LOSS;

    // helper to convert from menu input
    public static GoalType fromInt(int value) {
        return switch (value) {
            case 1 -> STRENGTH;
            case 2 -> ENDURANCE;
            case 3 -> FAT_LOSS;
            default -> STRENGTH;
        };
    }
}
