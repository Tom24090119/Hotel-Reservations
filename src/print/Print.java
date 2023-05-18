package print;

import Reservation.Customer;
import Reservation.RoomReservation;
import hotels.Floor;
import hotels.Room;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Print {
    public static void printCustomers(){
        System.out.println("Customers:");
        List<Customer> customers = Customer.getCustomers();
        List<Customer> list = customers.stream().sorted(Comparator.comparing(Customer::getName)).toList();
        list.forEach(customer -> {
            System.out.printf("%s : ( %d reservations )\n",
                    customer.getName(),
                    customer.getRoomReservations().size());
        });
        System.out.println();
    }

    public static void printCustomersInRoom(List<Floor> floors,int roomNumber) {

        Room first = floors.stream()
                .flatMap(floor -> floor.getRooms().stream())
                .filter(room -> room.getRoomDetails().getRoomNumber() == roomNumber)
                .findFirst().get();

        System.out.printf("Reservations for room %d (%s): \n",roomNumber,first.getRoomDetails().getRoomType());

        List<RoomReservation> reservations = first.getReservations();
        reservations
                .stream().sorted(Comparator.comparingInt(reservation -> reservation.getStayDuration().getStartDate()))
                .forEach(reservation -> {
            int startDate = reservation.getStayDuration().getStartDate();
            int endDate = reservation.getStayDuration().getEndDate();
            System.out.printf("Day %d , %d nights : %s \n", startDate,(endDate-startDate),reservation.getCustomer().getName());
        });
        System.out.println();
    }

    public static void printCustomerReservation(String name) {
        Optional<Customer> searchedCustomer = Customer.searchCustomer(name);
        if(searchedCustomer.isPresent()){
            System.out.println("Reservations for " + name);
            Customer customer = searchedCustomer.get();
            List<RoomReservation> roomReservations = customer.getRoomReservations();
            for(RoomReservation reservation: roomReservations){
                int startDate = reservation.getStayDuration().getStartDate();
                int endDate = reservation.getStayDuration().getEndDate();
                System.out.printf("Day %d , %d nights : rooms ",startDate,(endDate-startDate));
                List<Room> rooms = reservation.getRooms();
                int cost =0;
                for(Room room : rooms){
                    System.out.printf("%d ",room.getRoomDetails().getRoomNumber());
                    cost = cost + room.getRoomDetails().getCostPerNight();
                }
                cost = cost * (endDate-startDate);
                System.out.printf("($%d)\n",cost);
            }
        }
        System.out.println();
    }
    public static void printReservationsOfDay(List<Floor> floors, int day) {
        System.out.printf("Rooms on day %d \n",day);
            for(Floor floor : floors){
                List<Room> rooms = floor.getRooms();
                for(Room room : rooms){
                    List<RoomReservation> reservations = room.getReservations();
                    System.out.printf("%d : ",room.getRoomDetails().getRoomNumber());
                    for(RoomReservation reservation : reservations){
                        if(reservation.getStayDuration().getStartDate()<=day && reservation.getStayDuration().getEndDate() >= day)
                            System.out.printf(" %s ",reservation.getCustomer().getName());
                    }
                    System.out.println();
                }
            }
        System.out.println();
    }
}

