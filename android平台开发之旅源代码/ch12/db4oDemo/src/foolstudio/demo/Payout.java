package foolstudio.demo;

//支付记录对象
public class Payout {
	private String timestamp = null;
	private String title = null;
	private double money = 0.0D;

	//-------------------------------------------------------------------------
	public Payout(String __timestamp, String __title, double __money) {
		timestamp = __timestamp;
		title = __title;
		money = __money;
	}

	//-------------------------------------------------------------------------
	//设置时间戳
	public void setTimestamp(final String __timestamp) {
		timestamp = __timestamp;
	}

	//-------------------------------------------------------------------------
	//获取时间戳
	public String getTimestamp() {
		return (timestamp);
	}

	//-------------------------------------------------------------------------
	//设置抬头
	public void setTitle(final String __title) {
		title = __title;
	}

	//-------------------------------------------------------------------------
	//获取抬头
	public String getTitle() {
		return (title);
	}

	//-------------------------------------------------------------------------
	//设置金额
	public void setMoney(final double __money) {
		money = __money;
	}

	//-------------------------------------------------------------------------
	//获取金额
	public double getMoney() {
		return (money);
	}

	//-------------------------------------------------------------------------
	public String toString() {
		return (timestamp + "," + title + "," + money);
	}

	//-------------------------------------------------------------------------
};
