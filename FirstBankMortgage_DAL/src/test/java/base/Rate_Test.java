package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class Rate_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void selectRateTest() {
		ArrayList<RateDomainModel> rates = RateDAL.selectRate();
		assertTrue(rates.size() > 0);
	}
	
	@Test
	public void getRateTest() {
		Double rate1 = RateDAL.getRate(600);
		assertTrue(rate1 == 5);
		
		Double rate2 = RateDAL.getRate(800);
		assertTrue(rate2 == 3.5);
	}

}
