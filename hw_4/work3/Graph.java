package work3;

import java.util.*;

public class Graph {
    private Map<String, Set<String>> adjacencyList;

    // 构造函数，初始化邻接表
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // 向邻接表中添加一个单词
    public void addWord(String word) {
        adjacencyList.putIfAbsent(word, new HashSet<>());
    }

    // 添加边的方法，参数为两个字符串，分别表示两个单词
    public void addEdge(String word1, String word2) {
        adjacencyList.putIfAbsent(word1, new HashSet<>());
        adjacencyList.putIfAbsent(word2, new HashSet<>());
        adjacencyList.get(word1).add(word2);
        adjacencyList.get(word2).add(word1);
    }

    // 获取给定单词的邻居集合
    public Set<String> getNeighbors(String word) {
        return adjacencyList.getOrDefault(word, Collections.emptySet());
    }

    // 获取所有单词
    public Set<String> getAllWords() {
        return adjacencyList.keySet();
    }

    // 根据给定的单词集合构建图
    public static Graph buildGraph(Set<String> words) {
        Graph graph = new Graph();
        for (String word : words) {
            graph.addWord(word);
        }
        for (String word :words){
            List<String> neighbors = generateNeighbors(word, words);
            for (String neighbor : neighbors) {
                graph.addEdge(word, neighbor);
            }
        }
        return graph;
    }

    // 生成与给定单词相邻的单词列表
    private static List<String> generateNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = original;
        }
        return neighbors;
    }

    // 使用广度优先搜索算法找到从start到end的最短路径
    public List<String> bfsShortestPath(String start, String end) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(end)) {
            return Collections.emptyList();
        }

        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                break;
            }
            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parentMap.containsKey(end)) {
            return Collections.emptyList();
        }

        List<String> path = new ArrayList<>();
        String node = end;
        while (node != null) {
            path.add(0, node);
            node = parentMap.get(node);
        }
        return path;
    }
}