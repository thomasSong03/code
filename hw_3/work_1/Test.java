package work_1;

import java.io.*;
import java.util.regex.*;

public class Test {
    public static void main(String[] args) {
        BST<String, String> bst = new BSTImpl<>();
        try (
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Thomas\\Desktop\\hw_3\\work_1\\BST_testcases.txt"));
            PrintWriter pw = new PrintWriter("BST_result1.txt");
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("+")) { 
                    processInsert(bst, line, pw);
                } else if (line.startsWith("-")) { 
                    processRemove(bst, line, pw);
                } else if (line.startsWith("?")) { 
                    processSearch(bst, line, pw);
                } else if (line.startsWith("=")) { 
                    processUpdate(bst, line, pw);
                } else if (line.startsWith("#")) { 
                    bst.showStructure(pw);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processInsert(BST<String, String> bst, String line, PrintWriter pw) {
        Matcher m = Pattern.compile("\\+\\s*\\(\\s*([^,]+)\\s*,\\s*\"(.*?)\"\\s*\\)").matcher(line);
        if (m.find()) {
            String key = m.group(1).trim();
            String value = m.group(2).trim();
            bst.insert(key, value);
        }
    }

    private static void processRemove(BST<String, String> bst, String line, PrintWriter pw) {
        Matcher m = Pattern.compile("-\\s*\\(\\s*([^)]+)\\s*\\)").matcher(line);
        if (m.find()) {
            String key = m.group(1).trim();
            String value = bst.remove(key);
            if (value != null) {
                pw.println("remove success ---" + key + " " + value);
            } else {
                pw.println("remove unsuccess ---" + key);
            }
        }
    }

    private static void processSearch(BST<String, String> bst, String line, PrintWriter pw) {
        Matcher m = Pattern.compile("\\?\\s*\\(\\s*([^)]+)\\s*\\)").matcher(line);
        if (m.find()) {
            String key = m.group(1).trim();
            String value = bst.search(key);
            if (value != null) {
                pw.println("search success ---" + key + " " + value);
            } else {
                pw.println("search unsuccess ---" + key);
            }
        }
    }

    private static void processUpdate(BST<String, String> bst, String line, PrintWriter pw) {
        Matcher m = Pattern.compile("=\\s*\\(\\s*([^,]+)\\s*,\\s*\"(.*?)\"\\s*\\)").matcher(line);
        if (m.find()) {
            String key = m.group(1).trim();
            String newValue = m.group(2).trim();
            boolean success = bst.update(key, newValue);
            if (success) {
                pw.println("update success ---" + key + " " + newValue);
            } else {
                pw.println("update unsuccess ---" + key);
            }
        }
    }
}