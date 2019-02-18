public class LinkedListStack {
    private Node top = null;
    private int size = 1;

    void push(Object node) {
        if (!(node instanceof Node)){
            return;
        } else {
            Node newNode = (Node)node;
            newNode.next = top;
            top = newNode;
            size++;
        }
    }

    Node pop() {
        if(top != null) {
            Node proxyTop = top;
            top = top.next;
            size--;
            return proxyTop;
        } else return null;
    }

    Node top() {
        return top;
    }

    void clear() {
        while(top != null) {
            this.pop();
        }
    }

    private int size() {
        if (top == null) {
            return 0;
        } else {
            return size;
        }
    }

    boolean isEmpty() {
        return (top == null);
    }

    private LinkedListStack refresh(LinkedListStack stack) {
        LinkedListStack temp = new LinkedListStack();
        while (stack.isEmpty()) {
            temp.push(stack.pop());
        }
        stack = temp;
        return stack;
    }

    LinkedListStack randPush(LinkedListStack stack, int size, int a, int b) { // [a; b]
        while (stack.size() <= size) {
            stack.push(new Node((int)(Math.random() * (b - a) + a)));
        }
        return stack;
    }

    void printStack(LinkedListStack stack) {
        LinkedListStack temp = new LinkedListStack();
        while (stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            temp.push(node);
        }
        stack = temp.refresh(temp);
    }
}
