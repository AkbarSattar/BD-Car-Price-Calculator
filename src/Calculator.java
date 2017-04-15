import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.Locale;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.NumberFormat;

//BUILT BY AKBAR SATTAR



public class Calculator {

	protected Shell shlBangladeshCarPrice;
	private Text carName;
	private Text carPrice;
	private Text carCC;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Calculator window = new Calculator();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBangladeshCarPrice.open();
		shlBangladeshCarPrice.layout();
		while (!shlBangladeshCarPrice.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBangladeshCarPrice = new Shell();
		shlBangladeshCarPrice.setSize(513, 300);
		shlBangladeshCarPrice.setText("Bangladesh Car Price Calculator v1.0");
		
	// Name text field 
		carName = new Text(shlBangladeshCarPrice, SWT.BORDER);
		carName.setBounds(10, 30, 202, 19);
		
	//Name label 	
		Label lblEnterTheCars = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblEnterTheCars.setBounds(10, 10, 182, 28);
		lblEnterTheCars.setText("Enter the vehicle's name ");
	
	//Price label
		Label lblEnterTheCars_1 = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblEnterTheCars_1.setBounds(10, 55, 202, 28);
		lblEnterTheCars_1.setText("Enter the vehicle's price ($US)");
		
	//Price text field
		carPrice = new Text(shlBangladeshCarPrice, SWT.BORDER);
		carPrice.setBounds(10, 75, 64, 19);
		
	//Displacement text field 	
		carCC = new Text(shlBangladeshCarPrice, SWT.BORDER);
		carCC.setBounds(10, 120, 64, 19);
	
	//Engine displacement label 
		Label lblEnterTheCars_2 = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblEnterTheCars_2.setBounds(10, 100, 293, 28);
		lblEnterTheCars_2.setText("Enter the engine displacement (CC)");
	
	// Hybrid Radio Button 
		Button isHybrid = new Button(shlBangladeshCarPrice, SWT.CHECK);
		isHybrid.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		isHybrid.setBounds(10, 145, 182, 28);
		isHybrid.setText("The vehicle is a hybrid");
		
	// "This car price is" label 
		Label resultAnnouncement = new Label(shlBangladeshCarPrice, SWT.NONE);
		resultAnnouncement.setBounds(233, 33, 266, 28);
		resultAnnouncement.setText("This vehicle would cost");
		
	// Final result label 
		Label result = new Label(shlBangladeshCarPrice, SWT.NONE);
		result.setFont(SWTResourceManager.getFont(".SF NS Text", 14, SWT.NORMAL));
		result.setBounds(233, 52, 256, 28);
		result.setText("$0.00 after import taxes*");

// SUBMIT BUTTON
		Button submitButton = new Button(shlBangladeshCarPrice, SWT.NONE);
		submitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int carValue = 0;
				int engineSize = 0;
				
		// Get Car Price from text entry
				try{
					carValue = Integer.parseInt(carPrice.getText());
				}
			// Return error if text price is invalid or empty
				catch(Exception exc){
						MessageDialog.openError(shlBangladeshCarPrice, "Error", "Invalid price");
						return; 
				}
				
		// Get Engine Size from text
				try{
					engineSize = Integer.parseInt(carCC.getText());
				}
			// Return error if engine size is invalid or empty
				catch(Exception exc){
					MessageDialog.openError(shlBangladeshCarPrice, "Error", "Invalid displacement");
					return; 
				}
					
				// Declare variables 
					double finalPrice = 0;
					boolean iscarHybrid = false;
				// Find out if we've selected hybrid or not 
					iscarHybrid = isHybrid.getSelection();
					

// CALCULATIONS BEGIN HERE
					
						//0-1500cc tax bracket
					if (engineSize > 0 && engineSize <= 1500 && iscarHybrid == false){
						
						finalPrice = (carValue * 1.31) + carValue; 
						
						//1501-2000cc tax bracket
					} else if (engineSize > 1500 && engineSize <= 2000 &&
							iscarHybrid == false){
					
						finalPrice = (carValue * 2.17) + carValue; 
						
						//2001-2750cc tax bracket
					} else if (engineSize > 2000 && engineSize <= 2750 &&
							iscarHybrid == false){
						
						finalPrice = (carValue * 3.70) + carValue; 
						
						//2751-4000cc tax bracket
					} else if (engineSize > 2750 && engineSize <= 4000 &&
							iscarHybrid == false){	
						
						finalPrice = (carValue * 6.08) + carValue; 
						
						//4000cc and above tax bracket 
					} else if (engineSize > 4000 
							&& iscarHybrid == false){
		
						finalPrice = (carValue * 8.50) + carValue; 
						
						//Hybrid vehicle below 1500cc
					} else if (engineSize < 1500 && iscarHybrid == true){
						
						finalPrice = (carValue * 1.05) + carValue; 
						
						//Hybrid vehicle 1501-2000cc
					} else if (engineSize > 1500 && engineSize <= 2000 && 
							iscarHybrid == true){
						
						finalPrice = (carValue * 1.54) + carValue; 
						
						//Hybrid vehicle 2001-2500cc
					} else if (engineSize > 2000 && engineSize >= 2500 && iscarHybrid == true){
						
						finalPrice = (carValue * 2.96) + carValue;
						
						//Hybrid vehicle 2501cc ++ 
					} else if (engineSize > 2500 && iscarHybrid == true){
						
						finalPrice = (carValue * 5.00) + carValue; 
						
					} else {
						MessageDialog.openError(shlBangladeshCarPrice, "Error", "Please check inputs");
						return; 
					}
					
//CALCULATIONS END HERE 
					
			//Convert the value into a legible string 
				String resultString = NumberFormat.getNumberInstance(Locale.US).format(finalPrice);
			//Make alter the message to show car name 
				resultAnnouncement.setText("The " + carName.getText() + " would cost around");
			//Display the price 
				result.setText("$" + resultString + " after import taxes*");
			}
		});
		submitButton.setBounds(55, 185, 94, 28);
		submitButton.setText("Submit");
		
		
// CLEAR BUTTON 
		Button clearButton = new Button(shlBangladeshCarPrice, SWT.NONE);
		
		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			carName.setText("");
			carPrice.setText("");
			carCC.setText("");
			resultAnnouncement.setText("This vehicle would cost");
			result.setText("$0.00 after import taxes*");
				
			}
		});
		clearButton.setBounds(55, 219, 94, 28);
		clearButton.setText("Clear");

		
		
// Labels 		
		
		Label lblNewLabel = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblNewLabel.setBounds(233, 75, 166, 19);
		lblNewLabel.setText("in Bangladesh**");
		
		Label lblPleaseNote = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblPleaseNote.setBounds(233, 123, 266, 50);
		lblPleaseNote.setText("* Please note that this figure does not include\nother fees such as dealer commission and\nshipping costs ");
		
		Label lblThePricing = new Label(shlBangladeshCarPrice, SWT.NONE);
		lblThePricing.setBounds(233, 182, 266, 52);
		lblThePricing.setText("** The pricing represented is approximate and\nsubject to change. The information presented\nis valid for the fiscal year of 2016-2017\n");
		

		
	}
}
