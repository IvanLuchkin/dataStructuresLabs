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
    } // Add element to the top

    Node pop() {
        if(top != null) {
            Node proxyTop = top;
            top = top.next;
            size--;
            return proxyTop;
        } else return null;
    } // Remove element from the top and read (return) it

    Node top() {
        return top;
    } // Read (return) element from the top

    void clear() {
        while(top != null) {
            this.pop();
        }
    } // Remove all elements in stack

    private int size() {
        if (top == null) {
            return 0;
        } else {
            return size;
        }
    } // Return amount of elements in the stack

    boolean isEmpty() { // Check if stack is empty
        return (top == null);
    }

    private LinkedListStack refresh(LinkedListStack stack) {
        LinkedListStack temp = new LinkedListStack();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        stack = temp;
        return stack;
    } // Turn stack upside down

    LinkedListStack randPush(LinkedListStack stack, int size, int a, int b) { // [a; b]
        while (stack.size() <= size) {
            stack.push(new Node((int)(Math.random() * (b - a) + a)));
        }
        return stack;
    } /* Fill stack with Nodes
                                                                                     with random values [a; b]
                                                                                  */
    void printStack(LinkedListStack stack) { // To print values of all objects in stack
        LinkedListStack temp = new LinkedListStack();
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.value);
            temp.push(node);
        }
        stack = temp.refresh(temp);
    }
}
