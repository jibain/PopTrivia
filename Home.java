import java.util.*;

public class Home {
    private static HashMap<Integer, Integer> playerScores = new HashMap<>();
    private static HashMap<Character, Integer> playerKeys = new HashMap<>();
    private static int winningScore = 5;
    private static HashMap<String, List<Question>> questionBank = new HashMap<>();
    private static String[] categories = {"Star Wars", "Disney", "Harry Potter", "Naruto", "Attack on Titan"};
    private static String selectedCategory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeQuestions();
        System.out.println("Welcome to PopTrivia! Please enter the number of players: ");
        int playerCount = getInput();
        setupPlayers(playerCount);
        System.out.println("How many points to win? (Press Enter for default 5)");
        String pointInput = scanner.nextLine();
        if (!pointInput.isEmpty()) {
            winningScore = Integer.parseInt(pointInput);
        }
        System.out.println("Choose a category:");
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %s%n", i + 1, categories[i]);
        }
        int categoryIndex = getInput() - 1;
        if (categoryIndex >= 0 && categoryIndex < categories.length) {
            selectedCategory = categories[categoryIndex];
        } else {
            System.out.println("Invalid category selection. Defaulting to Star Wars.");
            selectedCategory = "Star Wars";
        }
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

    private static void initializeQuestions() {
        List<Question> starWarsQuestions = new ArrayList<>();
        starWarsQuestions.add(new Question("Who is Luke Skywalker's father?", new String[]{"Obi-Wan Kenobi", "Darth Vader", "Yoda", "Han Solo"}, 2));
        starWarsQuestions.add(new Question("What is the name of Han Solo’s ship?", new String[]{"Millennium Falcon", "X-Wing", "TIE Fighter", "Death Star"}, 1));
        List<Question> disneyQuestions = new ArrayList<>();
        disneyQuestions.add(new Question("What is the name of the toy cowboy in Toy Story?", new String[]{"Woody", "Buzz", "Rex", "Slinky"}, 1));
        List<Question> harryPotterQuestions = new ArrayList<>();
        harryPotterQuestions.add(new Question("What house is Harry Potter sorted into?", new String[]{"Slytherin", "Hufflepuff", "Gryffindor", "Ravenclaw"}, 3));
        List<Question> narutoQuestions = new ArrayList<>();
        narutoQuestions.add(new Question("What is Naruto's last name?", new String[]{"Uzumaki", "Uchiha", "Hatake", "Nara"}, 1));
        List<Question> attackOnTitanQuestions = new ArrayList<>();
        attackOnTitanQuestions.add(new Question("What is the name of the main protagonist of Attack on Titan?", new String[]{"Eren Yeager", "Armin Arlert", "Levi Ackerman", "Jean Kirstein"}, 1));
        questionBank.put("Star Wars", starWarsQuestions);
        questionBank.put("Disney", disneyQuestions);
        questionBank.put("Harry Potter", harryPotterQuestions);
        questionBank.put("Naruto", narutoQuestions);
        questionBank.put("Attack on Titan", attackOnTitanQuestions);
    }

    private static void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameWon = false;
        Random rand = new Random();
        List<Question> questions = new ArrayList<>(questionBank.get(selectedCategory));
        while (!gameWon && !questions.isEmpty()) {
            int questionIndex = rand.nextInt(questions.size());
            Question question = questions.get(questionIndex);
            boolean questionAnswered = false;

            while (!questionAnswered) {
                System.out.println("Question: " + question.getQuestionText());
                String[] options = question.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.printf("%d. %s  ", i + 1, options[i]);
                }
                System.out.println("\nPress your buzz-in key!");
                char buzzedKey = scanner.next().charAt(0);
                if (playerKeys.containsKey(buzzedKey)) {
                    int player = playerKeys.get(buzzedKey);
                    System.out.printf("Player %d buzzed in! Enter your answer (1-4): ", player);
                    int answer = getInput();

                    if (answer == question.getCorrectAnswer()) {
                        System.out.println("Correct!");
                        playerScores.put(player, playerScores.get(player) + 1);
                        questionAnswered = true;

                        if (playerScores.get(player) >= winningScore) {
                            System.out.printf("Player %d wins!\n", player);
                            gameWon = true;
                        }
                    } else {
                        System.out.println("Wrong answer! Buzz in again.");
                    }
                }
            }
            questions.remove(questionIndex);
        }
        if (questions.isEmpty() && !gameWon) {
            System.out.println("No more questions left!");
        }
    }
}
