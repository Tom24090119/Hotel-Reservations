package hotels;

import Reservation.Customer;
import Reservation.RoomReservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room {

    private final RoomDetails roomDetails;

    //TODO : Later see if you can remove this customer list
    private final List<Customer> customers;
    private final List<RoomReservation> reservations;


    public Room(RoomDetails details) {
        this.roomDetails = details;
        this.customers = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }
    public RoomDetails getRoomDetails() {
        return roomDetails;
    }
    public List<RoomReservation> getReservations() {
        return reservations;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer, int startDate){
        if(!checkIfCustomerExists(customer.getName(), startDate))
            customers.add(customer);
    }
    private boolean checkIfCustomerExists(String name, int startDate){
        Optional<Customer> first = customers.stream()
                .filter(customer -> customer.getName().compareTo(name) == 0 &&
                        customer.getRoomReservations().stream()
                                .allMatch(reservation -> reservation.getStayDuration().getStartDate() == startDate))
                    .findFirst();
        return first.isPresent();
    }
    public void addReservation(RoomReservation reservation){
        reservations.add(reservation);
    }

    public void deleteCustomer(Customer customer){
        customers.remove(customer);
    }

    public void deleteReservation(RoomReservation reservation){
        reservations.remove(reservation);
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomDetails=" + roomDetails +
                ", reservations=" + reservations +
                '}';
    }
}
