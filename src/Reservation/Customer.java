package Reservation;


import hotels.StayDuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {

    private final static List<Customer> customers = new ArrayList<>();

    private final String name;

    private final List<RoomReservation> roomReservations;

//    private final List<StayDuration> stayDurations;

    public Customer(String name) {
        this.name = name;
        this.roomReservations = new ArrayList<>();
//        this.stayDurations = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addReservation(RoomReservation reservation, int startDate){
        if(!checkCustomerIsPresent(startDate))
            roomReservations.add(reservation);
    }

    private boolean checkCustomerIsPresent(int startDate){
        Optional<RoomReservation> reservation = roomReservations.stream()
                .filter(roomReservation -> roomReservation.getCustomer().getName().compareTo(this.getName()) == 0 &&
                        roomReservation.getStayDuration().getStartDate() == startDate)
                .findFirst();

        return reservation.isPresent();
    }

    public void deleteReservation(RoomReservation reservation){
        roomReservations.remove(reservation);
    }

    public void addStayDuration(StayDuration stayDuration){
//        stayDurations.add(stayDuration);
    }

    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

//    public List<StayDuration> getStayDurations() {
//        return stayDurations;
//    }

    public static void addCustomers(Customer customer){
        customers.add(customer);
    }
    public static Optional<Customer> searchCustomer(String name){
        return customers.stream()
                .filter(customer -> customer.getName().compareTo(name) == 0)
                .findFirst();
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}
