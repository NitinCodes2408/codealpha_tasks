import javax.swing.*;
import java.awt.*;

public class HotelGUI extends JFrame {

    private HotelService service;
    private JTextArea outputArea;

    public HotelGUI() {

        service = new HotelService();

        setTitle("Hotel Reservation System");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));

        JButton viewBtn = new JButton("Available Rooms");
        JButton bookBtn = new JButton("Book Room");
        JButton cancelBtn = new JButton("Cancel Booking");
        JButton allBtn = new JButton("View Bookings");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(viewBtn);
        buttonPanel.add(bookBtn);
        buttonPanel.add(cancelBtn);
        buttonPanel.add(allBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10,10,10,10));

        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        viewBtn.addActionListener(e ->
                outputArea.setText(service.getAvailableRoomsText())
        );

        bookBtn.addActionListener(e -> bookRoom());

        cancelBtn.addActionListener(e -> cancelBooking());

        allBtn.addActionListener(e ->
                outputArea.setText(service.getAllBookingsText())
        );

        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void bookRoom() {

        String name = JOptionPane.showInputDialog(this, "Enter Customer Name:");
        if (name == null) return;

        String phone = JOptionPane.showInputDialog(this, "Enter Phone:");
        if (phone == null) return;

        String roomStr = JOptionPane.showInputDialog(this, "Enter Room Number:");
        if (roomStr == null) return;

        String daysStr = JOptionPane.showInputDialog(this, "Enter Days:");
        if (daysStr == null) return;

        try {
            int roomNo = Integer.parseInt(roomStr);
            int days = Integer.parseInt(daysStr);

            String result = service.bookRoomGUI(name, phone, roomNo, days);
            outputArea.setText(result);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number input!");
        }
    }

    private void cancelBooking() {

        String idStr = JOptionPane.showInputDialog(this, "Enter Booking ID:");
        if (idStr == null) return;

        try {
            int id = Integer.parseInt(idStr);
            String result = service.cancelBookingGUI(id);
            outputArea.setText(result);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelGUI::new);
    }
}