package work3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Task3a {
    public static void main(String[] args) throws IOException {
        Set<String> words = new HashSet<>(Files.readAllLines(Paths.get("C:\\Users\\Thomas\\Desktop\\hw_4\\work3\\words5.txt")));
        Graph graph = Graph.buildGraph(words);
        List<String> noLadderWords = new ArrayList<>();

        for (String word : graph.getAllWords()) {
            if (graph.getNeighbors(word).isEmpty()) {
                noLadderWords.add(word);
            }
        }

        Files.write(Paths.get("noladder.txt"), noLadderWords);
    }
}
