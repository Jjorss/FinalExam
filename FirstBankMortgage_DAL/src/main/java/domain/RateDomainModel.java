package domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class RateDomainModel implements Serializable {
	
	private int RateID;
	private int MinCreditScore;
	private double InterestRate;
	
	protected RateDomainModel()
	{	
	}
	
	public RateDomainModel(int RateID, int MinCreditScore, double InterestRate) {
		super();		
		this.RateID = RateID;
		this.MinCreditScore = MinCreditScore;
		this.InterestRate = InterestRate;
		
	}
	
	public RateDomainModel(RateDomainModel rate)
	{
		super();	
		RateID = rate.getRateID();
		MinCreditScore = rate.getMinCreditScore();
		InterestRate = rate.getInterestRate();
	}
	
	public int getRateID() {
		return RateID;
	}
	public void setRateID(int rateID) {
		RateID = rateID;
	}
	public int getMinCreditScore() {
		return MinCreditScore;
	}
	public void setMinCreditScore(int minCreditScore) {
		MinCreditScore = minCreditScore;
	}
	public double getInterestRate() {
		return InterestRate;
	}
	public void setInterestRate(double interestRate) {
		InterestRate = interestRate;
	}
	
	
}