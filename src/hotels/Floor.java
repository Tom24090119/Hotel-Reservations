package hotels;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private final int numberOfRooms;

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
