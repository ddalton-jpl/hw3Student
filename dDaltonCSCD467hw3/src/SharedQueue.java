
public class SharedQueue {
    private class Node {
        private String data;
        private Node next;

        public Node(String inputString) {
            this.data = inputString;
            this.next = null;
        }
    }
    private Node headNode, tailNode;
    private int currentSize;
    private int maxSize;
    private Boolean isDoneReading;

    public SharedQueue(int maxSize) {
        this.headNode = null;
        this.tailNode = null;
        this.currentSize = 0;
        this.maxSize = maxSize;
        this.isDoneReading = false;
    }

    public synchronized boolean isEmpty() {
        if ((this.headNode == null) && (this.tailNode == null) || (this.currentSize == 0)) {
            return true;
        }
        return false;
    }

    public synchronized void enqueue(String inputString) {
        // Check if the queue has reached MaxSize
        while (this.currentSize >= this.maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Node newNode = new Node(inputString);

        if (this.currentSize == 0) {
            this.headNode = newNode;
        } else {
            this.tailNode.next = newNode;
        }

        this.tailNode = newNode;
        this.currentSize++;

        notifyAll();
    }

    public synchronized String dequeue() {
        while (isEmpty()) {
            try {
                System.out.println("Queue is Empty");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String outputString = this.headNode.data;
        this.headNode = this.headNode.next;
        this.currentSize--;

        if (this.currentSize == 0) {
            this.tailNode = null;
        }

        notify();
        return outputString;
    }

    public synchronized void setDoneReading(Boolean doneReading) {
        this.isDoneReading = doneReading;
        notifyAll();
    }

    public synchronized Boolean getDoneReading() {
        return this.isDoneReading;
    }

}
