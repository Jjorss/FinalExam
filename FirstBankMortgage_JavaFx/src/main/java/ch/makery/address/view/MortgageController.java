package ch.makery.address.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {

	
	@FXML
	private TextField txtIncome;
	
	@FXML
	private TextField txtExpenses;
	
	@FXML
	private TextField txtCreditScore;
	
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private Labeled output;
	
	@FXML
	private Labeled lblMortgagePayment;
	
	@FXML
	private ComboBox cmbTerm;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Got this from the web form on sakai
    	// without, when ever you click on the combo box it
    	// forms an infinite loop.
    	cmbTerm . setOnMousePressed ( new EventHandler < MouseEvent >(){

    		 @Override

    		 public void handle ( MouseEvent event ) {

    			 cmbTerm . requestFocus (); } });
    	
    	cmbTerm.getItems().addAll (
    		"15",
    		"30"
    			);
    	lblMortgagePayment.setVisible(false);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    // Calculates monthly mortgage payment
    public static double mortgage(double interestRate, double term, double costOfHouse) {
    	return (-1 * FinanceLib.pmt((interestRate / 100) / 12, term * 12 , costOfHouse, 0, false));
    }
    
    // runs when "submit" button is pressed, determines if house is affordable
    // or not.
    @FXML
    private void calculate() {
    	lblMortgagePayment.setVisible(false);
    	Double income = Double.parseDouble(this.txtIncome.getText());
    	Double monthlyExpense = Double.parseDouble(this.txtExpenses.getText());
    	int creditScore = Integer.parseInt(this.txtCreditScore.getText());
    	Double costOfHouse = Double.parseDouble(this.txtHouseCost.getText());
    	Double term = Double.parseDouble(this.cmbTerm.getValue().toString());
    	Double interestRate = RateDAL.getRate(creditScore);
    	
    	// Calculate mortgage based on term, house cost, and interest rate based on credit score 
    double mortgage = mortgage(interestRate, term, costOfHouse);
    	
    	if(mortgage <= income * 0.36 && mortgage <= (income + (monthlyExpense * 2)) * 0.28) {
    		DecimalFormat df = new DecimalFormat("#.##");
    		String fMortgage = df.format(mortgage);
    		
    		this.output.setText("You Can afford this house!");
    		lblMortgagePayment.setVisible(true);
    		this.lblMortgagePayment.setText("Your monthly payment is: $" + fMortgage);
    	} else {
    		this.output.setText("House Cost Too High");
    		System.out.println("mortgage = " + mortgage);
    		System.out.println("income * 0.36 = " + income * 0.36);
    		System.out.println("(Income + Expenses) * 18% = " + (income + (monthlyExpense * 2)) * 0.18);
    	}
 
    }
   
}