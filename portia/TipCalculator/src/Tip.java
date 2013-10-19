
public class Tip {
	//private fields
	private double preTaxAmount;
	private double tipPercent;
	private double taxPercent;
	public Tip(){
	setPreTaxAmount(0);
	setTipPercent(0);
	setTaxPercent(0);		
	}
	public Tip(double preTaxAmount,double tipPercent,double taxPercent){
		
		setPreTaxAmount(preTaxAmount);
		setTipPercent(tipPercent);
		                             setTaxPercent(taxPercent);
			
	}
	public double getPreTaxAmount() {
		return preTaxAmount;
	}
	public void setPreTaxAmount(double preTaxAmount) {
		this.preTaxAmount = preTaxAmount;
	}
	public double getTipPercent() {
		return tipPercent;
	}
	public void setTipPercent(double tipPercent) {
		if (tipPercent>=1)
			tipPercent/=100;
		this.tipPercent = tipPercent;
	}
	public double getTaxPercent() {
		
		return taxPercent;
	}
	public void setTaxPercent(double taxPercent) {
		if (taxPercent>=1)
			taxPercent/=100;
		this.taxPercent = taxPercent;
	}
	public double CalculateTip()
	{
		return getPreTaxAmount()*getTipPercent();
		
		
		
	}
	public double CalculateTax()
	{
		return getPreTaxAmount()*getTaxPercent();
		
		
		
	}
	public double CalculateTotal(){
	return getPreTaxAmount()+CalculateTip()+CalculateTax();	
	}
	}


