/**Clase para los salones
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
public class Room {
    private int id;// id salón
    private char building;// edificio
    private int level;// nivel
    private int campus;// sede
    private int capacity;// capacidad máxima
    private int[][] calendar;// calendario de cursos
    /**
     * constructor de la clase
     * @param id
     * @param building
     * @param level
     * @param campus
     * @param capacity
     */
    public Room(int id, char building, int level, int campus, int capacity) {
        this.id = id;
        this.building = building;
        this.level = level;
        this.campus = campus;
        this.capacity = capacity;
        calendar = new int[6][24-7];// seis días de la semana y 24 - 7 horas posibles de cursos. Es 24 y no 21 porque es posible un curso que inicia a las 21 y dura 3
    }
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @return edificio
     */
    public char getBuilding() {
        return building;
    }
    /**
     * @return nivel
     */
    public int getLevel() {
        return level;
    }
    /**
     * @return sede
     */
    public int getCampus() {
        return campus;
    }
    /**
     * @return capacidad máxima
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * @return arreglo del calendario
     */
    public int[][] getCalendar() {
        return calendar;
    }
    
    /**
     * Método para intentar asignar un curso a este salón. Retorna un booleano que es usado inmediatamente para actualizar la condición de asignación del curso
     * @param course el curso a intentar asignar
     * @return verdadero si se asigno a este salón, falso si no
     */
    public boolean tryAssign(Course course) {
        if (course.getCampus() != this.campus) {// verifica que el curso y salón son de la misma sede
            return false;
        }
        if (course.getAmount() > this.capacity) {// verifica que la capacidad alcanza para la cantidad de alumnos en el curso
            return false;
        }
        for (String s : course.getDays()){
            int d;
            switch (s){// asignar un índice para el arreglo del calendario según cada día que se recibe el curso
                case "lunes":
                d = 0;
                break;
                case "martes":
                d = 1;
                break;
                case "miercoles":
                d = 2;
                break;
                case "jueves":
                d = 3;
                break;
                case "viernes":
                d = 4;
                break;
                case "sabado":
                d = 5;
                break;
                default:
                d= 0;
                break;
            }
            for (int x = 0; x < course.getDuration(); x++) {// verificar que no hay otros cursos asignados al salón en los días y horas de este curso
                if (this.calendar[d][course.getHour()-7+x] != 0) {
                    return false;
                }
            }
        }
        for (String s : course.getDays()){
            int d;// asigna nuevamente un índice a los días del curso
            switch (s){
                case "lunes":
                d = 0;
                break;
                case "martes":
                d = 1;
                break;
                case "miercoles":
                d = 2;
                break;
                case "jueves":
                d = 3;
                break;
                case "viernes":
                d = 4;
                break;
                case "sabado":
                d = 5;
                break;
                default:
                d= 0;
                break;
            }
            for (int x = 0; x < course.getDuration(); x++) {// una vez verificadas las condiciones de asignación se añade el id a cada espacio del horario necesario
                if (this.calendar[d][course.getHour()-7+x] == 0) {
                    this.calendar[d][course.getHour()-7+x] =course.getId();
                }
            }
        }
        return true;
    }
    
}
