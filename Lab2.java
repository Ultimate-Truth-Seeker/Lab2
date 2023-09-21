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
        System.out.println("Bienvenido al programa! Asegurese que los csv de datos usen punto y coma en vez de solo coma ");
        System.out.println("ingrese la ubicación del archivo de salones: ");
        String pathroom = s.nextLine();
        System.out.println("Ingrese la ubicación del archivo de cursos: ");
        String pathcourse = s.nextLine();
        try (Scanner sc = new Scanner(new File(pathroom))) {
            sc.useDelimiter(";");
            while (sc.hasNextLine()) {
                    sc.useDelimiter(";");
                    String xx = sc.next();
                    int campus = Integer.parseInt(xx);
                    char building = sc.next().charAt(0);
                    int level = Integer.parseInt(sc.next());
                    int id = Integer.parseInt(sc.next());
                    int capacity = Integer.parseInt(sc.next());
                    rooms.add(new Room(id, building, level, campus, capacity));
                
            }
            try (Scanner sd = new Scanner(new File(pathcourse))) {
                sd.useDelimiter(";");
                while (sd.hasNextLine()) {
                    int id = Integer.parseInt(sd.next());
                    int campus = Integer.parseInt(sd.next());
                    String name = sd.next();
                    int hour = Integer.parseInt(sd.next());
                    int duration = Integer.parseInt(sd.next());
                    String[] days = {sd.next()};
                    int amount = Integer.parseInt(sd.next());
                    courses.add(new Course(id, campus, name, hour, duration, days, amount));

                }
            }
            
        }
        s.close();

    }
    
}
