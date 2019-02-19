public class LabLinkedList {

   public static void replaceMinMax(LinkedListStack stack) {
       LinkedListStack temp = new LinkedListStack();
       Node max = stack.top(); // Creating max and min templates using top element from stack
       Node min = stack.top();

       while(!stack.isEmpty()) { // Finding max and min node values
           Node node = stack.pop();
           if (node.value > max.value) {
               max = node;
           }
           if (node.value < min.value) {
               min = node;
           }
           temp.push(new Node(node.value)); // Pushing every node into a temporary stack
       }

       while(!temp.isEmpty()) { // Popping elements from the temporary stack and pushing them into a new stack
           Node node = temp.pop();

           if (node.value == max.value) { // As the proccess goes, min and max value objects are replaced
               stack.push(new Node(min.value));
           } else if (node.value == min.value) {
               stack.push(new Node(max.value));
           } else {
               stack.push(new Node(node.value));
           }
       }
   }

    public static void main(String[] args) { // Testing
        LinkedListStack listStack = new LinkedListStack();
        listStack.push(new Node(10)); //15
        listStack.push(new Node(20)); //10
        listStack.push(new Node(30)); //30
        listStack.push(new Node(70)); //20
        listStack.push(new Node(15)); //70

        replaceMinMax(listStack);
        listStack.printStack(listStack);
    }

}