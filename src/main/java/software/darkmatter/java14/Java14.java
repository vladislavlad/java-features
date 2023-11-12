package software.darkmatter.java14;

import java.time.LocalDateTime;

public class Java14 {

    public void switchExpression() {
        var day = LocalDateTime.now().getDayOfWeek().name();

        boolean isWorkingDay = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> true;
            case "SATURDAY", "SUNDAY" -> false;
            default -> throw new IllegalArgumentException("No such day: " + day);
        };
        System.out.println("Is today a Working day: " + isWorkingDay);
    }
}
