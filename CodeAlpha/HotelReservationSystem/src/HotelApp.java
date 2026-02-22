import java.util.Scanner;

public class HotelApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== HOTEL RESERVATION SYSTEM =====");
        System.out.println("1. Run Console Version");
        System.out.println("2. Run GUI Version");
        System.out.print("Choose option: ");

        int mode = sc.nextInt();

        if (mode == 2) {

            // Launch GUI
            javax.swing.SwingUtilities.invokeLater(() -> {
                new HotelGUI();
            });

        } else {

            // Run Console Version
            HotelService service = new HotelService();

            while (true) {

                System.out.println("\n===== CONSOLE MENU =====");
                System.out.println("1. View Available Rooms");
                System.out.println("2. Book Room");
                System.out.println("3. Cancel Booking");
                System.out.println("4. View All Bookings");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.println(service.getAvailableRoomsText());
                        break;

                    case 2:
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine();

                        System.out.print("Enter Room Number: ");
                        int roomNo = sc.nextInt();

                        System.out.print("Enter Days: ");
                        int days = sc.nextInt();

                        System.out.println(
                                service.bookRoomGUI(name, phone, roomNo, days)
                        );
                        break;

                    case 3:
                        System.out.print("Enter Booking ID: ");
                        int id = sc.nextInt();
                        System.out.println(service.cancelBookingGUI(id));
                        break;

                    case 4:
                        System.out.println(service.getAllBookingsText());
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }
    }
}