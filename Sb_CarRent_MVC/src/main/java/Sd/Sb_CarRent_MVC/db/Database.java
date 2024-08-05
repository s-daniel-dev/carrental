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

	public List<Car> getCarList() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> query = session.createSelectionQuery("SELECT c FROM Car c", Car.class);
		
		List<Car> cars = query.getResultList();
		
		tx.commit();
		session.close();
		
		
		return cars;
	}

	public List<Lease> getLeaseList() {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Lease> query = session.createSelectionQuery("SELECT l FROM Lease l", Lease.class);
		
		List<Lease> leases = query.getResultList();
		
		tx.commit();
		session.close();
		
		return leases;
	}

	public String getUserNameById(int userId) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.get(User.class, userId);
		
		tx.commit();
		session.close();
		
		String userName = user.getName();
		
		
		return userName;
	}

	public void mergeCar(Car car) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(car);
		
		tx.commit();
		session.close();
		
		
	}

	public List<Lease> getLeaseListByCarId(int carId) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Lease> query = session.createSelectionQuery(
					"SELECT l FROM Lease l WHERE l.carId=?1",
					Lease.class
				);
		query.setParameter(1, carId);
		
		List<Lease> leases = query.getResultList();
		
		tx.commit();
		session.close();
		
		return leases;
	}

	public void persistCar(Car car) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(car);
		
		tx.commit();
		session.close();
		
	}
	
	
	
	
	

}
