package hotels;

import Reservation.Customer;
import Reservation.RoomReservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class contains :
 * 1. RoomDetails: Details of the current Room
 * 2. List Of Customers : This List stores the customers that are reserved in the current room
 * 3. List of RoomReservation : This List stores all the reservations that are done to the current room
 */

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

    /**
     *
     * @param customer
     * Adds the customer to the list
     * @param startDate
     */
    public void addCustomer(Customer customer, int startDate){
        if(!checkIfCustomerExists(customer.getName(), startDate))
            customers.add(customer);
    }

    /**
     *
     * @param name
     * Name of the customer
     * @param startDate
     * start date of the customer
     * @return true if the customer is present
     *
     * Loops through List of Customers , finds the customer with the parameter name
     * gets the reservations of that customer and finds the reservation which has a start Date equal to start Date
     * provided as a parameter
     */
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

    public void deleteReservation(RoomReservation reservation){
        reservations.remove(reservation);
    }



}
