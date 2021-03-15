import java.io.*;

public class exampleA {
    public static void main(String [] args) {
        // System.out.println("Hi world!! -> " + args.length);
        // int numeroEstudiante; // Solo ENTEROS
        // double suma; // SON ENTEROS Y DECIMAS
        // short puertas; // ENTERO
        // boolean isViernes = false; // true
        // String nombre = "Ronald";
        // long id;
        // char initName = 'A';
        //
        exampleA example = new exampleA();
        // String mensaje = example.saludar("Veronica");
        // System.out.println(mensaje);
        //
        // example.imprimirFecha();
        //
        // // if() else
        // // for, while, do while
        // for (int i=0; i < 5; i++) { // SIEMPRE CUANDO LA CONDICION ES TRUE
        //     System.out.println("Holaaaa -> " + i);
        // }
        //
        // for (int i=5; i>0; i--) {
        //     System.out.println("Como estas -> " + i);
        // }
        //
        // System.out.println(1 > 9);
        // System.out.println(1 < 9);
        // System.out.println(1 >= 9);
        // System.out.println(1 <= 9);
        // System.out.println(1 == 9);
        // System.out.println(1 != 9);
        //
        // // Operadores logicos evalucion
        // // && , ||
        // // && -> Operado Y
        // // || -> Or
        // int edad = 20;
        // boolean estudia = false;
        // if (edad > 18 && estudia == true) { // Cuando es TRUE
        //     System.out.println("Es mayor de edad y estudia");
        // } else {
        //     System.out.println("No es mayor de edad y no estudia");
        // }
        //
        // int contador = 0;
        // while (contador < 10) { // TRUE
        //     System.out.println("while");
        //     contador++; // contador = contador + 1;
        // }
        //
        // contador = 0;
        //
        // do {
        //     System.out.println("do while");
        //     contador++; // contador = contador + 1;
        // } while(contador < 10); // TRUE
        //
        // // Operador ++;
        // int numeroPaises = 0;
        // // System.out.println(numeroPaises++);
        // int s = numeroPaises++; // 1
        // System.out.println(s);
        // numeroPaises = 0;
        // int g = ++numeroPaises;
        // System.out.println(g);
        //
        // // MOD %
        // System.out.println(5%2); // IMPAR
        // System.out.println(10%2); // PAR

        example.ejercicio1();
    }

    public String saludar(String name) {
        String mensaje = "Hola " + name;
        return mensaje;
    }

    public void imprimirFecha() {
        System.out.println("03/12/2020");
    }

    public void ejercicio1(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // GENERICA
        System.out.println("Introduce un numero: ");
        try {
            // int num = Integer.valueOf(in.readLine().trim()).intValue();
            int num = Integer.valueOf(in.readLine().trim());
            // if (num == 0) throw new Ejercicio1Exception("Error, numero nulo detectado");
            if (num == 0) throw new Exception("Error, numero nulo detectado");
            if ((num % 2) == 0)
                System.out.println("El numero introducido es PAR");
            else
                System.out.println("El numero introducido es IMPAR");
        // } catch(Ejercicio1Exception e){
            // System.out.println(e.getMessage());
        } catch(NumberFormatException e1) {
            System.out.println(" Error.."+ e1.getMessage());
        } catch(Exception e2) {
            System.out.println(e2.getMessage());
        }
    }

    public class Ejercicio1Exception extends Exception {

     /** Creates a new instance of Ejercicio1Exception */
     public Ejercicio1Exception(String mensaje) {
     super(mensaje);
     }
    }
}
