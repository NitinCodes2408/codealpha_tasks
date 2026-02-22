import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StockTradingGUI extends JFrame {

    private JTextArea outputArea;
    private Portfolio portfolio;
    private HashMap<String, Stock> market;

    public StockTradingGUI() {

        portfolio = new Portfolio();

        market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("RELIANCE", new Stock("RELIANCE", 2500));

        setTitle("Stock Trading Platform");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Buttons Panel =====
        JPanel topPanel = new JPanel(new GridLayout(1,5,10,10));

        JButton marketBtn = new JButton("Market");
        JButton buyBtn = new JButton("Buy");
        JButton sellBtn = new JButton("Sell");
        JButton portfolioBtn = new JButton("Portfolio");
        JButton balanceBtn = new JButton("Balance");

        topPanel.add(marketBtn);
        topPanel.add(buyBtn);
        topPanel.add(sellBtn);
        topPanel.add(portfolioBtn);
        topPanel.add(balanceBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== Output Area =====
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ===== Button Actions =====

        marketBtn.addActionListener(e -> showMarket());
        buyBtn.addActionListener(e -> buyStock());
        sellBtn.addActionListener(e -> sellStock());
        portfolioBtn.addActionListener(e -> showPortfolio());
        balanceBtn.addActionListener(e -> showBalance());

        setVisible(true);
    }

    private void showMarket() {
        StringBuilder sb = new StringBuilder("===== MARKET STOCKS =====\n\n");

        for (Stock s : market.values()) {
            sb.append(s.getName())
              .append(" - ₹")
              .append(s.getPrice())
              .append("\n");
        }

        outputArea.setText(sb.toString());
    }

    private void buyStock() {

        String name = JOptionPane.showInputDialog(this, "Enter Stock Name (TCS/INFY/RELIANCE):");
        if (name == null) return;

        name = name.toUpperCase();

        if (!market.containsKey(name)) {
            JOptionPane.showMessageDialog(this, "Stock Not Found!");
            return;
        }

        String qtyStr = JOptionPane.showInputDialog(this, "Enter Quantity:");
        if (qtyStr == null) return;

        try {
            int qty = Integer.parseInt(qtyStr);
            portfolio.buyStock(market.get(name), qty);
            outputArea.setText("Stock Purchased Successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity!");
        }
    }

    private void sellStock() {

        String name = JOptionPane.showInputDialog(this, "Enter Stock Name:");
        if (name == null) return;

        name = name.toUpperCase();

        if (!market.containsKey(name)) {
            JOptionPane.showMessageDialog(this, "Stock Not Found!");
            return;
        }

        String qtyStr = JOptionPane.showInputDialog(this, "Enter Quantity:");
        if (qtyStr == null) return;

        try {
            int qty = Integer.parseInt(qtyStr);
            portfolio.sellStock(market.get(name), qty);
            outputArea.setText("Stock Sold Successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity!");
        }
    }

    private void showPortfolio() {
        outputArea.setText(portfolio.getPortfolioDetails());
    }

    private void showBalance() {
        outputArea.setText("Current Balance: ₹" + portfolio.getBalance());
    }

    public static void main(String[] args) {
        new StockTradingGUI();
    }
}