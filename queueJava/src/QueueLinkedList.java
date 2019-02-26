public class QueueLinkedList {
    private Node top;
    private Node bottom;
    private int size;

    public QueueLinkedList() {
        top = bottom = null;
        size = 0;
    }

    void pushTop(int value) {
        final Node currentTop = top;
        final Node newNode = new Node(value, currentTop, null);
        top = newNode;
        if(currentTop == null)
            bottom = newNode;
        else {
            currentTop.prev = newNode;
        }
        size++;
    }
    void pushBottom(int value) {
        final Node currentBottom = bottom;
        final Node newNode = new Node(value, null, currentBottom);
        bottom = newNode;
        if(currentBottom == null)  // if empty list
            top = newNode;
        else {
            currentBottom.next = newNode;
        }
        size++;
    }
    Node popTop() {
        if(top != null) {
            Node proxyTop = top;
            top = top.next;
            size--;
            return proxyTop;
        } else return null;
    }
    public Node popBottom() {
        if(bottom != null) {
            Node proxyBottom = bottom;
            bottom = bottom.prev;
            size--;
            return proxyBottom;
        } else return null;
    }
    Node getTop() {
        return top;
    }
    Node getBottom() {
        return bottom;
    }
    int getSize() {
        return size;
    }
    boolean isEmpty() {
        return (getSize() == 0);
    }
    void clear() {
        while((top != null) && (bottom != null)) {
            this.popTop();
        }
    }
    QueueLinkedList printQueue() {
        QueueLinkedList temp = new QueueLinkedList();
        while(!this.isEmpty()) {
            System.out.println(bottom.value);
            temp.pushTop(this.popBottom().value);
        }
        System.out.println();
        return temp;
    }
    void fillQueue(int size, int a, int b) {
        while(this.size < size) {
            this.pushTop((int)(Math.random() * (b - a) + a));
        }
    }

}
