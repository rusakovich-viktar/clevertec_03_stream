package by.clevertec;

import static by.clevertec.util.ConstantsAndMagicWords.BUILDING_TYPE_HOSPITAL;
import static by.clevertec.util.ConstantsAndMagicWords.COSTS_PER_TON_OF_VEHICLE;
import static by.clevertec.util.ConstantsAndMagicWords.Flower.COST_PER_CUBIC_METER;
import static by.clevertec.util.ConstantsAndMagicWords.Flower.NUMBER_OF_DAYS_IN_A_YEAR;
import static by.clevertec.util.ConstantsAndMagicWords.Flower.YEARS_FIVE;
import static by.clevertec.util.ConstantsAndMagicWords.Person.AGE_18;
import static by.clevertec.util.ConstantsAndMagicWords.Person.AGE_27;
import static by.clevertec.util.ConstantsAndMagicWords.Person.AGE_60;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.AGE_10;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.AGE_20;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.AGE_30;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.GENDER_FEMALE;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.GENDER_MALE;
import static by.clevertec.util.ConstantsAndMagicWords.Zoo.NUMBER_OF_ANIMALS_PER_ZOO;
import static by.clevertec.util.Task13Util.getAgeBetweenDate;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Task14Util;
import by.clevertec.util.Task15Util;
import by.clevertec.util.Util;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >= AGE_10 && animal.getAge() <= AGE_20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        filteredAndSortedAnimals -> IntStream.range(0, filteredAndSortedAnimals.size())
                                .boxed()
                                .collect(Collectors.groupingBy(index -> index / NUMBER_OF_ANIMALS_PER_ZOO))
                                .values()
                                .stream()
                                .map(indices -> indices
                                        .stream()
                                        .map(filteredAndSortedAnimals::get)
                                        .collect(Collectors.toList()))
                                .collect(Collectors.toList())
                ))
                .get(2)
                .forEach(animal -> {
                    System.out.println("ID: " + animal.getId() + ", Возраст: " + animal.getAge());
                });
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese") && animal.getGender().equals(GENDER_FEMALE))
                .map(animal -> animal.getBreed().toUpperCase())
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(country -> country.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();

        long countFemaleAnimals = animals.stream()
                .filter(animal -> GENDER_FEMALE.equals(animal.getGender()))
                .count();

        System.out.println("Количество животных с полом Female: " + countFemaleAnimals);

    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >= AGE_20 && animal.getAge() <= AGE_30)
                .map(animal -> "Hungarian".equals(animal.getOrigin()))
                .reduce((result1, result2) -> result1 || result2)
                .ifPresent(System.out::println);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .allMatch(animal -> GENDER_MALE.equals(animal.getGender()) || GENDER_FEMALE.equals(animal.getGender()))
                        ? "Все животные имеют пол Male или Female" : "Есть животные с другим гендером"
        );
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .noneMatch(animal -> "Oceania".equals(animal.getOrigin()))
                        ? "Нет животных из Oceania" : "Есть животные из Oceania"
        );
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .sorted(Comparator.comparing(Animal::getBreed))
                .limit(100)
                .max(Comparator.comparingInt(Animal::getAge))
                .ifPresent(oldestAnimal -> System.out.println("Возраст самого старого животного среди первых 100: " + oldestAnimal.getAge()));

    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .map(Animal::getBreed)
                .map(String::toCharArray)
                .mapToInt(arr -> arr.length)
                .min()
                .ifPresent(shortestArrayLength ->
                        System.out.println("Длина самого короткого массива: " + shortestArrayLength)
                );
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();

        System.out.println("Суммарный возраст всех животных: "
                + animals.stream()
                .mapToInt(Animal::getAge)
                .sum());

    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresentOrElse(
                        averageAge -> System.out.println("Средний возраст всех животных из Индонезии: " + averageAge),
                        () -> System.out.println("Нет животных из Индонезии.")
                );
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> GENDER_MALE.equals(person.getGender()) && person.getDateOfBirth().isAfter(LocalDate.now().minusYears(AGE_27))
                        && person.getDateOfBirth().isBefore(LocalDate.now().minusYears(AGE_18)))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();

        List<Person> evacuationList = houses.stream()
                .flatMap(house -> {
                    Stream<Person> hospitalPatients = BUILDING_TYPE_HOSPITAL.equals(house.getBuildingType())
                            ? house.getPersonList().stream()
                            : Stream.empty();

                    Stream<Person> childrenAndElderly = BUILDING_TYPE_HOSPITAL.equals(house.getBuildingType())
                            ? Stream.empty()
                            : house.getPersonList().stream()
                            .filter(person -> {
                                int age = getAgeBetweenDate(person);
                                return age < AGE_18 || age > AGE_60;
                            });

                    Stream<Person> remainingPeople = !BUILDING_TYPE_HOSPITAL.equals(house.getBuildingType())
                            ? house.getPersonList().stream()
                            .filter(person -> {
                                int age = getAgeBetweenDate(person);
                                return age >= AGE_18 && age <= AGE_60;
                            }) : Stream.empty();

                    return Stream.of(hospitalPatients, childrenAndElderly, remainingPeople
                    );
                })
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .limit(500)
                .toList();

        evacuationList
                .forEach(person -> {
                    System.out.println(person.getId() + " " + person.getDateOfBirth() + " " + person.getFirstName() + " " + person.getLastName());
                });
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        AtomicReference<Double> totalPrice = new AtomicReference<>((double) 0);
        cars.stream()
                .collect(Collectors.groupingBy(Task14Util::getKey)).values().stream()
                .limit(6)
                .forEach(list -> {
                    System.out.println("Транспортные расходы: " + new DecimalFormat("#.#").format(COSTS_PER_TON_OF_VEHICLE * list.stream()
                            .mapToLong(Car::getMass)
                            .sum()));
                    totalPrice.updateAndGet(value -> value + list.stream()
                            .mapToDouble(Car::getPrice)
                            .sum() - COSTS_PER_TON_OF_VEHICLE * list.stream()
                            .mapToDouble(Car::getMass)
                            .sum());
                });
        System.out.println("Общая выручка логистической кампании: " + new DecimalFormat("#.#").format(totalPrice.get()));
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();

        double totalCost = flowers.stream()
                .filter(Task15Util::isFlowerInRangeAndPreferred)
                .sorted(Comparator.comparing(Flower::getOrigin)
                        .reversed()
                        .thenComparing(Comparator.comparing(Flower::getPrice).reversed())
                        .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed()))
                .mapToDouble(flower -> {
                    double waterCost = flower.getWaterConsumptionPerDay() * NUMBER_OF_DAYS_IN_A_YEAR * YEARS_FIVE * COST_PER_CUBIC_METER;
                    double flowerCost = flower.getPrice();
                    System.out.printf("%s : Flower Cost = %.2f and Water Cost = %.2f \n", flower.getCommonName(), flowerCost, waterCost);
                    return waterCost + flowerCost;
                })
                .sum();

        System.out.println("\nОбщая стоимость обслуживания всех растений: " + totalCost + " $");
    }

    public static void task16() {
        List<Student> students = Util.getStudents();

        students.stream()
                .filter(student -> student.getAge() < AGE_18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + " (" + student.getAge() + " years old)"));
    }

    public static void task17() {
        List<Student> students = Util.getStudents();

        students.stream()
                .map(Student::getGroup)
                .distinct()
                .peek(System.out::println)
                .toList();
    }

    public static void task18() {
        List<Student> students = Util.getStudents();

        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)
                ))
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> System.out.printf("%s : %.2f\n", entry.getKey(), entry.getValue()));
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        String targetGroup = "C-3";

        students.stream()
                .filter(student -> student.getGroup().equals(targetGroup))
                .flatMap(student -> {
                    List<Examination> studentExams = examinations.stream()
                            .filter(exam -> exam.getStudentId() == student.getId())
                            .toList();
                    return !studentExams.isEmpty() && studentExams.stream()
                            .allMatch(exam -> exam.getExam1() > 4 && exam.getExam2() > 4 && exam.getExam3() > 4)
                            ? Stream.of(student)
                            : Stream.empty();
                })
                .peek(student -> System.out.println("Студент с успешными экзаменами: " + student))
                .toList();
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();

        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingDouble(student -> examinations.stream()
                                .filter(exam -> exam.getStudentId() == student.getId())
                                .mapToDouble(Examination::getExam1)
                                .findFirst()
                                .orElse(0.0)
                        )
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.printf("%s = %.2f", entry.getKey(), entry.getValue()));
    }

    public static void task21() {
        List<Student> students = Util.getStudents();

        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .entrySet()
                .stream()
                .peek(entry -> System.out.println("Группа " + entry.getKey() + ": Количество студентов " + entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();

        Map<String, Integer> minAgeByFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Student::getAge)),
                                minStudent -> minStudent.map(Student::getAge).orElse(0)
                        )
                ));

        minAgeByFaculty.forEach((faculty, minAge) -> {
            System.out.println("Факультет " + faculty + ": Минимальный возраст - " + minAge);
        });
    }
}
