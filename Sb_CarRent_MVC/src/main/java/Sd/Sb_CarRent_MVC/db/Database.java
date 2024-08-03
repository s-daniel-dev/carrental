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
import Sd.Sb_CarRent_MVC.model.Lease;
import Sd.Sb_CarRent_MVC.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {

		Configuration cfg = new Configuration();
		cfg.configure();
		this.sessionFactory = cfg.buildSessionFactory();
		
	}

	public List<Car> getCarsBetweenDates(LocalDate from, LocalDate to) {

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
		
		
		tx.commit();
		session.close();
		
		
		return cars;
	}

	public int persistUser(User user) {

		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(user);
		SelectionQuery<User> query = session.createSelectionQuery(
					"SELECT u FROM User u WHERE u.id = (SELECT MAX(u.id) FROM User u)",
					User.class
				);
		
		User newUser = query.getSingleResult();
		
		tx.commit();
		session.close();
		
		
		return newUser.getId();
	}

	public void persistLease(Lease lease) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(lease);
		
		tx.commit();
		session.close();
		
	}
	
	
	
	
	

}
