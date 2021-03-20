/*
 * javac -cp ".;algs4.jar" ExperimentW2.java // Ya esta compilada la clase Point.java
 * java -cp ".;algs4.jar" ExperimentW2 <size_array_digit> <name_file> . <name_file>
 */
import edu.princeton.cs.algs4.*;
import java.io.File;

public class ExperimentW2 {
    public static void main(String [] args) {
        ExperimentW2 ew2 = new ExperimentW2();
        ew2.sorterNumbers(Integer.parseInt(args[0]));
        // StdOut.println();
        // ew2.stringSorter(args[1]);
        // StdOut.println();
        // ew2.filesSorter(args[2]);
        StdOut.println();
        ew2.pointSorter(args[3]);
        StdOut.println();
        // ew2.temperatureSorter();
    }

    public void sorterNumbers(int sizeElements) {
        Integer [] arrayNumeros = new Integer[sizeElements]; // On execution?????
        for (int i = 0; i < sizeElements; i++) arrayNumeros[i] = StdRandom.uniform(100);
        for (int element : arrayNumeros) StdOut.println(element);
        // Insertion.sort(arrayNumeros); // Sorter elements "Numbers" asc
        ShellsortCustom.sort(arrayNumeros);
        StdOut.println();
        for (int element : arrayNumeros) StdOut.println(element);
    }

    public void stringSorter(String nameFile) {
        String [] arrayWords = In.readStrings(nameFile);
        for (String word : arrayWords) StdOut.println(word);
        Insertion.sort(arrayWords); // Sorter elements "text" asc
        StdOut.println();
        for (String word : arrayWords) StdOut.println(word);
    }

    public void filesSorter(String path) {
        File directory = new File(path);
        File [] files = directory.listFiles();
        for (File element : files) StdOut.println(element.getName());
        Insertion.sort(files); // Sorter elements "name files" asc
        StdOut.println();
        for (File element : files) StdOut.println(element.getName());
    }

    public void pointSorter(String nameFile) {
        In input = new In(nameFile);
        int sizeArray = input.readInt();
        Point [] arrayPoint = new Point[sizeArray];
        for (int index = 0; index < sizeArray; index++) arrayPoint[index] = new Point(input.readInt(), input.readInt());
        for (Point el : arrayPoint) StdOut.println(el.toString());
        // Insertion.sort(arrayPoint); // OK
        // SelectionCustom.sort(arrayPoint); // OK
        // InsertionCustom.sort(arrayPoint); // OK
        ShellsortCustom.sort(arrayPoint);
        StdOut.println();
        for (Point el : arrayPoint) StdOut.println(el.toString());
    }

    public void temperatureSorter() {
        Temperature [] arrayTemperature = new Temperature[4];
        arrayTemperature[0] = new Temperature(10.16);
        arrayTemperature[1] = new Temperature(10.08);
        arrayTemperature[2] = new Temperature(10.01);
        arrayTemperature[3] = new Temperature(10.21);
        for (Temperature el : arrayTemperature) StdOut.println(el);
        Insertion.sort(arrayTemperature);
        StdOut.println();
        for (Temperature el : arrayTemperature) StdOut.println(el);
    }
}
