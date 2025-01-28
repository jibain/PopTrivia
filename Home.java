import java.util.*;
//need to be added:
//difficulties, more questions
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
        starWarsQuestions.add(new Question("Which limb does Luke lose?", new String[]{"Right hand", "Left hand", "Right leg", "Left leg"}, 1));
        starWarsQuestions.add(new Question("Where did Obi-Wan take Luke after birth?", new String[]{"Dantooine", "Naboo", "Tattooine", "Alderaan"}, 3));
        starWarsQuestions.add(new Question("What year did A New Hope release?", new String[]{"1977", "1978", "1969", "1981"}, 1));
        starWarsQuestions.add(new Question("What is Rey's last name?", new String[]{"Skywalker", "Solo", "Sebulba", "Palpatine"}, 4));
        starWarsQuestions.add(new Question("Who adopted Leia?", new String[]{"Obi-Wan", "Nute Gunray", "Bail Organa", "Wedge Antilles"}, 1));
        starWarsQuestions.add(new Question("What is the path to the dark side according to Yoda?", new String[]{"Anger", "Fear", "Hate", "Suffering"}, 2));
        starWarsQuestions.add(new Question("What color is the darksaber?", new String[]{"Black", "White", "Brown", "Gray"}, 1));
        starWarsQuestions.add(new Question("Who is known for saying I have spoken?", new String[]{"Ackbar", "Kuill", "Grogu", "Din"}, 2));
        starWarsQuestions.add(new Question("Where does Rey find Luke?", new String[]{"Naboo", "Endor", "Ahch-To", "Jakku"}, 3));
        starWarsQuestions.add(new Question("What material is Din Djarin's spear made out of?", new String[]{"Adamantium", "Steel", "Beskar", "Vibranium"}, 3));
        starWarsQuestions.add(new Question("Jedi mind tricks don't work on me, only ______.", new String[]{"Credits", "Lightsabers", "Money", "Drugs"}, 3));
        starWarsQuestions.add(new Question("What theme music plays as Anakin goes to rescue his mother in Attack of the Clones?", new String[]{"Duel of the Fates", "Across the Stars", "Battle of the Heroes", "The Force Theme"}, 1));
        starWarsQuestions.add(new Question("What is Count Dooku's Sith name?", new String[]{"Sifo-Dyas", "Tyrannus", "Tricerus", "Plagueis"}, 2));
        starWarsQuestions.add(new Question("Where is Cloud City located?", new String[]{"Coruscant", "Endor", "Dantooine", "Bespin"}, 1));
        starWarsQuestions.add(new Question("How many parsecs did it take Han Solo to complete the Kessel Run?", new String[]{"14", "12", "11", "1000"}, 2));
        starWarsQuestions.add(new Question("How long does it take for the Sarlacc Pit to digest a person?", new String[]{"10 years", "1,000 years", "500 years", "1,500 years"}, 2));
        starWarsQuestions.add(new Question("Where does Padme give birth to twins?", new String[]{"Naboo", "Mustafar", "Polis Massa", "Coruscant"}, 3));
        List<Question> disneyQuestions = new ArrayList<>();
        disneyQuestions.add(new Question("What is the name of the toy cowboy in Toy Story?", new String[]{"Woody", "Buzz", "Rex", "Slinky"}, 1));
        disneyQuestions.add(new Question("What Pixar character does Owen Wilson voice?", new String[]{"Remy", "Lightning McQueen", "Randall", "Syndrome"}, 2));
        disneyQuestions.add(new Question("Which movie involves a villain named Lotso?", new String[]{"Moana", "Toy Story 3", "Brave", "Incredibles 2"}, 2));
        disneyQuestions.add(new Question("Which princess sings Once Upon a Dream?", new String[]{"Aurora", "Snow White", "Ariel", "Belle"}, 1));
        disneyQuestions.add(new Question("Which one is not one of the Seven Dwarves?", new String[]{"Sleepy", "Grumpy", "Angry", "Dopey"}, 3));
        disneyQuestions.add(new Question("What is the name of the monkey in Aladdin?", new String[]{"Caesar", "Rafiki", "Abu", "Boots"}, 3));
        disneyQuestions.add(new Question("Name a stepsister of Cinderella's:", new String[]{"Anastasia", "Ruth", "Martha", "Cordelia"}, 1));
        disneyQuestions.add(new Question("What does Hakuna Matata mean?", new String[]{"", "", "It's okay", "No worries"}, 4));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        disneyQuestions.add(new Question("", new String[]{"", "", "", ""}, 1));
        List<Question> harryPotterQuestions = new ArrayList<>();
        harryPotterQuestions.add(new Question("What house is Harry Potter sorted into?", new String[]{"Slytherin", "Hufflepuff", "Gryffindor", "Ravenclaw"}, 3));
        harryPotterQuestions.add(new Question("", new String[]{"", "", "", ""}, 3));
        List<Question> narutoQuestions = new ArrayList<>();
        narutoQuestions.add(new Question("What is Naruto's last name?", new String[]{"Uzumaki", "Uchiha", "Hatake", "Nara"}, 1));
        narutoQuestions.add(new Question("Who is the Ghost of the Uchiha?", new String[]{"Obito", "Madara", "Itachi", "Sasuke"}, 2));
        narutoQuestions.add(new Question("How many genin went to rescue Sasuke?", new String[]{"Seven", "Six", "Five", "Three"}, 3));
        narutoQuestions.add(new Question("Who gave a speech to the shinobi alliance before the Fourth Great Ninja War?", new String[]{"Tsunade", "Naruto", "Kakashi", "Gaara"}, 4));
        narutoQuestions.add(new Question("Who created the Rasengan?", new String[]{"Jiraiya", "Naruto", "Minato", "Kakashi"}, 3));
        narutoQuestions.add(new Question("Which tailed beast is sealed inside Naruto?", new String[]{"Eight Tails", "Two Tails", "Three Tails", "Nine Tails"}, 4));
        narutoQuestions.add(new Question("Why did Sasuke run away?", new String[]{"To kill Orochimaru", "To run away with Sakura", "To become strong enough to kill his brother", "To get away from Naruto"}, 3));
        narutoQuestions.add(new Question("Who does Pain kill to make Naruto lose control?", new String[]{"Kakashi", "Hinata", "Sasuke", "Jiraiya"}, 2));
        narutoQuestions.add(new Question("Who brought Madara back in the Great Shinobi War?", new String[]{"Naruto", "Orochimaru", "Kabuto", "Itachi"}, 1));
        narutoQuestions.add(new Question("How many times did Naruto fail the graduation test at the Ninja Academy?", new String[]{"1", "2", "3", "4"}, 3));
        narutoQuestions.add(new Question("Who did Kakashi get his eye from?", new String[]{"Madara", "Shisui", "Obito", "Pain"}, 3));
        narutoQuestions.add(new Question("What is the first arc in Naruto called?", new String[]{"Konoha Crush", "The Land of Waves", "Chunin Exams", "Homecoming"}, 2));
        narutoQuestions.add(new Question("What jutsu is Naruto most proficient in?", new String[]{"Rasengan", "Shadow Clone Jutsu", "Rasenshuriken", "Mugetsu"}, 2));
        narutoQuestions.add(new Question("How many questions did Naruto get right in the Chunin Exam written test?", new String[]{"1", "4", "0", "3"}, 3));
        narutoQuestions.add(new Question("What is Kakashi's dog's name?", new String[]{"Pakkun", "Tim", "Tenten", "Tonton"}, 1));
        narutoQuestions.add(new Question("What jutsu did Kakashi teach Sasuke?", new String[]{"Rasengan", "Fireball", "Chidori", "64 Palms"}, 3));
        narutoQuestions.add(new Question("What is Pain's real name?", new String[]{"Kakuzu", "Yahiko", "Nagato", "Yagito"}, 3));
        narutoQuestions.add(new Question("Who founded the Akatsuki?", new String[]{"Madara", "Yahiko", "Obito", "Pain"}, 2));
        narutoQuestions.add(new Question("How many jutsu elements are there?", new String[]{"5", "6", "3", "4"}, 2));
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
