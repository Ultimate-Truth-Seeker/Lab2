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
 * 
 * @author Ultimate-Truth-Seeker
 * @since 20/09/2023
 * @version 22/09/2023
 */

public class Lab2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Creación de lista de los días posibles
        String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado"}; 
        ArrayList<String> ndias = new ArrayList<>();
        for (String ss : dias) {
            ndias.add(ss);
        }
        boolean menu = true; // condición de menú, se desactiva si hay algun error en la lectura
        List<Course> courses = new ArrayList<>(); // lista de cursos
        List<Room> rooms = new ArrayList<>(); // lista de salones
        Scanner s = new Scanner(System.in); // scanner general de entrada
        System.out.println("Bienvenido al programa! Asegurese que en la primera línea del csv no están los nombres de los campos ");
        System.out.println("ingrese la ubicación del archivo de salones: ");
        String pathroom = s.nextLine(); // ingresa archivo de salones 
        System.out.println("Ingrese la ubicación del archivo de cursos: ");
        String pathcourse = s.nextLine(); // ingresa archivo de cursos
        try (Scanner scc = new Scanner(new File(pathroom))) {// recursos try catch para intentar abrir el archivo y que el tipo de datos sea correcto
            while (scc.hasNextLine()) {
                String line = scc.nextLine();// se lee el csv linea por linea
                Scanner sc = new Scanner(line);
                    sc.useDelimiter(",");// Se usa un scanner para leer cada elemento separado por comas en la línea 
                    String xx = sc.next();
                    int campus = Integer.parseInt(xx);// los valores enteros se convierten de string a int
                    String btxt = sc.next();
                    char building = "A".charAt(0);
                    if (btxt.length() != 1) {// verifica que el campo de edificio tenga un solo caracter
                        System.out.println("Error, hay un campo de edificio que no es de un solo caracter: " + btxt);
                        menu = false;
                    } else {
                        building = btxt.charAt(0);
                    }
                    String abc = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
                    for (int x = 0; x < abc.length(); x++) {// verifica que el edificio sea una letra mayúscula
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
                    if (level < 1 || level > 10) {// verifica el rango del nivel
                        System.out.println("Error, hay un nivel que no está entre 1 y 10: " + level);
                        menu = false;
                    }
                    int id = Integer.parseInt(sc.next());
                    if (id < 1 || id > 99) {// verifica el rango del id
                        System.out.println("Error, un id de salón no está entre 1 y 99: " + id);
                        menu = false;
                    }
                    int capacity = Integer.parseInt(sc.next());
                    if (capacity < 10 || capacity > 150) {// verifica el rango de la capacidad
                        System.out.println("Error, una capacidad no está entre 10 y 150: " + capacity);
                        menu = false;
                    }
                    if (sc.hasNext()) {
                        System.out.println("Error, hay más elementos en la línea de los que debería haber");
                        menu = false;
                    }
                    if (menu) {// si no hay errores se crea un nuevo objeto en la lista
                        rooms.add(new Room(id, building, level, campus, capacity));
                    }
                    else {// sino se muestra la línea con los errores
                        System.out.println("Linea errónea: \n" + line);
                    }
                sc.close();
            }
            try (Scanner sdd = new Scanner(new File(pathcourse))) {// try catch con el otro archivo
                while (sdd.hasNextLine()) {
                    String line = sdd.nextLine();// mismo procedimiento con las líneas del csv
                    Scanner sd = new Scanner(line);
                    sd.useDelimiter(",");
                    int id = Integer.parseInt(sd.next());
                    for (Course rm : courses){// verifica que no hay ningun id que se repita en la lísta de cursos
                        if (rm.getId() == id) {
                            System.out.println("Error, hay un id de curso repetido: " + id);
                            menu =false;
                            break;
                        }
                    }
                    if (id == 0){// evita que haya un id igual a 0 debido a que en el objeto salón el cero significa que el salón está libre
                        System.out.println("Error, hay un id de curso igual a 0: " + id);
                        menu =false;
                    }
                    int campus = Integer.parseInt(sd.next());
                    String name = sd.next();
                    int hour = Integer.parseInt(sd.next());
                    if (hour < 7 || hour > 21) {// verifica el rango del horario
                        System.out.println("Error, una hora no está entre 7 y 21: " + hour);
                        menu = false;
                    }
                    int duration = Integer.parseInt(sd.next());
                    if (duration < 1 || duration > 3) {// verifica el rango de la duración
                        System.out.println("Error, una duración de curso no está entre 1 y 3: " + duration);
                        menu = false;
                    }
                    // verifica dia por dia que esten bien escritos. La mayúsculas y minúsculas no importan
                    
                    ArrayList<String> dayss = new ArrayList<>();
                    while (sd.hasNext()) {
                        dayss.add(sd.next());
                    }
                    String[] days = new String[dayss.size() - 1];
                    for (int x = 0; x < dayss.size() - 1; x++) {
                        if (ndias.indexOf(dayss.get(x).toLowerCase()) == -1) {
                            System.out.println("Error, Hay un día mal escrito: \"" + dayss.get(x) + "\" Escriba los días sin tilde, sin espacios y sin errores ortográficos");
                            menu = false;
                        } else {
                            days[x] = dayss.get(x).toLowerCase().toString();
                        }
                    }
                    int amount = Integer.parseInt(dayss.get(dayss.size() - 1));
                    if (amount < 1 || amount > 60) {// verifica el rango de la cantidad de estudiantes
                        System.out.println("Error, una cantidad de estudiantes no está entre 1 y 60: " + amount);
                        menu = false;
                    }
                    
                    if (menu) {// añade un nuevo curso si no hay errores, sino muestra la línea erronea
                        courses.add(new Course(id, campus, name, hour, duration, days, amount));
                    } else {
                        System.out.println("Línea errónea: \n" + line);
                    }
                    sd.close();
                }
                
            } catch (Exception FileNotFoundException) {
                System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
                System.out.println(pathcourse);
                menu = false;
            }
            
        } catch (Exception FileNotFoundException) {
            System.out.println("Error al cargar al archivo, revise que ingresó bien la ubicación y que los tipos de datos en cada campo son los correctos");
            System.out.println(pathroom);
            menu = false;
        }
        ArrayList<String> crs = new ArrayList<>(); // Lista para la exportación del archivo de asignaciones
        while (menu) {// menu
            System.out.println("Elija una opción:\n1. Mostrar información de salón\n2. Mostrar información de curso\n3. Asignar salones a cursos\n4. Salir");
            switch (s.nextInt()) {
                case 1:// ver datos de salón en base a sede, edificio, nivel e id
                System.out.println("Escriba la sede: ");
                int campus = s.nextInt();
                System.out.println("Escriba el edificio: ");
                s.nextLine();
                char b = s.nextLine().charAt(0);
                System.out.println("Escriba el nivel: ");
                int l = s.nextInt();
                System.out.println("Escriba el id: ");
                int id = s.nextInt();
                for (Room rm : rooms) {// encontrar el salón que coincida con los datos
                    if (rm.getCampus() == campus && rm.getBuilding() == b && rm.getLevel() == l && rm.getId() == id) {
                        System.out.println("Sede: "+ rm.getCampus());// imprime los datos
                        System.out.println("Edificio: "+ rm.getBuilding());
                        System.out.println("Nivel: "+ rm.getLevel());
                        System.out.println("id_salón: "+ rm.getId());
                        int[][] cln = rm.getCalendar();
                        String f = "día/horario|";
                        for (int x = 7; x < 25; x++) {
                            f += x + ":00|";
                        }
                        System.out.println(f);// imprime línea de horas del calendario
                        int clk = 0;
                        for (int[] h: cln) {
                            f = "|";
                            for (int hh: h) {// consulta el arreglo de calendario del salón, y añade los cursos asignados a la hora si hay
                                if (hh == 0) {
                                    f += "libre|";
                                } else {
                                    f += "id: " + hh + "|" ;
                                }
                                
                            }
                            System.out.println(ndias.get(clk)+ f);// imprime el horario de cada día
                            clk ++;
                        }
                        break;
                    }
                }
                break;
                case 2:
                System.out.println("Escriba el id: ");// ver datos de curso en base a id único
                int idd = s.nextInt();
                for (Course c : courses) {
                    if (c.getId() == idd) {// encuentra al curso con id ingresado
                        System.out.println("Id: " + c.getId());// muestra los datos del curso
                        System.out.println("Sede: " + c.getCampus());
                        System.out.println("Nombre: " + c.getName());
                        String ds = "";
                        for (String ss : c.getDays()) {// añade en una sola cadena los días del curso
                            ds += ", " + ss;
                        }
                        System.out.println("Horario: " + c.getHour() + ":00 a " + (c.getHour() + c.getDuration()) + ":00" + ds);
                        System.out.println("Cantidad de estudiantes: " + c.getAmount());
                        break;
                    }
                }
                break;
                case 3:
  
                for (Course c : courses) {
                    if (c.isAssigned() == false) {// con cada curso verifica si todavía no ha sido asignado
                        for (Room rm : rooms) {
                            c.setAssigned(rm.tryAssign(c));// prueba asignarse a cada salón
                            if (c.isAssigned()) {// si se logró asignar añadir datos a la lista de exportación y dejar de probar salones
                                crs.add("" + c.getId() + "," + rm.getCampus() + "," + rm.getBuilding() + "," + rm.getLevel() + "," + rm.getId());
                                
                                break;
                            }
                        }
                    }
                }
                System.out.println("Asignación finalizada");
                System.out.println("Cursos asignados: ");
                for (Course c : courses) {
                    if (c.isAssigned()) {// muestra los cursos asignados
                        System.out.println("id: " + c.getId());
                    }
                }
                System.out.println("Cursos no asignados: ");
                for (Course c : courses) {
                    if (c.isAssigned() == false) {// muestra los cursos no asignados
                        System.out.println("id: " + c.getId());
                    }
                }
                System.out.println("\nDesea exportar los resultados? Ingrese 1 para sí");
                int exp = s.nextInt();
                if (exp == 1){// exporta datos si es solicitado por usuario
                    try (FileWriter wr = new FileWriter(new File("export.csv"))) {// try catch por si ocurre un error durante la escritura
                        for (String x : crs) {
                            wr.write(x + "\n");// escribe cada línea de la lista de exportación
                        }
                        System.out.println("Exportado con éxito. Nombre del archivo: export.csv \nFormato: id_curso;sede;edificio;nivel;id_salón");
                    } catch (FileNotFoundException e) {
                        throw e;
                    } catch (IOException e) {
                        System.out.println("Error al escribir");
                        e.printStackTrace();
                    }

                }
                break;
                case 4:
                menu = false;//salir
                break;
                default:
                break;
            }
        }
        s.close();

    }
    
}
