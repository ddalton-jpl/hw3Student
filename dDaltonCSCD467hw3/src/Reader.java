/*
 *  We want to read in a text file line by line and enqueue those lines into the shared queue
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader implements Runnable {
    BufferedReader reader;
    SharedQueue queue = new SharedQueue(10);
    String fileName;

    @Override
    public void run() {
        try {
            reader = new BufferedReader(
                    new FileReader(this.fileName));
            String line = reader.readLine();
            while (line != null) {
                queue.enqueue(line);
                line = reader.readLine();
            }
            queue.setDoneReading(true);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setFileName(String fname) {
        this.fileName = fname;
    }

}
