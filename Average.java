/**
    Compilar (W10):
        javac -cp "algs4.jar" Average.java
    Ejecutar (W10):
        java -cp ".;algs4.jar" Average

    Link of reference:
    https://algs4.cs.princeton.edu/code/
*/

import edu.princeton.cs.algs4.*;

public class Average {
    public static void main (String[] args) {
        double _suma = 0.0;
        int _contador = 0;
        //StdOut.printf(" ++++ Excersise One ++++\n");
        System.out.printf(" ++++ Excersise One ++++\n");
        while(!StdIn.isEmpty()) { // ctrl-z para salir del lazo
            _suma += StdIn.readDouble();
            _contador++;
        }
        double _avg = _suma / _contador;
        StdOut.printf("Average is %.5f\n", _avg);
    }
}