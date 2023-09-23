import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Programa de aasignación de cursos
 * 
 * NOTA: se asume que en la primera línea de los csv se comienza de una vez con los datos y no con los nombres de los campos
 * NOTA: se asume que los csv se separan por punto y coma y no por coma
 * 
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
//"Documentos de la Universidad/Programación orientada a objetos/Lab2/movies2023.csv"
public class Lab2 {
    public static void main(String[] args) throws FileNotFoundException {
        String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado"};
        ArrayList<String> ndias = new ArrayList<>();
        for (String ss : dias) {
            ndias.add(ss);
        }
        boolean menu = true;
        List<Course> courses = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Bienvenido al programa! Asegurese que los csv de datos usen punto y coma en vez de solo coma, y que en la primera línea no están los nombres de los campos ");
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
                    String btxt = sc.next();
                    char building = "A".charAt(0);
                    if (btxt.length() != 1) {
                        System.out.println("Error, hay un campo de edificio que no es de un solo caracter: " + btxt);
                        menu = false;
                    } else {
                        building = btxt.charAt(0);
                    }
                    String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                    for (int x = 0; x < abc.length(); x++) {
                        if (building == abc.charAt(x)) {
                            break;
                        } else {
                            if (x == abc.length() - 1) {
                                System.out.println("Error, un edificio no es una letra mayúscula: " + building);
                                menu = false;
                            }
                        }
                    }
                    int level = Integer.parseInt(sc.next());
                    if (level < 1 || level > 10) {
                        System.out.println("Error, hay un nivel que no está entre 1 y 10: " + level);
                        menu = false;
                    }
                    int id = Integer.parseInt(sc.next());
                    if (id < 1 || id > 99) {
                        System.out.println("Error, un id de salón no está entre 1 y 99: " + id);
                        menu = false;
                    }
                    int capacity = Integer.parseInt(sc.next());
                    if (capacity < 10 || capacity > 150) {
                        System.out.println("Error, una capacidad no está entre 10 y 150: " + capacity);
                        menu = false;
                    }
                    if (menu) {
                        rooms.add(new Room(id, building, level, campus, capacity));
                    }
                    else {
                        System.out.println("Linea errónea: \n" + line);
                    }
                sc.close();
            }
            try (Scanner sdd = new Scanner(new File(pathcourse))) {
                while (sdd.hasNextLine()) {
                    String line = sdd.nextLine();
                    Scanner sd = new Scanner(line);
                    sd.useDelimiter(";");
                    int id = Integer.parseInt(sd.next());
                    for (Course rm : courses){
                        if (rm.getId() == id) {
                            System.out.println("Error, hay un id de curso repetido: " + id);
                            menu =false;
                            break;
                        }
                    }
                    if (id == 0){
                        System.out.println("Error, hay un id de curso igual a 0: " + id);
                        menu =false;
                    }
                    int campus = Integer.parseInt(sd.next());
                    String name = sd.next();
                    int hour = Integer.parseInt(sd.next());
                    if (hour < 7 || hour > 21) {
                        System.out.println("Error, una hora no está entre 7 y 21: " + hour);
                        menu = false;
                    }
                    int duration = Integer.parseInt(sd.next());
                    if (duration < 1 || duration > 3) {
                        System.out.println("Error, una duración de curso no está entre 1 y 3: " + duration);
                        menu = false;
                    }
                    Scanner ddd = new Scanner(sd.next());
                    ddd.useDelimiter(",");
                    ArrayList<String> dayss = new ArrayList<>();
                    while (ddd.hasNext()) {
                        dayss.add(ddd.next());
                    }
                    String[] days = new String[dayss.size()];
                    for (int x = 0; x < dayss.size(); x++) {
                        if (ndias.indexOf(dayss.get(x).toLowerCase()) == -1) {
                            System.out.println("Error, Hay un día mal escrito: \"" + dayss.get(x) + "\" Escriba los días sin tilde, sin espacios y sin errores ortográficos");
                            menu = false;
                        } else {
                            days[x] = dayss.get(x).toLowerCase().toString();
                        }
                    }
                    int amount = Integer.parseInt(sd.next());
                    if (amount < 1 || amount > 60) {
                        System.out.println("Error, una cantidad de estudiantes no está entre 1 y 60: " + amount);
                        menu = false;
                    }
                    if (menu) {
                        courses.add(new Course(id, campus, name, hour, duration, days, amount));
                    } else {
                        System.out.println("Línea errónea: \n" + line);
                    }
                    sd.close();
                }
                
            } catch (Exception FileNotFoundException) {
                System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
                menu = false;
            }
            
        } catch (Exception FileNotFoundException) {
            System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
            menu = false;
        } 
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
                        String ds = "";
                        for (String ss : c.getDays()) {
                            ds += ss + " ";
                        }
                        System.out.println("Horarios: " + c.getHour() + ":00, " + ds);
                        System.out.println("Cantidad de estudiantes: " + c.getAmount());
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
                                crs.add("" + c.getId() + ";" + rm.getCampus() + ";" + rm.getBuilding() + ";" + rm.getLevel() + ";" + rm.getId());
                                
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
        }
        s.close();

    }
    
}
