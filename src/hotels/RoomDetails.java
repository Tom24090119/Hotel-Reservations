package hotels;

/**
 * This class stores the details of the room such as
 * 1. RoomType
 * 2. Max number of Guests allowed
 * 3. Cost for the number per night
 * 4. Room Number
 */
public class RoomDetails {

    private final String roomType;

    private final int maxOccupants;
    private final int costPerNight;
    private final int roomNumber;

    public RoomDetails(String roomType, int maxOccupants, int costPerNight, int roomNumber) {
        this.roomType = roomType;
        this.maxOccupants = maxOccupants;
        this.costPerNight = costPerNight;
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getMaxOccupants() {
        return maxOccupants;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

}
