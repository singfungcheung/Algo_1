import edu.princeton.cs.algs4.StdIn;
// import edu.princeton.cs.algs4.StdOut; // Not used
import edu.princeton.cs.algs4.StdRandom;
public class RandomWord {
    public static void main(String[] args) {
        // Read the input
//        String name = StdIn.readString();
//        if (StdIn.isEmpty()) {
//            System.out.print("Empty");
//        } else {
//            System.out.println("Not Empty");
//        }
//        System.out.println(name);
        String name = "";
        String winner = "";
        int counter = 0;
        while (!StdIn.isEmpty()) {
            counter++;
            name = StdIn.readString();
//            System.out.println(name);
            double prob = (double) 1/counter;
//            System.out.println(prob);
            if (StdRandom.bernoulli(prob)) {
                winner = name;
            }
        }
        System.out.println(winner);

    }
}
