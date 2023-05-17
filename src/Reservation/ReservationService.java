package Reservation;

import hotels.Floor;
import hotels.Room;
import hotels.StayDuration;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    public static Optional<Room> leastCostRoom(List<Floor> floors , int size, StayDuration stayDuration){
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getRoomDetails().getMaxOccupants() >= size)
                .filter(room -> room.getReservations().size() == 0 || room.getReservations().stream()
                                .allMatch(reservation ->  stayDuration.getEndDate() < reservation.getStayDuration().getStartDate()))
                .min(Comparator.comparingInt(room2 -> room2.getRoomDetails().getCostPerNight()));
    }
//    public static List<Room> sameFloorRoom(List<Floor> floors , int size, StayDuration stayDuration){
//
//    }


}
