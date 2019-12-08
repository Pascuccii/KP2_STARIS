package by.bsuir.view;

public final class Printer {
    private Printer(){

    }

    public static void print(String message){
        System.out.println(message);
    }

    public static void print(Object object){
        System.out.println(object);
    }
}
