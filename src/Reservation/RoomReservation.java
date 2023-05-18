package Reservation;

import hotels.Room;
import hotels.StayDuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class the keeps the track of the stay duration of customer and List of rooms it has being reserved
 */
public class RoomReservation {

    private final List<Room> rooms;
    private final Customer customer;
    private final StayDuration stayDuration;

    public RoomReservation(Customer customer, StayDuration stayDuration) {
        this.stayDuration = stayDuration;
        this.rooms = new ArrayList<>();
        this.customer = customer;
    }

    public void addRoom(Room room,int startDate){
        if(!checkRoomPresent(startDate))
            this.rooms.add(room);
    }

    private boolean checkRoomPresent(int startDate){
        Optional<RoomReservation> reservation = customer.getRoomReservations().stream()
                .filter(roomReservation -> roomReservation.getCustomer().getName().compareTo(customer.getName()) == 0 &&
                        roomReservation.getStayDuration().getStartDate() == startDate)
                .findFirst();
        return reservation.isPresent();
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public Customer getCustomer() {
        return customer;
    }

    public StayDuration getStayDuration() {
        return stayDuration;
    }

}