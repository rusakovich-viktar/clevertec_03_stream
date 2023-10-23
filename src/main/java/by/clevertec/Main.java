package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
//        task5();
//        task6();
//        task7();
//        task8();
//        task9();
//        task10();
//        task11();
//        task12();
//        task13();
        task14();
//        task15();
//        task16();
//        task17();
//        task18();
//        task19();
//        task20();
//        task21();
//        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        filteredAndSortedAnimals -> IntStream.range(0, filteredAndSortedAnimals.size())
                                .boxed()
                                .collect(Collectors.groupingBy(index -> index / 7))
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
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> {
                    if ("Female".equals(animal.getGender())) {
                        return animal.getBreed().toUpperCase();
                    } else {
                        return animal.getBreed();
                    }
                })
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
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();

        System.out.println("Количество животных с полом Female: " + countFemaleAnimals);

//        System.out.println(animals.stream()
//                .filter(animal -> "Female".equals(animal.getGender()))
//                .mapToLong(animal -> 1)
//                .sum());
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();

        animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .map(animal -> "Hungarian".equals(animal.getOrigin()))
                .reduce((result1, result2) -> result1 || result2)
                .ifPresent(System.out::println);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();

        System.out.println(
                animals.stream()
                        .allMatch(animal -> "Male".equals(animal.getGender()) || "Female".equals(animal.getGender())) ?
                        "Все животные имеют пол Male или Female" : "Есть животные с другим гендером"
        );
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        System.out.println(
                animals.stream()
                        .noneMatch(animal -> "Oceania".equals(animal.getOrigin())) ?
                        "Ни одно животное не имеет страну происхождения Oceania" : "Есть животные из Oceania"
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

        System.out.println("Суммарный возраст всех животных: " +
                animals.stream()
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
                .filter(person -> "Male".equals(person.getGender()) && person.getDateOfBirth().isAfter(LocalDate.now().minusYears(27))
                        && person.getDateOfBirth().isBefore(LocalDate.now().minusYears(18)))
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();

        List<Person> evacuationList = houses.stream()
                .flatMap(house -> {
                    Stream<Person> hospitalPatients = "Hospital".equals(house.getBuildingType()) ?
                            house.getPersonList().stream() :
                            Stream.empty();

                    Stream<Person> childrenAndElderly = "Hospital".equals(house.getBuildingType()) ?
                            Stream.empty() :
                            house.getPersonList().stream()
                                    .filter(person -> {
                                        LocalDate currentDate = LocalDate.now();
                                        LocalDate birthDate = person.getDateOfBirth();
                                        int age = Period.between(birthDate, currentDate).getYears();
                                        return age < 18 || age > 60;
                                    });

                    Stream<Person> remainingPeople = "Hospital".equals(house.getBuildingType()) ?
                            Stream.empty() :
                            house.getPersonList().stream()
                                    .filter(person -> {
                                        LocalDate currentDate = LocalDate.now();
                                        LocalDate birthDate = person.getDateOfBirth();
                                        int age = Period.between(birthDate, currentDate).getYears();
                                        return age >= 18 && age <= 60;
                                    });

                    return Stream.of(hospitalPatients, childrenAndElderly);
                    //TODO STRANGE BEHAVIOR STREAM OF TURN ON THIRD ARGUMENT
                })
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .limit(500)
                .peek(person -> {
                    System.out.println(person.getId() + " " + person.getDateOfBirth() + " " + person.getFirstName() + " " + person.getLastName());
                })
                .toList();

    }

    public static void task14() {
        List<Car> cars = Util.getCars();

        Map<String, Double> countryMassSum = cars.stream()
                .filter(car -> {
                    String destinationCountry = null; // Страна по умолчанию
                    if ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor())) {
                        destinationCountry = "Туркменистан";
                    } else if (car.getMass() <= 1500 ||
                            Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())) {
                        destinationCountry = "Узбекистан";
                    } else if (("Black".equals(car.getColor()) && car.getMass() > 4000) ||
                            Arrays.asList("GMC", "Dodge").contains(car.getCarMake())) {
                        destinationCountry = "Казахстан";
                    } else if ((car.getReleaseYear() < 1982) ||
                            Arrays.asList("Civic", "Cherokee").contains(car.getCarModel())) {
                        destinationCountry = "Кыргызстан";
                    } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
                            car.getPrice() > 40000) {
                        destinationCountry = "Россия";
                    } else if (car.getVin().contains("59")) {
                        destinationCountry = "Монголия";
                    }
                    return destinationCountry != null;
                })
                .collect(Collectors.groupingBy(
                        car -> {
                            String destinationCountry = null; // Страна по умолчанию
                            if ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor())) {
                                destinationCountry = "Туркменистан";
                            } else if (car.getMass() <= 1500 ||
                                    Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())) {
                                destinationCountry = "Узбекистан";
                            } else if (("Black".equals(car.getColor()) && car.getMass() > 4000) ||
                                    Arrays.asList("GMC", "Dodge").contains(car.getCarMake())) {
                                destinationCountry = "Казахстан";
                            } else if ((car.getReleaseYear() < 1982) ||
                                    Arrays.asList("Civic", "Cherokee").contains(car.getCarModel())) {
                                destinationCountry = "Кыргызстан";
                            } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
                                    car.getPrice() > 40000) {
                                destinationCountry = "Россия";
                            } else if (car.getVin().contains("59")) {
                                destinationCountry = "Монголия";
                            }
                            return destinationCountry;
                        },
                        Collectors.summingDouble(car -> car.getMass() / 1000.0 * 7.14)
                ));

        countryMassSum.forEach((country, totalCost) -> {
            System.out.println("Страна: " + country);
            System.out.println("Суммарная стоимость транспортных расходов: $" + totalCost);
        });
    }
//}

//        Map<Car, String> carToCountry = new HashMap<>();
//
//
//        List<Car> sortedCars = cars.stream()
//                .filter(car -> {
//                            String destinationCountry = null; // Страна по умолчанию
//                            if ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor())) {
//                                destinationCountry = "Туркменистан";
//                            } else if (car.getMass() <= 1500 ||
//                                    Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())) {
//                                destinationCountry = "Узбекистан";
//                            } else if (("Black".equals(car.getColor()) && car.getMass() > 4000) ||
//                                    Arrays.asList("GMC", "Dodge").contains(car.getCarMake())) {
//                                destinationCountry = "Казахстан";
//                            } else if ((car.getReleaseYear() < 1982) ||
//                            Arrays.asList("Civic", "Cherokee").contains(car.getCarModel())) {
//                                destinationCountry = "Кыргызстан";
//                            } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
//                            car.getPrice() > 40000) {
//                                destinationCountry = "Россия";
//                            } else if (car.getVin().contains("59")) {
//                                destinationCountry = "Монголия";
//                            }
//                            return destinationCountry != null;
////                            carToCountry.put(car, destinationCountry);
////                            return true;
//
////                    boolean priority1 = ("Jaguar".equals(car.getCarMake()) || "White".equals(car.getColor()));
////                    boolean priority2 = (car.getMass() <= 1500 ||
////                            Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake()));
////                    boolean priority3 = ("Black".equals(car.getColor()) && car.getMass() > 4000) ||
////                            Arrays.asList("GMC", "Dodge").contains(car.getCarMake());
////                    boolean priority4 = (car.getReleaseYear() < 1982) ||
////                            Arrays.asList("Civic", "Cherokee").contains(car.getCarModel());
////                    boolean priority5 = !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
////                            car.getPrice() > 40000;
////                    boolean priority6 = car.getVin().contains("59");
//
////                    return priority1 || priority2 || priority3 || priority4 || priority5 || priority6;
//
//                        }
//                )
//                .sorted((car1, car2) -> {
//                    boolean car1IsPriority1 = ("Jaguar".equals(car1.getCarMake()) || "White".equals(car1.getColor()));
//                    boolean car2IsPriority1 = ("Jaguar".equals(car2.getCarMake()) || "White".equals(car2.getColor()));
//
//                    if (car1IsPriority1 && !car2IsPriority1) {
//                        return -1;
//                    } else if (!car1IsPriority1 && car2IsPriority1) {
//                        return 1;
//                    }
//
//                    boolean car1IsPriority2 = (car1.getMass() <= 1500 ||
//                            Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car1.getCarMake()));
//                    boolean car2IsPriority2 = (car2.getMass() <= 1500 ||
//                            Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car2.getCarMake()));
//
//                    if (car1IsPriority2 && !car2IsPriority2) {
//                        return -1;
//                    } else if (!car1IsPriority2 && car2IsPriority2) {
//                        return 1;
//                    }
//
//                    boolean car1IsPriority3 = ("Black".equals(car1.getColor()) && car1.getMass() > 4000) ||
//                            Arrays.asList("GMC", "Dodge").contains(car1.getCarMake());
//                    boolean car2IsPriority3 = ("Black".equals(car2.getColor()) && car2.getMass() > 4000) ||
//                            Arrays.asList("GMC", "Dodge").contains(car2.getCarMake());
//
//
//                    if (car1IsPriority3 && !car2IsPriority3) {
//                        return -1;
//                    } else if (!car1IsPriority3 && car2IsPriority3) {
//                        return 1;
//                    }
//
//                    boolean car1IsPriority4 = (car1.getReleaseYear() < 1982) ||
//                            Arrays.asList("Civic", "Cherokee").contains(car1.getCarModel());
//                    boolean car2IsPriority4 = (car2.getReleaseYear() < 1982) ||
//                            Arrays.asList("Civic", "Cherokee").contains(car2.getCarModel());
//
//                    if (car1IsPriority4 && !car2IsPriority4) {
//                        return -1;
//                    } else if (!car1IsPriority4 && car2IsPriority4) {
//                        return 1;
//                    }
////
//                    boolean car1IsPriority5 = !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car1.getColor()) ||
//                            car1.getPrice() > 40000;
//                    boolean car2IsPriority5 = !Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car2.getColor()) ||
//                            car2.getPrice() > 40000;
//
//                    if (car1IsPriority5 && !car2IsPriority5) {
//                        return -1;
//                    } else if (!car1IsPriority5 && car2IsPriority5) {
//                        return 1;
//                    }
//
//                    boolean car1IsPriority6 = car1.getVin().contains("59");
//                    boolean car2IsPriority6 = car2.getVin().contains("59");
//
//                    if (car1IsPriority6 && !car2IsPriority6) {
//                        return -1;
//                    } else if (!car1IsPriority6 && car2IsPriority6) {
//                        return 1;
//                    }
//
//                    return 0;
//                })
//                .peek(car -> {
//                    System.out.println("ID: " + car.getId());
//                    System.out.println("VIN: " + car.getVin());
//                    System.out.println("Make: " + car.getCarMake());
//                    System.out.println("Model: " + car.getCarModel());
//                    System.out.println("Release Year: " + car.getReleaseYear());
//                    System.out.println("Color: " + car.getColor());
//                    System.out.println("Mass: " + car.getMass() + " kg");
//                    System.out.println("Price: $" + car.getPrice());
//                    System.out.println();
//                })
//                .toList();
//    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        students.stream() Продолжить ...
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}
