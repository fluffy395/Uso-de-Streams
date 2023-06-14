package Examen_Ordinario;

// Estas son las librerias con las que trabajan algunas funciones y metodos
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class UsosStream {
    public static void main(String[] args) {
        // Estas lineas de codigo leen los archivos y los guarda en un Array
        String[] nombresHombres = leerArchivo("nombres_hombres.txt");
        String[] nombresMujeres = leerArchivo("nombres_mujeres.txt");
        // Aquí lee el arreglo del archivo de mujeres y usa el metodo toUpperCase para convertir los valores en mayusculas
        Stream<String> nombresMujeresMayusculas = Arrays.stream(nombresMujeres)
                .map(String::toUpperCase);
        System.out.println("Nombres de mujeres en mayúsculas:");
        nombresMujeresMayusculas.forEach(System.out::println); // El metodo forEach recorre la lista de los nombres de mujeres

        Scanner scanner = new Scanner(System.in); // El método Scanner permite obtener datos de cualquier tio (int, double, String)
        System.out.print("Ingrese un nombre: ");
        String nombreIngresado = scanner.nextLine();
        // Aqui lo que hace .anyMatch es buscar entre todos los valores del Array si alguno coincide con el valor que ingresamos
        // tanto en el Array de nombre de Hombres como de Mujeres
        boolean nombrePopularMujer = Arrays.stream(nombresMujeres)
                .anyMatch(nombre -> nombre.equalsIgnoreCase(nombreIngresado));
        boolean nombrePopularHombre = Arrays.stream(nombresHombres)
                .anyMatch(nombre -> nombre.equalsIgnoreCase(nombreIngresado));

        // Lo que hace la estructura if es que tiene la variable booleana, ejecuta el anyMatch y manda un mensaje dependiendo de lo que se busca
        // Si el nombre ingresado es igual al de la variable nombrePopularMujer, manda el mensaje de que es popular entre mujeres
        if (nombrePopularMujer) {
            System.out.println("El nombre ingresado es popular entre las niñas.");
            // En el otro caso, si es igual a nombrePopularHombre, manda el mensaje de que es popular entre hombres
        } else if (nombrePopularHombre) {
            System.out.println("El nombre ingresado es popular entre los niños.");
            // Y si on se da ninguno de los casos, manda el mensaje de que ninguno es popular
        } else {
            System.out.println("El nombre ingresado no es popular.");
        }
        // Aqui se concatena tanto los nombres de hombres como los de mujeres,
        // y se imprimen los nombres que empiezan con la letra A
        Stream<String> nombresCombinados = Stream.concat(Arrays.stream(nombresMujeres), Arrays.stream(nombresHombres));
        System.out.println("Nombres que empiezan con la letra A: ");
        nombresCombinados.filter(nombre -> nombre.startsWith("A"))
                .forEach(System.out::println);
    }
    //Este metodo es el qeu hace posible ejecutar el código,
    // mandando llamar los archivos de texto de donde se obtendrán los valores para los Array
    private static String[] leerArchivo(String nombreArchivo) {
        try {
            // PATH es la variable del sistema que utiliza el sistema operativo para buscar
            // los ejecutables necesarios desde la línea de comandos o la ventana Terminal.
            Path path = Path.of(nombreArchivo);
            // Aqui se usa un catch en caso de que no se haya encontrado un archivo
            // y  en lugar de mandar un error en la consola, ejecuta otra accion
            return Files.lines(path)
                    .toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}