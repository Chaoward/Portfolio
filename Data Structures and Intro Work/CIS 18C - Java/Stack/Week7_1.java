public class Week7_1 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; ++i) {
            stack.push(2 * i);
        }

        System.out.println("Size: " + stack.size());
        while (stack.hasNext()) {
            System.out.println("Pop! Value: " + stack.next().intValue());
            stack.pop();
            System.out.println("Size: " + stack.size());
        }

        System.out.println("EMPTY!");
    }
}
