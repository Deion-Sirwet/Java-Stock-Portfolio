//Deion Sirwet IFT 210 Final Project
//This is the code for my PortfolioManager class
package finalProject;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.util.Scanner;

public class PortfolioManager {

	private static ArrayList<TransactionHistory> portfolioList = new ArrayList<TransactionHistory>();
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String d = "Date";
		String t = "Ticker";
		String q = "Quantity";
		String cb = "Cost Basis";
		String tt = "Trans Type";
		double cashInAccount = 0;
		double totalCost = 0;
		double totalSale = 0;
		double totalStock = 0;
		
		int userInput;
		boolean end = true;
		
		while (end) {

			try {
		
				do {
//menu print out
					System.out.println("Deion Sirwet's Brokerage Account");
					System.out.println();
					System.out.println("0 - Exit");
					System.out.println();
					System.out.println("1 - Deposit Cash");
					System.out.println();
					System.out.println("2 - Withdraw Cash");
					System.out.println();
					System.out.println("3 - Buy Stock");
					System.out.println();
					System.out.println("4 - Sell Stock");
					System.out.println();
					System.out.println("5 - Display Transaction History");
					System.out.println();
					System.out.println("6 - Display Portfolio");
					System.out.println();
					System.out.println("Enter option (0 to 6): ");
		
					userInput = scnr.nextInt();
//checking if user input is between 0 and 6			
					while ((userInput > 6 || userInput < 0)) {
						System.out.println("You must enter a number between 0 and 6");
						userInput = scnr.nextInt();
					}
//breaking the loop			
					if (userInput == 0) {
						System.out.println();
						System.out.println("Goodbye.");
						end = false;
						break;
					}
//depositing cash			
					else if (userInput == 1) {
						try {
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							System.out.println("How much cash would you like to deposit? You can enter any letter to quit.");
							double depositAmount = scnr.nextDouble();
							while (depositAmount <= 0) {
								System.out.println("You must deposit more than 0 dollars.");
								depositAmount = scnr.nextDouble();
							}
							cashInAccount += depositAmount;
							TransactionHistory transaction = new TransactionHistory("CASH", dtf.format(now), "DEPOSIT", depositAmount, 1.00);
							portfolioList.add(transaction);
							
						} catch (java.util.InputMismatchException e) {
							System.out.println("\n");
							System.out.println("You did not deposit any cash.");
							System.out.println("You will now be sent back to the menu.");
							System.out.println("\n");
							String invalidInput = scnr.nextLine();
						}
					}
//withdrawing cash			
					else if (userInput == 2) {
						try {
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							System.out.println("How much cash would you like to withdraw? You can enter any letter to quit.");
							System.out.println("You have " + cashInAccount + " dollars in your account.");
							double withdrawAmount = scnr.nextDouble();
							while (withdrawAmount <= 0) {
								System.out.println("You must withdraw more than 0 dollars.");
								withdrawAmount = scnr.nextDouble();
							}
							if (cashInAccount - withdrawAmount < 0) {
								while (cashInAccount - withdrawAmount < 0) {
									System.out.println("You cannot withdraw more than what's in your account.");
									System.out.println("Please enter an amount of " + cashInAccount + " or less or enter any letter to quit.");
									withdrawAmount = scnr.nextDouble();
								}
							}
							cashInAccount -= withdrawAmount;
							TransactionHistory transaction = new TransactionHistory("CASH", dtf.format(now), "WITHDRAW", withdrawAmount, 1.00);
							portfolioList.add(transaction);
							
						} catch (java.util.InputMismatchException e) {
							System.out.println("\n");
							System.out.println("You did not withdraw any cash");
							System.out.println("You will now be sent back to the menu.");
							System.out.println("\n");
							String invalidInput = scnr.nextLine();
						}
					}
//buying stock					
					else if (userInput == 3) {
						try {
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							System.out.println("How much does each stock of DFEN cost? You can enter any letter to quit.");
							double stockCost = scnr.nextDouble();
							while (stockCost <= 0) {
								System.out.println("The cost of each stock must be more than 0. You can enter any letter to quit.");
								stockCost = scnr.nextDouble();
							}
							System.out.println("How many stocks would you like to buy? You can enter any letter to quit.");
							double quantityOfStock = scnr.nextDouble();
							while (quantityOfStock <= 0) {
								System.out.println("You must buy more than 0 stocks or you can enter any letter to quit.");
								quantityOfStock = scnr.nextDouble();
							}
							totalStock += quantityOfStock;
							totalCost = stockCost * quantityOfStock;
							if (cashInAccount - totalCost < 0) {
								while (cashInAccount - totalCost < 0) {
									System.out.println("You can't buy that much stock because you don't have enough cash in your account.");
									System.out.println("How many of each stock would you like to buy? You can enter any letter to quit.");
									quantityOfStock = scnr.nextDouble();
									totalCost = stockCost * quantityOfStock;
									while (quantityOfStock <= 0) {
										System.out.println("You must buy more than 0 stocks or you can enter any letter to quit.");
										quantityOfStock = scnr.nextDouble();
										totalCost = stockCost * quantityOfStock;
									}
								}
							}
							cashInAccount -= totalCost;
							TransactionHistory transaction = new TransactionHistory("DFEN", dtf.format(now), "BUY", quantityOfStock, stockCost);
							portfolioList.add(transaction);
							TransactionHistory transactionCash = new TransactionHistory("CASH", dtf.format(now), "WITHDRAW", totalCost, 1.00);
							portfolioList.add(transactionCash);
							
						} catch (java.util.InputMismatchException e) {
								System.out.println("\n");
								System.out.println("You did not buy any stock");
								System.out.println("You will now be sent back to the menu.");
								System.out.println("\n");
								String invalidInput = scnr.nextLine();
						}
					}
//selling stock					
					else if (userInput == 4) {
						try {
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							System.out.println("How much is each stock of DFEN worth? You can enter any letter to quit.");
							double stockCost = scnr.nextDouble();
							while (stockCost < 0) {
								System.out.println("The worth of each stock must be at least 0. You can enter any letter to quit.");
								stockCost = scnr.nextDouble();
							}
							System.out.println("How many stocks would you like to sell? You have " + totalStock + " stock(s).");
							System.out.println("You can enter any letter to quit.");
							double quantityOfStock = scnr.nextDouble();
							while (quantityOfStock <= 0) {
								System.out.println("You must sell more than 0 stocks or you can enter any letter to quit.");
								quantityOfStock = scnr.nextDouble();
							}
							while (totalStock - quantityOfStock < 0) {
								System.out.println("You can't sell that much stock because you don't have that much stock.");
								System.out.println("You only have " + totalStock + " stock(s). Please enter a valid amount or enter any letter to quit.");
								quantityOfStock = scnr.nextDouble();
							}
							totalStock -= quantityOfStock;
							totalSale = stockCost * quantityOfStock;
							cashInAccount += totalSale;
							TransactionHistory transaction = new TransactionHistory("DFEN", dtf.format(now), "SELL", quantityOfStock, stockCost);
							portfolioList.add(transaction);
							TransactionHistory transactionCash = new TransactionHistory("CASH", dtf.format(now), "DEPOSIT", totalSale, 1.00);
							portfolioList.add(transactionCash);
							
						} catch (java.util.InputMismatchException e) {
								System.out.println("\n");
								System.out.println("You did not sell any stock");
								System.out.println("You will now be sent back to the menu.");
								System.out.println("\n");
								String invalidInput = scnr.nextLine();
						}
					}
//display transaction history			
					else if (userInput == 5) {
						System.out.println();
						System.out.println("Deion Sirwet's Brokerage Account");
						System.out.println("================================");
						System.out.println();
						System.out.printf("%-14s %-14s %-14s %-14s %-14s\n", d, t, q, cb, tt);
						System.out.println("======================================================================");
						System.out.println();
						for (int i = 0; i < portfolioList.size(); ++i) {
							portfolioList.get(i).printTH();
						}
						System.out.println();
					}
//display portfolio					
					else if (userInput == 6) {
						LocalDateTime now = LocalDateTime.now();
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
						System.out.println("Portfolio as of: " + dtf.format(now));
						System.out.println("====================================");
						System.out.println("\n");
						System.out.printf("%-14s %-14s \n", t, q);
						System.out.println("=======================\n");
						ArrayList<TransactionHistory> uniqueList = new ArrayList<TransactionHistory>();
						double totalAmount = 0;
						double totalAmountStock = 0;
						for (int i = 0; i < portfolioList.size(); ++i) {
							TransactionHistory currentObj = portfolioList.get(i);
							boolean isDuplicate = false;
							
							for (int j = 0; j < uniqueList.size(); ++j) {
								if (currentObj.getTicker().equals(uniqueList.get(j).getTicker())) {
									isDuplicate = true;
									break;
								}
							}
							
							if (!isDuplicate) {
								uniqueList.add(currentObj);
							}
							if (currentObj.getTransType().equals("DEPOSIT")) {
								totalAmount += currentObj.getQty();
							}
								
							if (currentObj.getTransType().equals("WITHDRAW")) {
								totalAmount -= currentObj.getQty();
							}

							if (currentObj.getTransType().equals("BUY")) {
								totalAmountStock += currentObj.getQty();
							}
							
							if (currentObj.getTransType().equals("SELL")) {
								totalAmountStock -= currentObj.getQty();
							}

							//totalAmountStock = 0;
						}
						
						for (TransactionHistory transaction : uniqueList) {
							if (transaction.getTicker().equals("CASH")) {
								if (totalAmount > 0) {
									System.out.printf("%-14s %-14.2f\n", transaction.getTicker(), totalAmount);
								}
							}
							
							else {
								if (totalAmountStock > 0) {
									System.out.printf("%-14s %-14.2f\n", transaction.getTicker(), totalAmountStock);
								}
							}
						}
						System.out.println();
					}
			
				} while ((userInput >= 0) && (userInput <= 6));
				
			} catch (java.util.InputMismatchException e) {
				System.out.println("\n");
				System.out.println("You will now be sent back to the menu.");
				System.out.println("You must enter a whole number.");
				System.out.println("\n");
				String invalidInput = scnr.nextLine();
			}
		}
	}
	
}
