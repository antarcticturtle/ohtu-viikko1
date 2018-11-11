package ohtu.main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    public static void main(String[] args) {
        int storageCapacity = 1;
        Varasto varasto = new Varasto(storageCapacity);
        System.out.println(varasto);
    }
}
