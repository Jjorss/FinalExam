package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.RateDomainModel;
import util.HibernateUtil;

public class RateDAL {

	//FinalExam - please implement		
			// Figure out which row makes sense- return back the 
			// right interest rate from the table based on the given credit score
			
			//FinalExam - obviously change the return value
			
	public static double getRate(int GivenCreditScore) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel rateGet = null;

		try {
			tx = session.beginTransaction();

			Query query = session.createQuery("from RateDomainModel where MinCreditScore = :MINCREDITSCORE ");
			query.setParameter("MINCREDITSCORE", GivenCreditScore);

			rateGet = (RateDomainModel) query.list().get(0);

			tx.commit();

		} catch (IndexOutOfBoundsException ex) {
			rateGet = null;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rateGet.getInterestRate();
		
	}
	
	//created just for testing if number of rows is greater than 0
	public static ArrayList<RateDomainModel> selectRate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		RateDomainModel rateGet = null;		
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List Lrates = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = Lrates.iterator(); iterator.hasNext();) {
				RateDomainModel newRate = (RateDomainModel) iterator.next();
				rates.add(newRate);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return rates;

	}

}