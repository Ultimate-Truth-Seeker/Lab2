import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        try (Scanner scc = new Scanner(new File(pathroom))) {
            while (scc.hasNextLine()) {
                String line = scc.nextLine();
                Scanner sc = new Scanner(line);
                    sc.useDelimiter(";");
                    String xx = sc.next();
                    int campus = Integer.parseInt(xx);
                    char building = sc.next().charAt(0);
                    int level = Integer.parseInt(sc.next());
                    int id = Integer.parseInt(sc.next());
                    int capacity = Integer.parseInt(sc.next());
                    rooms.add(new Room(id, building, level, campus, capacity));
                sc.close();
            }
            try (Scanner sdd = new Scanner(new File(pathcourse))) {
                while (sdd.hasNextLine()) {
                    Scanner sd = new Scanner(sdd.nextLine());
                    sd.useDelimiter(";");
                    int id = Integer.parseInt(sd.next());
                    int campus = Integer.parseInt(sd.next());
                    String name = sd.next();
                    int hour = Integer.parseInt(sd.next());
                    int duration = Integer.parseInt(sd.next());
                    String[] days = {sd.next()};
                    int amount = Integer.parseInt(sd.next());
                    courses.add(new Course(id, campus, name, hour, duration, days, amount));
                    
                }
                
            } catch (Exception FileNotFoundException) {
                System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
            }
            
        } catch (Exception FileNotFoundException) {
            System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
        } 
        boolean menu = true;
        while (menu) {
            System.out.println("Elija una opción:\n1. Mostrar información de salón\n2. Mostrar información de curso\n3. Asignar salones a cursos\n4. Salir");
            switch (s.nextInt()) {
                case 1:
                System.out.println("Escriba la sede: ");
                int campus = s.nextInt();
                System.out.println("Escriba el edificio: ");
                s.nextLine();
                char b = s.nextLine().charAt(0);
                System.out.println("Escriba el nivel: ");
                int l = s.nextInt();
                System.out.println("Escriba el id: ");
                int id = s.nextInt();
                for (Room rm : rooms) {
                    if (rm.getCampus() == campus && rm.getBuilding() == b && rm.getLevel() == l && rm.getId() == id) {
                        System.out.println("Sede: "+ rm.getCampus());
                        System.out.println("Edificio: "+ rm.getBuilding());
                        System.out.println("Nivel: "+ rm.getLevel());
                        System.out.println("id_salón: "+ rm.getId());
                        int[][] cln = rm.getCalendar();
                        int clk = 0;
                        for (int[] h: cln) {
                            String f = " ";
                            for (int hh: h) {
                                f += hh + " " ;
                            }
                            System.out.println(clk + f);
                            clk ++;
                        }
                        break;
                    }
                }
                break;
                case 2:
                System.out.println("Escriba el id: ");
                int idd = s.nextInt();
                for (Course c : courses) {
                    if (c.getId() == idd) {
                        System.out.println("Id: " + c.getId());
                        System.out.println("Sede: " + c.getCampus());
                        System.out.println("Nombre: " + c.getName());
                        System.out.println("Horarios: " + c.getHour() + ":00, " +c.getDays()[0]);
                        System.out.println("Cantidad de estudiantes: " + c.getId());
                        break;
                    }
                }
                break;
                case 3:
                ArrayList<String> crs = new ArrayList<>();
  
                for (Course c : courses) {
                    if (c.isAssigned() == false) {
                        for (Room rm : rooms) {
                            c.setAssigned(rm.tryAssign(c));
                            if (c.isAssigned()) {
                                crs.add("" + c.getId() + "; " + rm.getId());
                                
                                break;
                            }
                        }
                    }
                }
                System.out.println("Asignación finalizada");
                System.out.println("Cursos asignados: ");
                for (Course c : courses) {
                    if (c.isAssigned()) {
                        System.out.println("" + c.getId());
                    }
                }
                System.out.println("Cursos no asignados: ");
                for (Course c : courses) {
                    if (c.isAssigned() == false) {
                        System.out.println("" + c.getId());
                    }
                }
                System.out.println("\nDesea exportar los resultados? Ingrese 1 para sí");
                int exp = s.nextInt();
                if (exp == 1){
                    try (FileWriter wr = new FileWriter(new File("export.csv"))) {
                        for (String x : crs) {
                            wr.write(x + "\n");
                        }
                    } catch (FileNotFoundException e) {
                        throw e;
                    } catch (IOException e) {
                        System.out.println("Error al escribir");
                        e.printStackTrace();
                    }

                }
                break;
                case 5:
                menu = false;
                break;
                default:
                break;
            }
            break;
        }
        s.close();

    }
    
}
