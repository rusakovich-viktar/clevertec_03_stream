package by.clevertec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstantsAndMagicWords {
    public static final Double COSTS_PER_TON_OF_VEHICLE = 7.14;
    public static final String BUILDING_TYPE_HOSPITAL = "Hospital";


    @UtilityClass
    public class Zoo {
        public static final Integer AGE_10 = 10;
        public static final Integer AGE_20 = 20;
        public static final Integer AGE_30 = 30;
        public static final Integer NUMBER_OF_ANIMALS_PER_ZOO = 7;
        public static final String GENDER_MALE = "Male";
        public static final String GENDER_FEMALE = "Female";
    }

    @UtilityClass
    public class Person {
        public static final Integer AGE_18 = 18;
        public static final Integer AGE_27 = 27;
        public static final Integer AGE_60 = 60;

    }

    @UtilityClass
    public class Flower {
        public static final Integer NUMBER_OF_DAYS_IN_A_YEAR = 365;
        public static final Integer YEARS_FIVE = 5;
        public static final Double COST_PER_CUBIC_METER = 1.39;

    }
}
