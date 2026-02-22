import java.util.ArrayList;

public class HotelService {

    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    public HotelService() {

        rooms.add(new Room(101, "Standard", 1000));
        rooms.add(new Room(102, "Standard", 1000));
        rooms.add(new Room(201, "Deluxe", 2000));
        rooms.add(new Room(301, "Suite", 4000));
    }

    public String getAvailableRoomsText() {

        StringBuilder sb = new StringBuilder();
        sb.append("========== AVAILABLE ROOMS ==========\n\n");

        boolean found = false;

        for (Room r : rooms) {
            if (r.isAvailable()) {
                sb.append("Room ").append(r.getRoomNumber())
                        .append(" | ").append(r.getCategory())
                        .append(" | ₹").append(r.getPrice())
                        .append("\n");
                found = true;
            }
        }

        if (!found) {
            sb.append("No rooms available.\n");
        }

        return sb.toString();
    }

    public String bookRoomGUI(String name, String phone, int roomNo, int days) {

        for (Room r : rooms) {

            if (r.getRoomNumber() == roomNo && r.isAvailable()) {

                Customer customer = new Customer(name, phone);
                Booking booking = new Booking(customer, r, days);

                r.setAvailable(false);
                bookings.add(booking);

                return "========== BOOKING SUCCESSFUL ==========\n\n"
                        + booking.toString();
            }
        }

        return "Room not available or invalid room number!";
    }

    public String getAllBookingsText() {

        if (bookings.isEmpty()) {
            return "No bookings yet.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("========== ALL BOOKINGS ==========\n\n");

        for (Booking b : bookings) {
            sb.append(b.toString()).append("\n");
        }

        return sb.toString();
    }

    public String cancelBookingGUI(int bookingId) {

        for (Booking b : bookings) {

            if (b.getBookingId() == bookingId) {

                b.getRoom().setAvailable(true);
                bookings.remove(b);

                return "Booking Cancelled Successfully!";
            }
        }

        return "Booking ID not found!";
    }
}