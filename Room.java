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
            int d;
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
            for (int x = 0; x < course.getDuration(); x++) {
                if (this.calendar[d][course.getHour()-7+x] != 0) {
                    return false;
                }
            }
        }
        for (String s : course.getDays()){
            int d;
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
            for (int x = 0; x < course.getDuration(); x++) {
                if (this.calendar[d][course.getHour()-7+x] == 0) {
                    this.calendar[d][course.getHour()-7+x] =course.getId();
                }
            }
        }
        return true;
    }
    
}
