import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Programa de aasignación de cursos
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
//"Documentos de la Universidad/Programación orientada a objetos/Lab2/movies2023.csv"
public class Lab2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Course> courses = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenido al programa: ");
        System.out.println("ingrese la ubicación del archivo de salones: ");
        String pathroom = s.nextLine();
        System.out.println("Ingrese la ubicación del archivo de cursos: ");
        String pathcourse = s.nextLine();
        try (Scanner sc = new Scanner(new File(pathroom))) {
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                
                    String xx = s.next();
                    int campus = Integer.parseInt(xx);
                    char building = s.next().charAt(0);
                    int level = Integer.parseInt(s.next());
                    int id = Integer.parseInt(s.next());
                    int capacity = Integer.parseInt(s.next());
                    rooms.add(new Room(id, building, level, campus, capacity));
                
            }
            try (Scanner sd = new Scanner(new File(pathcourse))) {
                
            }
            
        }

    }
    
}
