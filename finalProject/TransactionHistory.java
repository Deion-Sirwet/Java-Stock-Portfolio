//Deion Sirwet IFT 210 Final Project
//This is the code for my TransactionHistory class
package finalProject;

public class TransactionHistory {
	private String ticker;
	private String transDate;
	private String transType;
	private double qty;
	private double costBasis;
	
	public TransactionHistory() {
		setTicker("Default");
		transDate = "Mo/Da/Year";
		transType = "BUY, SELL, DEPOSIT, WITHDRAW";
		qty = 0.0;
		costBasis = 1.0;
	}
	
	public TransactionHistory(String ticker, String transDate, String transType, double qty, double costBasis) {
		this.ticker = ticker;
		this.transDate = transDate;
		this.transType = transType;
		this.qty = qty;
		this.costBasis = costBasis;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	
	public String getTransDate() {
		return transDate;
	}
	
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public String getTransType() {
		return transType;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}
	
	public double getQty() {
		return qty;
	}
	
	public void setCostBasis(double costBasis) {
		this.costBasis = costBasis;
	}
	
	public double getCostBasis() {
		return costBasis;
	}
	
	public void printTH() {
		System.out.printf("%-14s %-14s %-14.2f %-14.2f %-14s\n", transDate, ticker, qty, costBasis, transType);
	}
}
