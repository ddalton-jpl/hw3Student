import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread {
    private SharedQueue queue;
    private String pattern;

    public Searcher(String inputPattern, SharedQueue inputQueue) {
        this.pattern = inputPattern;
        this.queue = inputQueue;
    }

    public void run() {
        int currentRow = 0;
        int totalFound = 0;
        try {
            while (queue.getDoneReading() == false || queue.isEmpty() == false) {
                String line = this.queue.dequeue();

                Pattern pattern = Pattern.compile(this.pattern);
                Matcher matcher = pattern.matcher(line);

                int count = 0;
                while (matcher.find()) {
                    count++;
                    // System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
                }

                totalFound += count;
                currentRow++;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (this.queue.getDoneReading() == true) {
            System.out.println("Total number of lines searched is " + currentRow);
            System.out.println("Total occurance of pattern " + this.pattern + "is " + totalFound);
        }
    }

}
