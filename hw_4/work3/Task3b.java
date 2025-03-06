package work3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Task3b {
    public static void main(String[] args) throws IOException {
        Set<String> words = new HashSet<>(Files.readAllLines(Paths.get("C:\\Users\\Thomas\\Desktop\\hw_4\\work3\\words5.txt")));
        Graph graph = Graph.buildGraph(words);
        WordLadderGame game = new WordLadderGame(graph);
        game.startNewGame();

        System.out.println("Start word: " + game.getStartWord());
        System.out.println("End word: " + game.getEndWord());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> userPath = new ArrayList<>();
        userPath.add(game.getStartWord());
        String currentWord = game.getStartWord();

        while (true) {
            System.out.println("Current word: " + currentWord);
            System.out.print("Next word: ");
            String nextWord = reader.readLine().trim().toLowerCase();

            if (!words.contains(nextWord)) {
                System.out.println("Invalid word. Try again.");
                continue;
            }

            if (!graph.getNeighbors(currentWord).contains(nextWord)) {
                System.out.println("Not a valid adjacent word. Try again.");
                continue;
            }

            if (userPath.contains(nextWord)) {
                System.out.println("Word already used. Try again.");
                continue;
            }

            userPath.add(nextWord);
            currentWord = nextWord;

            if (currentWord.equals(game.getEndWord())) {
                System.out.println("Congratulations! You reached the end.");
                if (game.isShortest(userPath)) {
                    System.out.println("You used the shortest path!");
                } else {
                    System.out.println("But it's not the shortest path.");
                }
                break;
            }
        }
    }
}

class WordLadderGame {
    private Graph graph;
    private String startWord;
    private String endWord;
    private List<String> shortestPath;

    public WordLadderGame(Graph graph) {
        this.graph = graph;
    }

    public void startNewGame() {
        List<String> words = new ArrayList<>(graph.getAllWords());
        Random random = new Random();
        while (true) {
            int startIndex = random.nextInt(words.size());
            int endIndex = random.nextInt(words.size());
            if (startIndex == endIndex) continue;
            startWord = words.get(startIndex);
            endWord = words.get(endIndex);
            shortestPath = graph.bfsShortestPath(startWord, endWord);
            if (!shortestPath.isEmpty()) {
                break;
            }
        }
    }

    public String getStartWord() {
        return startWord;
    }

    public String getEndWord() {
        return endWord;
    }

    public boolean isShortest(List<String> userPath) {
        return userPath.size() == shortestPath.size();
    }
}