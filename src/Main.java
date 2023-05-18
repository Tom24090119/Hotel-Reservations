import commands.CommandType;
import Reservation.Customer;
import Reservation.ReservationService;
import Reservation.RoomReservation;
import commands.PrintCommands;
import hotels.*;
import print.Print;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;

import java.util.Optional;
import java.util.Scanner;


public class Main {
    private Hotel hotel;
    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.readHotel("a1plain.txt");
        main.readReserve("a1reserve.txt");
    }

    public void readHotel(String filename) throws FileNotFoundException {
        File source = new File(filename);
        Scanner sc = new Scanner(source);

        while (sc.hasNextLine()) {
            String hotelName = sc.nextLine();
            int floor = Integer.parseInt(sc.nextLine());
            hotel = new Hotel(hotelName, floor);
            for (int i = 1; i <= hotel.getNumberOfFloors(); i++) {
                int numberOfRooms = Integer.parseInt(sc.nextLine());
                Floor floors = new Floor(numberOfRooms);
                hotel.addFloor(floors);
                for (int j = 1; j <= floors.getNumberOfRooms(); j++) {
                    String[] split = sc.nextLine().split(",");
                    RoomDetails roomDetails = new RoomDetails(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), (int) (i * Math.pow(10.0, 2.0) + j));
                    Room room = new Room(roomDetails);
                    floors.addRooms(room);
                }

            }
        }
        sc.close();

    }

    public void readReserve(String filename) throws FileNotFoundException {

        File source = new File(filename);
        Scanner sc = new Scanner(source);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.startsWith(CommandType.RESERVE.toString())) {
                String[] split = s.split(",");

                String name = split[1];
                int numberOfGuests = Integer.parseInt(split[2]);
                int startDate = Integer.parseInt(split[3]);
                int endDate = startDate + Integer.parseInt(split[4]);

                StayDuration stayDuration = new StayDuration(startDate, endDate);

                Optional<Customer> searchCustomer = Customer.searchCustomer(name);
                Customer customer;
                if (searchCustomer.isEmpty()) {
                    customer = new Customer(name);
                    customer.addStayDuration(stayDuration);
                    Customer.addCustomers(customer);
                }
                else {
                    customer = searchCustomer.get();
                }
                List<Floor> floors = hotel.getFloors();
                Optional<Room> room = ReservationService.leastCostRoom(floors, numberOfGuests, stayDuration);

                if (room.isPresent()) {
                    Room availableRoom = room.get();
                    RoomReservation roomReservation = new RoomReservation(customer, stayDuration);
                    availableRoom.addCustomer(customer,stayDuration.getStartDate());
                    roomReservation.addRoom(availableRoom,stayDuration.getStartDate());
                    customer.addReservation(roomReservation, stayDuration.getStartDate());
                    availableRoom.addReservation(roomReservation);
                }
                else {
                    List<Room> sameFloorRooms = ReservationService.sameFloorRoom(hotel.getFloors(), numberOfGuests, stayDuration);
                    List<Room> anyAvailableRooms = ReservationService.findAnyAvailableRooms(hotel.getFloors(), numberOfGuests, stayDuration);
                    if(!sameFloorRooms.isEmpty()) {
                        ReservationService.reserveRooms(sameFloorRooms,customer,stayDuration);
                    }
                    else if(!anyAvailableRooms.isEmpty()){
                        ReservationService.reserveRooms(anyAvailableRooms,customer,stayDuration);
                    }
                    else{
                        System.out.printf("Cannot make a reservation for %s : No rooms available\n\n",customer.getName());
                    }
                }
            }
            else if (s.startsWith(CommandType.CANCEL.toString())) {
                try {
                    String[] split = s.split(",");
                    String name = split[1];
                    int cancellationDate = Integer.parseInt(split[2]);

                    Optional<Customer> searchedCustomer = Customer.searchCustomer(name);
                    if(searchedCustomer.isEmpty())
                        throw new RuntimeException();

                    Customer customer = searchedCustomer.get();

                    Optional<RoomReservation> roomReservation = customer.getRoomReservations().stream()
                            .filter(reservation -> reservation.getStayDuration().getStartDate() <= cancellationDate &&
                                    cancellationDate <= reservation.getStayDuration().getEndDate())
                            .findFirst();

                    if(roomReservation.isEmpty()){
                        throw  new RuntimeException();
                    }
                    StayDuration stayDuration = roomReservation.get().getStayDuration();
                    if(stayDuration.getStartDate() == cancellationDate){
                        customer.deleteReservation(roomReservation.get());
                        roomReservation.get().getRooms().forEach(room -> room.deleteReservation(roomReservation.get()));
                    }
                    else{
                        stayDuration.setEndDate(cancellationDate);
                    }


                } catch (Exception e) {
                    System.out.println("Error while parsing the string : " + s + "Or customer doesn't exist");
                }

            }
            else if (s.startsWith(CommandType.PRINT.toString())) {

                String[] split = s.split(",");

                if(split[1].compareTo(PrintCommands.DAY.toString()) == 0){
                    int day = Integer.parseInt(split[2]);
                    Print.printReservationsOfDay(hotel.getFloors(),day);
                }
                else if (split[1].compareTo(PrintCommands.ROOM.toString()) == 0) {
                    int roomNumber = Integer.parseInt(split[2]);
                    Print.printCustomersInRoom(hotel.getFloors(),roomNumber);
                }
                else if (split[1].compareTo(PrintCommands.CUSTOMER.toString()) == 0) {
                    String name = split[2];
                    Print.printCustomerReservation(name);
                }
                else if (split[1].compareTo(PrintCommands.CUSTOMERS.toString()) == 0) {
                    Print.printCustomers();
                }
            }
        }
    }
}