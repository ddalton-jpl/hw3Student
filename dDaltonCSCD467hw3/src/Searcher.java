import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread{
    // TODO figure out what the valid maxSize is
    SharedQueue queue;
    int currentRow = 0;
    int totalFound = 0;
    String pattern;

    public Searcher(String inputPattern, SharedQueue inputQueue) {
        this.pattern = inputPattern;
        this.queue = inputQueue;
    }

    public void run() {
        searchPattern();
    }

    // Search a text file for a pattern
	public synchronized int searchPattern(){
        while (queue.isEmpty() == false && queue.getDoneReading() == false) {
            String line = queue.dequeue();
            totalFound += searchLine(line, this.pattern);
            currentRow ++;
        }
        System.out.println("Total number of lines searched is " + currentRow);
        return totalFound;
	}//end of searchPattern
	
	public synchronized static int searchLine(String line, String patt){
		Pattern pattern = Pattern.compile(patt);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while(matcher.find()) {
		    count++;
		    System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
		}
		return count;
	}


}
