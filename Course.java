/**Clase para los cursos
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
public class Course {
    private int id;// id único del curso
    private int campus;// sede
    private String name;// nombre
    private int hour;// horario
    private int duration;// duración en horas
    private String[] days;// días que se recibe
    private int amount;// cantidad de estudiantes en el curso
    private boolean Assigned;// si ya está asignado o no a un salón
    /**
     * constructor de la clase
     * @param id
     * @param campus
     * @param name
     * @param hour
     * @param duration
     * @param days
     * @param amount
     */
    public Course(int id, int campus, String name, int hour, int duration, String[] days, int amount) {
        this.id = id;
        this.campus = campus;
        this.name = name;
        this.hour = hour;
        this.duration = duration;
        this.days = days;
        this.amount = amount;
        this.Assigned = false;// por defecto los cursos comienzan sin asignarse
    }
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * @return sede
     */
    public int getCampus() {
        return campus;
    }
    /**
     * @return nombre
     */
    public String getName() {
        return name;
    }
    /**
     * @return horario
     */
    public int getHour() {
        return hour;
    }
    /**
     * @return duración
     */
    public int getDuration() {
        return duration;
    }
    /**
     * @return días del curso
     */
    public String[] getDays() {
        return days;
    }
    /**
     * @return cantidad de estudiantes
     */
    public int getAmount() {
        return amount;
    }
    /**
     * @return si está asignado ya o no
     */
    public boolean isAssigned() {
        return Assigned;
    }
    /**
     * Actualiza la condición de asignación
     * @param Assigned nueva condición. En el driver este es el método tryAssign del salón
     */
    public void setAssigned(boolean Assigned) {
        this.Assigned = Assigned;
    }
    

    
    
}
