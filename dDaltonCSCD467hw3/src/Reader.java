/*
 *  We want to read in a text file line by line and enqueue those lines into the shared queue
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader extends Thread {
    BufferedReader reader;
    String fileName;
    SharedQueue queue;

    public Reader(String inputFileName, SharedQueue inputQueue) {
        this.fileName = inputFileName;
        this.queue = inputQueue;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(
                    new FileReader(this.fileName));
            String line = reader.readLine();
            while (line != null) {
                if (!line.trim().isEmpty()) {
                    queue.enqueue(line);
                }
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
