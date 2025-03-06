package work_2;

import java.io.*;
import java.util.*;

public class dictionary {
    public static void main(String[] args) {
        BST<String, List<Integer>> bst = new BSTImpl<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Thomas\\Desktop\\hw_3\\work_2\\article.txt"));
             PrintWriter writer = new PrintWriter("index_result.txt")) {

            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    word = word.replaceAll("[^a-zA-Z']", "").toLowerCase();
                    if (word.isEmpty()) continue;
                    List<Integer> lines = bst.search(word);
                    if (lines == null) {
                        lines = new ArrayList<>();
                        bst.insert(word, lines);
                    }
                    if (!lines.contains(lineNum)) {
                        lines.add(lineNum);
                    }
                }
            }

            bst.printInorder(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}