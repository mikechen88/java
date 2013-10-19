package foolstudio.demo;

import com.db4o.query.Predicate;

import foolstudio.util.Payout;

//Î½´ÊÊµÀý
public class JournalBookPredicate extends Predicate<Payout> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mColumnName = null;
	private String mOperator = null;
	private String mValue = null;
	
	public JournalBookPredicate(String columnName, String operator, String value) {
		mValue = value;
		mOperator = operator;
		mColumnName = columnName;
		//µ÷ÊÔ
		System.out.println(mColumnName + " " + mOperator + " " + mValue);
	}

	@Override
	public boolean match(Payout payout) {
		// TODO Auto-generated method stub
		
		if(mColumnName.compareToIgnoreCase("Timestamp") == 0) {
			if(mOperator.compareToIgnoreCase("=") == 0) {
				return (payout.getTimestamp().equals(mValue) );
			}
			else if(mOperator.compareToIgnoreCase("LIKE") == 0) {
				return (payout.getTimestamp().indexOf(mValue) != -1);
			}		
		}
		else if(mColumnName.compareToIgnoreCase("Comments") == 0) {
			if(mOperator.compareToIgnoreCase("=") == 0) {
				return (payout.getComments().equals(mValue) );
			}
			else if(mOperator.compareToIgnoreCase("LIKE") == 0) {
				return (payout.getComments().indexOf(mValue) != -1);
			}			
		}
		else { //Money
			if(mOperator.compareToIgnoreCase("=") == 0) {
				return (payout.getMoney() == Double.parseDouble(mValue) );
			}
			else if(mOperator.compareToIgnoreCase(">") == 0) {
				return (payout.getMoney() > Double.parseDouble(mValue) );
			}
			else if(mOperator.compareToIgnoreCase("<") == 0) {
				return (payout.getMoney() < Double.parseDouble(mValue) );
			}			
		}
		
		return (false);
	}
	
	//--------------------------------------------------------------------------
};
