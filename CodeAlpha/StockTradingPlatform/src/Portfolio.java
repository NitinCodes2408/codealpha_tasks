import java.util.HashMap;

public class Portfolio {

    private HashMap<String, Integer> holdings = new HashMap<>();
    private double balance = 10000; // Starting money

    public double getBalance() {
        return balance;
    }

    public void buyStock(Stock stock, int quantity) {

        double totalCost = stock.getPrice() * quantity;

        if (totalCost > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= totalCost;
        holdings.put(stock.getName(),
                holdings.getOrDefault(stock.getName(), 0) + quantity);

        System.out.println("Stock Purchased Successfully!");
    }

    public void sellStock(Stock stock, int quantity) {

        if (!holdings.containsKey(stock.getName()) ||
                holdings.get(stock.getName()) < quantity) {

            System.out.println("Not enough stocks to sell!");
            return;
        }

        holdings.put(stock.getName(),
                holdings.get(stock.getName()) - quantity);

        balance += stock.getPrice() * quantity;

        System.out.println("Stock Sold Successfully!");
    }

    public void viewPortfolio() {

        System.out.println("\n===== YOUR PORTFOLIO =====");
        System.out.println("Balance: ₹" + balance);

        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }

        for (String stockName : holdings.keySet()) {
            System.out.println(stockName + " - Quantity: " + holdings.get(stockName));
        }
    }

    public String getPortfolioDetails() {

        StringBuilder sb = new StringBuilder();
        sb.append("===== YOUR PORTFOLIO =====\n\n");
        sb.append("Balance: ₹").append(balance).append("\n\n");

        if (holdings.isEmpty()) {
            sb.append("No stocks owned.");
            return sb.toString();
        }

        for (String stockName : holdings.keySet()) {
           sb.append(stockName)
             .append(" - Quantity: ")
             .append(holdings.get(stockName))
             .append("\n");
        }

        return sb.toString();
    }
}