package by.clevertec.util;

import by.clevertec.model.Person;
import java.time.LocalDate;
import java.time.Period;


public class Task13Util {
    public static int getAgeBetweenDate(Person person) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = person.getDateOfBirth();
        return Period.between(birthDate, currentDate).getYears();
    }
}
