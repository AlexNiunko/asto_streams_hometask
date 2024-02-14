import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PuttingIntoPractise {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).

        List<Transaction> exercise1 = transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((o1, o2) -> o1.getTrader().getName().charAt(0) - o2.getTrader().getName().charAt(0))
                .collect(Collectors.toList());
        System.out.println(exercise1 + "\n");

        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.

        List<String> cities = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities + "\n");

        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.

        List<Trader> tradersFromCambridge = transactions
                .stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted((o1, o2) -> o1.getName().charAt(0) - o2.getName().charAt(0))
                .collect(Collectors.toList());

        System.out.println(tradersFromCambridge + "\n");

        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.

        String result = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted((o1, o2) -> o1.charAt(0) - o2.charAt(0))
                .collect(Collectors.joining(" ,"));

        System.out.println(result + "\n");

        //5. Выяснить, существует ли хоть один трейдер из Милана.

        boolean isTraderFromMilan = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(isTraderFromMilan + "\n");

        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.

        transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
        System.out.println();

        //7. Какова максимальная сумма среди всех транзакций?

        long maxAmountTransaction = transactions
                .stream()
                .map(transaction -> transaction.getValue())
                .max((o1, o2) -> o1 - o2)
                .orElse(0);
        System.out.println("The max value is " + maxAmountTransaction + "\n");

        //8. Найти транзакцию с минимальной суммой.

        long minAmountTransaction = transactions
                .stream()
                .map(transaction -> transaction.getValue())
                .min((o1, o2) -> o1 - o2)
                .orElse(0);
        System.out.println("The min value is " + minAmountTransaction + "\n");


    }
}
