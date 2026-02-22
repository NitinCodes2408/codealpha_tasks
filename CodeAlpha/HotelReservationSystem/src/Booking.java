public class Booking {

    private static int counter = 1000;

    private int bookingId;
    private Customer customer;
    private Room room;
    private int days;
    private double totalAmount;

    public Booking(Customer customer, Room room, int days) {
        this.bookingId = counter++;
        this.customer = customer;
        this.room = room;
        this.days = days;
        this.totalAmount = room.getPrice() * days;
    }

    public int getBookingId() { return bookingId; }
    public Room getRoom() { return room; }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId +
                "\nCustomer: " + customer.getName() +
                "\nPhone: " + customer.getPhone() +
                "\nRoom: " + room.getRoomNumber() +
                "\nDays: " + days +
                "\nTotal: ₹" + totalAmount +
                "\n---------------------------";
    }
}