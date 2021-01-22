package fr.benoitne.libraryapi;

import java.util.ArrayList;
import java.util.List;

public class SandBox {

    public static void main(String[] args) {
        List<String> waitingLine = new ArrayList<>();
        waitingLine.add("toto");
        waitingLine.add("tata");
        waitingLine.add("tutu");
        waitingLine.add("titi");

        System.out.println(waitingLine.get(0));
        System.out.println(waitingLine.size());

        waitingLine.remove(0);

        System.out.println(waitingLine.get(0));
        System.out.println(waitingLine.size());

    }




}
