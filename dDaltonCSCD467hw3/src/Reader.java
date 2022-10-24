/*
 *  We want to read in a text file line by line and enqueue those lines into the shared queue
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader extends Thread {
    private BufferedReader reader;
    private String fileName;
    private SharedQueue queue;

    public Reader(String inputFileName, SharedQueue inputQueue) {
        this.fileName = inputFileName;
        this.queue = inputQueue;
    }

    @Override
    public synchronized void run() {
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
            this.queue.setDoneReading(true);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
