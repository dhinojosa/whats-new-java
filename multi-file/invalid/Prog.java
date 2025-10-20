// Prog.java
class Prog {
    public static void main(String[] args) { Helper.run(); Aux.cleanup(); }
}
class Aux {
    static void cleanup() {
        System.out.println("Running Cleanup in Prog.Aux");
    }
}
