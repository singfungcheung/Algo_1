public class main {
    public static void main(String[] args) {
        Percolation x = new Percolation(5);
        //x.open(0,3);
        //System.out.println("does this run");
        x.open(3,5);
        x.open(2,2);
        x.isOpen(1,3);
        x.isOpen(1,1);
        x.isOpen(0,1);
        System.out.println(x.numberOfOpenSites());

    }
}
