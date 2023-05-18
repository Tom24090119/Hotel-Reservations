package Reservation;

import hotels.Floor;
import hotels.Room;
import hotels.StayDuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    public static Optional<Room> leastCostRoom(List<Floor> floors , int size, StayDuration stayDuration){
        return floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getRoomDetails().getMaxOccupants() >= size)
                .filter(room -> room.getReservations().size() == 0 || room.getReservations().stream()
                                .allMatch(reservation ->  stayDuration.getEndDate() <= reservation.getStayDuration().getStartDate()))
                .min(Comparator.comparingInt(room2 -> room2.getRoomDetails().getCostPerNight()));
    }
    public static List<Room> sameFloorRoom(List<Floor> floors , int size, StayDuration stayDuration){

        List<Room> availableRooms = new ArrayList<>();

        for(Floor floor:floors){
            List<Room> rooms = floor.getRooms();
            availableRooms.clear();
            int availableSize=0;
            for (Room room:rooms){
                List<RoomReservation> reservations = room.getReservations();
                for(RoomReservation reservation:reservations){
                    boolean check = stayDuration.getStartDate() >= reservation.getStayDuration().getStartDate() &&
                            stayDuration.getEndDate() <= reservation.getStayDuration().getEndDate();
                    availableSize = availableSize + room.getRoomDetails().getMaxOccupants();
                    availableRooms.add(room);
                    if(check){
                        availableSize =0;
                        availableRooms.clear();
                        break;
                    }
                }
                if(reservations.isEmpty()){
                    availableSize = availableSize + room.getRoomDetails().getMaxOccupants();
                    availableRooms.add(room);
                }
                if(availableSize >= size && availableRooms.size() > 0){
                    return availableRooms.stream()
                            .sorted(Comparator.comparingInt(
                                    room1->room1.getRoomDetails()
                                            .getCostPerNight()))
                            .distinct()
                            .toList();
                }
            }
        }
        return new ArrayList<>();
    }
    public static List<Room> findAnyAvailableRooms(List<Floor> floors , int size, StayDuration stayDuration){
        List<Room> availableRooms = new ArrayList<>();
        int availableSize=0;
        for(Floor floor:floors){
            List<Room> rooms = floor.getRooms();

            for (Room room:rooms){
                List<RoomReservation> reservations = room.getReservations();
                for(RoomReservation reservation:reservations){
                    boolean check = stayDuration.getStartDate() >= reservation.getStayDuration().getStartDate() &&
                            stayDuration.getEndDate() <= reservation.getStayDuration().getEndDate();
                    availableSize = availableSize + room.getRoomDetails().getMaxOccupants();
                    availableRooms.add(room);
                    if(check){
                        availableSize =0;
                        availableRooms.clear();
                        break;
                    }
                }
                if(reservations.isEmpty()){
                    availableSize = availableSize + room.getRoomDetails().getMaxOccupants();
                    availableRooms.add(room);
                }
                if(availableSize >= size && availableRooms.size() > 0){
                    return availableRooms.stream()
                            .sorted(Comparator.comparingInt(
                                    room1->room1.getRoomDetails()
                                            .getCostPerNight()))
                            .distinct().toList();
                }
            }
        }
        return availableRooms;
    }

    public static void reserveRooms(List<Room> rooms,Customer customer, StayDuration stayDuration){
        List<RoomReservation> customerRoomReservations = customer.getRoomReservations();
        Optional<RoomReservation> reservation1 = customerRoomReservations.stream()
                .filter(reservation ->
                        reservation.getStayDuration().getStartDate() == stayDuration.getStartDate() &&
                                reservation.getStayDuration().getEndDate() == stayDuration.getEndDate())
                .findFirst();
        RoomReservation roomReservation;
        if(customerRoomReservations.isEmpty() || reservation1.isEmpty()){
            roomReservation = new RoomReservation(customer,stayDuration);

        }
        else {
            roomReservation = reservation1.get();
        }
        rooms.forEach(room1 -> room1.addCustomer(customer,stayDuration.getStartDate()));
        rooms.forEach(room1 -> roomReservation.addRoom(room1,stayDuration.getStartDate()));
        customer.addReservation(roomReservation,stayDuration.getStartDate());
        rooms.forEach(room1 -> room1.addReservation(roomReservation));
    }

}
