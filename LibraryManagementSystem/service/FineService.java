package LibraryManagementSystem.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineService {

    private static final int FINE_PER_DAY = 10;

    public int calculateFine(LocalDate dueDate, LocalDate returnDate) {

        if (returnDate == null || dueDate == null) {
            return 0;
        }

        if (!returnDate.isAfter(dueDate)) {
            return 0; 
        }

        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);

        return (int) daysLate * FINE_PER_DAY;
    }
}
