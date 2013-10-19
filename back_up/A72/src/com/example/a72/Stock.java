package com.example.a72;

public class Stock {
	private String stockName;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}





	

	public String getProfit(){
		return (bidPrice-buyPrice)/buyPrice*100+"%";
	}


	private int bidPrice;
	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getHands() {
		return hands;
	}

	public void setHands(int hands) {
		this.hands = hands;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}


	private int hands;
	private int buyPrice;	
	
	public Stock(String stockName, String buyPrice,String bidPrice,String hands) {
		this.stockName = stockName;
		this.buyPrice = Integer.parseInt(buyPrice);
		this.bidPrice=Integer.parseInt(bidPrice);
		this.hands=Integer.parseInt(hands);
	}
}
