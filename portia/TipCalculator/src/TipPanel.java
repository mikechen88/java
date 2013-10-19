import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class TipPanel extends JPanel implements ActionListener {

	 private JPanel panel;
	private JPanel buttonPanel;
	private JLabel lblAmount;
	private JLabel lblChoosePercent;
	private JRadioButton rbTenPercent;
	private JRadioButton rbFifteenPercent;
	private JRadioButton rbTwentyPercent;
	private JRadioButton rbOther;
	private JLabel lblOther;
	private JLabel lblTax;
	private JLabel lblTaPercent;
	private JLabel lblTipPercent;
	private JLabel lblTip;
	private JLabel lblTaxAmount;
	private JLabel lblTipAmount;
	private JLabel lblTotal;
	private JLabel lblTotalAmount;
	private JLabel lblTaxPercent;
	private JTextField tstTotal;
	private JTextField txtOther;
	private JButton btnCalculate;
	private JButton btnExit;
	private JTextField txtAmount;
	private JTextField txtTipAmount;
	private JTextField txtTaxAmount;
	private JTextField txtTaxPercent;
	private JTextField txtTotal;
	
	private double tipPercent;
private double tip;
	private double amount;
	private double taxPercent;
	
	
	public TipPanel()
	{
		CreatePanel();
		
		
		
		
	}
	private void CreatePanel(){
		panel =new JPanel();
		panel.setLayout(new GridLayout(10,2,5,5));//first two are x,y(10rows,2column); next two are spacing;
		lblAmount =new JLabel("enter pre tax");
		txtAmount=new JTextField(10);
		rbTenPercent=new JRadioButton("Ten Percent");
		rbFifteenPercent=new JRadioButton("Fifteen Percent");
		rbFifteenPercent.setSelected(true);
		rbTwentyPercent=new JRadioButton("Twenty");
		rbOther=new JRadioButton("Other");
		
		ButtonGroup group=new ButtonGroup();
		group.add(rbTenPercent);
		group.add(rbFifteenPercent);
		group.add(rbTwentyPercent);
		group.add(rbOther);
	
		lblTaxPercent=new JLabel("enter taxPercent");
		txtTaxPercent=new JTextField(10);
		lblOther=new JLabel("enter other amount");
		txtOther=new JTextField(10);
		lblTip=new JLabel("Tip amount");
		txtTipAmount=new JTextField(10);
		txtTipAmount.setEditable(false);
		
		lblTaxAmount=new JLabel("Tax Amount");
		txtTaxAmount=new JTextField(10);
		txtTaxAmount.setEditable(false);
		
		lblTotal =new JLabel("total");
		txtTotal=new JTextField(10);
		txtTotal.setEditable(false);
			
			panel.add(lblAmount);
		panel.add(txtAmount);
		panel.add(rbTenPercent);
		panel.add(rbFifteenPercent);
		panel.add(rbTwentyPercent);
		panel.add(rbOther);
		panel.add(lblOther);
		panel.add(txtOther);
		panel.add(lblTaxPercent);
		panel.add(txtTaxPercent);
		panel.add(lblTip);
		panel.add(txtTipAmount);
		panel.add(lblTaxAmount);
		panel.add(txtTaxAmount);
		panel.add(lblTotal);
		panel.add(txtTotal);
		
		
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnCalculate=new JButton("Calculate");
		btnExit=new JButton("Exit");
		buttonPanel.add(btnCalculate);
		buttonPanel.add(btnExit);
		
		this.setLayout(new BorderLayout());
		this.add(panel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source=e.getSource();
		if (source==btnExit)
			System.exit(0);
		else if (source==btnCalculate)
			getTipInfo();
		
	}
	private void getTipInfo()
	{
		String strAmount=txtAmount.getText();
		String taxperc=txtTaxPercent.getText();
		tipPercent=0;
		
		if(rbTenPercent.isSelected())
			tipPercent=.1;
		else if(rbFifteenPercent.isSelected())
		   tipPercent=.15;
		else if(rbTwentyPercent.isSelected())
			tipPercent=.2;
		else if (rbOther.isSelected())
		
		tipPercent=Double.parseDouble(txtOther.getText());
		else
			tipPercent=0;
		amount=Double.parseDouble(strAmount);
		taxPercent=Double.parseDouble(taxperc);
		
	}
	
	
}
