package UnitTests;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

import ch.makery.address.view.MortgageController;

public class MortgageController_Test {

	@Test
	public void pmtTest() {
		double pmt = FinanceLib.pmt(0.04 / 12, 360 , 300000, 0, false);
			
		System.out.println(pmt);
		assertEquals(pmt, -1432.25, 0.01);
	}

	@Test
	public void mortgageTest() {
		double expectedMortgage = MortgageController.mortgage(4, 30, 300000);
		System.out.println(expectedMortgage);
		assertEquals(expectedMortgage, 1432.25, 0.01);
	}
}
