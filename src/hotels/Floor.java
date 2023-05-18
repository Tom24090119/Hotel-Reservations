package hotels;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores number of rooms present in the current floor
 * Also,
 * Stores the list of Rooms present in the current Floor
 */

public class Floor {

    /**
     * This instance variable stores the number of rooms present in this floor
     */
    private final int numberOfRooms;

    /**
     * This instance variable stores the List of Rooms present int this floor
     */
    private final List<Room> rooms;

    public Floor(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        this.rooms = new ArrayList<>();
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * @param room
     * Adds the room into the List of rooms
     */

    public void addRooms(Room room){
        this.rooms.add(room);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "rooms=" + rooms +
                '}';
    }
}
