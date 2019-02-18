public class LinkedListStack {
    Node top = null;
    int size = 1;

    public void push(Object node) {
        if (node == null || !(node instanceof Node)){
            return;
        } else {
            Node newNode = (Node)node;
            newNode.next = top;
            top = newNode;
            size++;
        }
    }

    public Node pop() {
        if(top != null) {
            Node proxyTop = top;
            top = top.next;
            size--;
            return proxyTop;
        } else return null;
    }

    public Node top() {
        return top;
    }

    public void clear() {
        while(top != null) {
            this.pop();
        }
    }

    public int size() {
        if (top == null) {
            return 0;
        } else {
            return size;
        }
    }

    public boolean isEmpty() {
        return (top == null);
    }

    public LinkedListStack refresh(LinkedListStack stack) {
        LinkedListStack temp = new LinkedListStack();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        stack = temp;
        return stack;
    }

    public LinkedListStack randPush(LinkedListStack stack, int size, int a, int b) { // [a; b]
        while (stack.size() <= size) {
            stack.push(new Node((int)(Math.random() * (b - a) + a)));
        }
        return stack;
    }

    public void printStack(LinkedListStack stack) {
        LinkedListStack temp = new LinkedListStack();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            temp.push(node);
        }
        stack = temp.refresh(temp);
    }
}
