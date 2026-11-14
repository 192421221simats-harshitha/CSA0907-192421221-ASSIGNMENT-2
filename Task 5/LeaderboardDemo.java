
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

class Team {
    final String name;
    final int points;
    final int goalDiff;

    Team(String name, int points, int goalDiff) {
        this.name = name;
        this.points = points;
        this.goalDiff = goalDiff;
    }

    @Override
    public String toString() {
        return name + "(" + points + "," + goalDiff + ")";
    }
}

class Leaderboard<T> {

    private final List<T> items = new ArrayList<>();
    private final Comparator<? super T> order;

    Leaderboard(Comparator<? super T> order) {
        this.order = order;
    }

    void add(T item) {
        items.add(item);
    }

    // Accepts a list of T or its subclasses
    void addAll(List<? extends T> newItems) {
        items.addAll(newItems);
    }

    // Returns a sorted copy without changing original items
    List<T> ranking() {
        List<T> result = new ArrayList<>(items);
        result.sort(order);
        return result;
    }
}

public class LeaderboardDemo {

    public static void main(String[] args) {

        // Correct comparator:
        // 1. Points - high to low
        // 2. Goal difference - high to low
        // 3. Team name - alphabetical order

        Comparator<Team> byRank =
            Comparator.comparingInt((Team t) -> t.points)
                .reversed()
                .thenComparing(
                    Comparator.comparingInt(
                        (Team t) -> t.goalDiff
                    ).reversed()
                )
                .thenComparing(t -> t.name);

        Leaderboard<Team> board =
            new Leaderboard<>(byRank);

        board.add(new Team("Panthers", 9, 4));
        board.add(new Team("Eagles", 9, 6));
        board.add(new Team("Cobras", 6, 1));
        board.add(new Team("Bisons", 9, 4));

        System.out.println("SPORTS LEADERBOARD");
        System.out.println("------------------------------");

        System.out.println("Correct Ranking:");
        System.out.println(board.ranking());

        // Second test: faulty comparator
        // It compares points only and ignores tie-breakers.

        Comparator<Team> faultyOrder =
            Comparator.comparingInt(
                (Team t) -> t.points
            ).reversed();

        Leaderboard<Team> faultyBoard =
            new Leaderboard<>(faultyOrder);

        faultyBoard.add(new Team("Panthers", 9, 4));
        faultyBoard.add(new Team("Eagles", 9, 6));
        faultyBoard.add(new Team("Cobras", 6, 1));
        faultyBoard.add(new Team("Bisons", 9, 4));

        System.out.println("\nFaulty Ranking:");
        System.out.println(faultyBoard.ranking());

        System.out.println("\nProgram completed successfully.");
    }
}
