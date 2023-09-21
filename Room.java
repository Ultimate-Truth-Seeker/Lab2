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
        calendar = new int[7][21-7];
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
        return false;
    }
    
}
