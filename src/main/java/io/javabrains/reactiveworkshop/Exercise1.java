package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {
        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        System.out.println("--- Exercise 1 - task 1 ---");
        StreamSources.intNumbersStream()
                .forEach(System.out::println);

        // Print numbers from intNumbersStream that are less than 5
        System.out.println("--- Exercise 1 - task 2 ---");
        StreamSources.intNumbersStream()
                .filter(n -> n < 5)
                .forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream that's greater than 5
        System.out.println("--- Exercise 1 - task 3 ---");
        StreamSources.intNumbersStream()
                .filter(n -> n > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        // Print the first number in intNumbersStream that's greater than 5.
        // If nothing is found, print -1
        System.out.println("--- Exercise 1 - task 4 ---");
        StreamSources.intNumbersStream()
                .filter(n -> n > 5)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println(-1));

        // Print first names of all users in userStream
        System.out.println("--- Exercise 1 - task 5 ---");
        StreamSources.userStream()
                .map(User::getFirstName)
                .forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        // Solution 1
        System.out.println("--- Exercise 1 - task 6, Solution 1 ---");
        StreamSources.intNumbersStream()
                .<User>mapMulti((n, consumer) -> StreamSources.userStream().filter(user -> user.getId() == n)
                        .forEach(user -> consumer.accept(user)))
                .map(User::getFirstName)
                .forEach(System.out::println);
        // Solution 2
        System.out.println("--- Exercise 1 - task 6, Solution 2 ---");
        StreamSources.intNumbersStream()
                .flatMap(n -> StreamSources.userStream().filter(user -> user.getId() == n))
                .map(User::getFirstName)
                .forEach(System.out::println);
        // Solution 3
        System.out.println("--- Exercise 1 - task 6, Solution 3 ---");
        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(n -> n == user.getId()))
                .map(User::getFirstName)
                .forEach(System.out::println);
    }

}
