package hotels;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private final int floorNumber;

    private final List<Room> rooms;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.rooms = new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
