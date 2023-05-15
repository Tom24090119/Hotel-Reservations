package hotels;

public class Room {

    private final String roomType;

    private final int roomNumber;

    private final int maxOccupants;

    private final int costPerNight;

    private boolean reserved;


    public Room(String roomType, int roomNumber, int maxOccupants, int costPerNight, boolean reserved) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.maxOccupants = maxOccupants;
        this.costPerNight = costPerNight;
        this.reserved = reserved;
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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
