public class QueueArray {
    private int[] queue;
    private int maxSize;
    private int size;
    private int front;
    private int rear;

    public QueueArray (int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = 0;
        front = -1;
        size = 0;
    }

    public void pushRear(int element) {
        queue[++rear] = element;
        size++;
    }
    public void pushFront(int element) {
        if(this.isFull()) {
            System.out.println("The queue is full, unable to push");
            return;
        } else {
            queue[++front] = element;
            size++;
        }
    }
    public int popRear() {
        int temp = queue[rear++];
        size--;
        return temp;
    }
    public int popFront() {
        int temp = queue[front--];
        size--;
        return temp;
    }
    public int getFront() {
        return queue[front];
    }
    public int getRear() {
        return queue[rear];
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public boolean isFull() {
        return (size == maxSize);
    }
    public int[] clear() {
        if(this.isEmpty()) {
            System.out.println("The stack is empty");
            return queue;
        } else {
            queue = new int[0];
            rear = -1;
            front = 0;
            size = 0;
            return queue;
        }
    }
    public void printQueue() {
        for(int counter = 0; counter < size; counter++) {
            System.out.printf("%d, ", queue[counter]);
        }
        System.out.println();
    }
    public void insertRand(int a, int b) {
        while(!this.isFull()) {
            this.pushFront(((int)(Math.random() * (b - a) + a)));
        }
    }
}
