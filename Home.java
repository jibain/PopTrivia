import java.util.*;

public class Home {
    private static HashMap<Integer, Integer> playerScores = new HashMap<>();
    private static HashMap<Character, Integer> playerKeys = new HashMap<>();
    private static int winningScore = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to PopTrivia! Please enter the number of players: ");
        int playerCount = getInput();
        setupPlayers(playerCount);
        System.out.println("How many points to win? (Press Enter for default 5)");
        String pointInput = scanner.nextLine();
        if (!pointInput.isEmpty()) {
            winningScore = Integer.parseInt(pointInput);
        }
        System.out.println("Choose a category: 1. Star Wars");
        int category = getInput();
        startGame();
    }

    private static int getInput() {
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }

    private static void setupPlayers(int playerCount) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= playerCount; i++) {
            System.out.printf("Assign a key for Player %d to buzz in: ", i);
            char key = scanner.next().charAt(0);
            playerKeys.put(key, i);
            playerScores.put(i, 0);
        }
    }

    private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;
        while (!gameWon) {
            System.out.println("Question: Who is Luke Skywalker's father?");
            System.out.println("1. Obi-Wan Kenobi  2. Darth Vader  3. Yoda  4. Han Solo");
            System.out.println("Press your buzz-in key!");
            char buzzedKey = scanner.next().charAt(0);
            if (playerKeys.containsKey(buzzedKey)) {
                int player = playerKeys.get(buzzedKey);
                System.out.printf("Player %d buzzed in! Enter your answer (1-4): ", player);
                int answer = getInput();
                if (answer == 2) {
                    System.out.println("Correct!");
                    playerScores.put(player, playerScores.get(player) + 1);
                    if (playerScores.get(player) >= winningScore) {
                        System.out.printf("Player %d wins!\n", player);
                        gameWon = true;
                    }
                } else {
                    System.out.println("Wrong answer!");
                }
            }
        }
    }
}
