package Sd.Sb_CarRent_MVC.db;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import Sd.Sb_CarRent_MVC.model.Car;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();
		this.sessionFactory = cfg.buildSessionFactory();
		
	}

	public List<Car> getCarsByBetweenDate(LocalDate from, LocalDate to) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> query = session.createSelectionQuery(
					"SELECT c FROM Car c WHERE c.active=?3 AND c.id NOT IN "
							+ 		"(SELECT l.carId FROM Lease l WHERE "
									+ 			"( (l.from >= ?1) AND (l.from < ?2) ) OR "
								  	+		    "( (l.from <= ?1) AND (l.to >= ?2) ) OR "
								  	+ 			"( (l.to > ?1) AND (l.to <= ?2) ) OR "
								  	+ 			"( (l.from > ?1) AND (l.to < ?2) ))",
					Car.class
				);
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, true);
		
		List<Car> cars = query.getResultList();		
		
		System.out.println(cars);
		
		tx.commit();
		session.close();
		
		
		return cars;
	}
	
	
	
	
	

}
