package foolstudio.demo;

//֧����¼����
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
	//����ʱ���
	public void setTimestamp(final String __timestamp) {
		timestamp = __timestamp;
	}

	//-------------------------------------------------------------------------
	//��ȡʱ���
	public String getTimestamp() {
		return (timestamp);
	}

	//-------------------------------------------------------------------------
	//����̧ͷ
	public void setTitle(final String __title) {
		title = __title;
	}

	//-------------------------------------------------------------------------
	//��ȡ̧ͷ
	public String getTitle() {
		return (title);
	}

	//-------------------------------------------------------------------------
	//���ý��
	public void setMoney(final double __money) {
		money = __money;
	}

	//-------------------------------------------------------------------------
	//��ȡ���
	public double getMoney() {
		return (money);
	}

	//-------------------------------------------------------------------------
	public String toString() {
		return (timestamp + "," + title + "," + money);
	}

	//-------------------------------------------------------------------------
};
