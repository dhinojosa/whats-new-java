//All of the following classes must be in the same file

class Zoom {
    void main() {
        Helper.run("!");
    }

}

class Prog {
    void main() { Helper.run("?"); }

}

class Helper {
    static void run(String c) { System.out.println("Hello" + c); }
}


//Next we will take Prog and put it in Prog.java and Helper and put in Helper.java
//Will it work? In Java 17? In Java 22+?
