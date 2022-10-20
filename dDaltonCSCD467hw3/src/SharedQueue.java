
import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    private class Node {
        private Object data;
        private Node next;

        public Node(Object inputObject) {
            this.data = inputObject;
            this.next = null;
        }
    }
    private Node headNode, tailNode;
    private int currentSize;
    private int maxSize;

    public SharedQueue(int maxSize) {
        this.headNode = null;
        this.tailNode = null;
        this.currentSize = 0;
        this.maxSize = maxSize;
    }

    public synchronized void enqueue(Object inputObject) {
        // Check if the queue has reached MaxSize
        while (this.currentSize >= this.maxSize) {
            try {
                System.out.println("The Queue is Full");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error in enqueue wait");
                e.printStackTrace();
            }
        }

        Node newNode = new Node(inputObject);

        if (this.currentSize == 0) {
            this.headNode = newNode;
        } else {
            this.tailNode.next = newNode;
        }

        this.tailNode = newNode;
        this,.currentSize++;

        System.out.println("Produced " + val + "!" );

        notifyAll();
    }

    public synchronized Object dequeue() {
        while (isEmpty()) {
            try {
                System.out.println("Queue is Empty");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error in dequeue");
                e.printStackTrace();
            }
        }

        Object outpuObject = this.headNode.data;
        this.headNode = this.headNode.next;
        this.currentSize--;

        if (this.currentSize == 0) {
            this.tailNode = null;
        }

        notify();
        return outpuObject;
    }

    public synchronized boolean isEmpty() {
        if ((this.headNode == null) && (this.tailNode == null) || (this.currentSize == 0)) {
            return true;
        }
        return false;
    }

}
