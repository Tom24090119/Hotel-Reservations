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

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void addFloor(Floor floor){
        this.floors.add(floor);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", numberOfFloors=" + numberOfFloors +
                ", " +
                "\n"+
                "floors=" + floors +
                '}';
    }
}
