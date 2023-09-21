/**Clase para los salones
 * @author Ultimate-Truth-Seeker
 * @version 20/09/2023
 */
public class Room {
    private int id;
    private char building;
    private int level;
    private int campus;
    private int capacity;
    private int[][] calendar;
    public Room(int id, char building, int level, int campus, int capacity) {
        this.id = id;
        this.building = building;
        this.level = level;
        this.campus = campus;
        this.capacity = capacity;
        calendar = new int[6][21-7];
    }
    public int getId() {
        return id;
    }
    public char getBuilding() {
        return building;
    }
    public int getLevel() {
        return level;
    }
    public int getCampus() {
        return campus;
    }
    public int getCapacity() {
        return capacity;
    }
    public int[][] getCalendar() {
        return calendar;
    }
    
    public boolean tryAssign(Course course) {
        if (course.getCampus() != this.campus) {
            return false;
        }
        if (course.getAmount() > this.capacity) {
            return false;
        }
        for (String s : course.getDays()){
            for (int x = 0; x < course.getDuration(); x++) {
                if (this.calendar[0][course.getHour()-9+x] != 0) {
                    return false;
                }
            }
        }
        for (String s : course.getDays()){
            for (int x = 0; x < course.getDuration(); x++) {
                if (this.calendar[0][course.getHour()-9+x] == 0) {
                    this.calendar[0][course.getHour()-9+x] =course.getId();
                }
            }
        }
        return true;
    }
    
}
