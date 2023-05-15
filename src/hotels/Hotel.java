package hotels;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private final String hotelName;

    private final int numberOfFloors;


    private final List<Floor> floors;


    public Hotel(String hotelName, int numberOfFloors) {
        this.hotelName = hotelName;
        this.numberOfFloors = numberOfFloors;
        this.floors = new ArrayList<>();
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
