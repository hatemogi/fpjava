package fpjava;

public class App {
    public String getGreeting() {
        return "Hello Functional Programming!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
