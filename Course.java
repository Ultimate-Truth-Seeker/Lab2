/**Clase para los cursos
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
public class Course {
    private int id;
    private int campus;
    private String name;
    private int hour;
    private int duration;
    private String[] days;
    private int amount;
    private boolean Assigned;
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
        this.Assigned = false;
    }
    /**
     * @return id
     */
    public int getId() {
        return id;
    }
    public int getCampus() {
        return campus;
    }
    public String getName() {
        return name;
    }
    public int getHour() {
        return hour;
    }
    public int getDuration() {
        return duration;
    }
    public String[] getDays() {
        return days;
    }
    public int getAmount() {
        return amount;
    }
    /**
     * @return assigned
     */
    public boolean isAssigned() {
        return Assigned;
    }
    /**
     * @param Assigned
     */
    public void setAssigned(boolean Assigned) {
        this.Assigned = Assigned;
    }
    

    
    
}
