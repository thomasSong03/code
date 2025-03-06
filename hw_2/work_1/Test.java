import java.io.*;

public class Test {
    public static void main(String[] args) throws Exception {
        List<Character> list = new ArrayList(); // 切换实现类测试
        BufferedReader br = new BufferedReader(new FileReader("list_testcase.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        String line;
        while ((line = br.readLine()) != null) {
            String[] commands = line.trim().split("\\s+");
            for (String cmd : commands) {
                processCommand(cmd, list, pw);
            }
            list.showStructure(pw);
            pw.flush();
        }
    }

    private static void processCommand(String cmd, List<Character> list, PrintWriter pw) {
        try {
            if (cmd.startsWith("+")) {
                list.insert(cmd.charAt(1));
            } else if (cmd.equals("<")) {
                list.gotoPrev();
            } else if (cmd.equals(">")) {
                list.gotoNext();
            } else if (cmd.startsWith("-")){
                list.remove();
            } else if (cmd.equals("=")) {
                list.replace(cmd.charAt(1));
            } else if (cmd.equals("#")){
                list.gotoBeginning();
            } else if (cmd.equals("*")){
                list.gotoEnd();
            } else if (cmd.equals("~")){
                list.clear();
            }
        } catch (ListException e) {
            pw.println("Error: " + e.getMessage());
        }
    }
}