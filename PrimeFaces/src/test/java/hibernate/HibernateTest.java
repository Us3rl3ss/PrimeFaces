package hibernate;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {

//		SessionFactory sf = new Configuration().configure()
//				.buildSessionFactory();
//		Session s = sf.openSession();
//
//		s.beginTransaction();
		
//		@SuppressWarnings("unchecked")
//		List<Projection> p1 = s.createQuery("select p from Movie m join m.actorList a join m.projection p").list();
//		List<Projection> p2 = s.createQuery("from Projection where current_timestamp < date and theater_id = 17 group by movie_id").list();
//		User user2 = (User) s.createQuery("from User where id = 7").uniqueResult();
//		User user3 = (User) s.createQuery("from User where id = 24").uniqueResult();
//		user1.getUserFollowList().add(user3);
		
//		Criteria criteria = s.createCriteria(Projection.class)
//			.createAlias("movie", "m")
//			.createAlias("m.actorList", "a");
//		criteria.add(Restrictions.gt("id", 12));
		
//		List<Projection> p3 = criteria.list();
		
		
//		List<Actor> list = s.createQuery("select actorList from Movie").list();
//		String result = "test test, ";
//		
//		System.out.println(result.substring(0, result.length() - 2));
//		System.out.println(result.substring(0, result.length() - 3));
//		System.out.println(result.substring(0, result.length() - 4));
		
//		Cinema c = new Cinema();
//		c.setName("222");
//		c.setNumber_of_seats(3);
//		c.setTheater((Theater)s.createQuery("from Theater where id = 17").list().get(0));
//		CinemaSeats cs1 = new CinemaSeats();
//		cs1.setSeats_row("Znj");
//		cs1.setSeats_number(1);
//		CinemaSeats cs2 = new CinemaSeats();
//		cs2.setSeats_row("Znj");
//		cs2.setSeats_number(2);
//		CinemaSeats cs3 = new CinemaSeats();
//		cs3.setSeats_row("Znj");
//		cs3.setSeats_number(3);
		
//		List<CinemaSeats> csList = new ArrayList<CinemaSeats>();
//		csList.add(cs1);
//		csList.add(cs2);
//		csList.add(cs3);
		
		// bidirectional association
//		c.addToCinemaSeatsList(cs1);
//		c.addToCinemaSeatsList(cs2);
//		c.addToCinemaSeatsList(cs3);
		

//		s.save(c);
//		s.getTransaction().commit();
//		s.close();
		
		System.out.println(setCinemaSeatsList(10, 20).size());
		
		 List<CinemaSeats> cs = setCinemaSeatsList(10,20);
		
		for (int seat=0; seat<cs.size(); seat++) {
			
			System.out.println(cs.get(seat).getSeats_row() + " " + cs.get(seat).getSeats_number());
		}
	}
	
	private static List<CinemaSeats> setCinemaSeatsList(int numberOfRows, int numberOfSeatsInRow) {

		List<CinemaSeats> result = new ArrayList<CinemaSeats>();
		
		for (int row=1; row<=numberOfRows; row++) {
			
			for (int seat=1; seat<=numberOfSeatsInRow; seat++) {
				
				CinemaSeats cs = new CinemaSeats();
				cs.setSeats_row(remapRows(row));
				cs.setSeats_number(seat);
				
				result.add(cs);
				
//				System.out.println(cs.getSeats_row() + " " + cs.getSeats_number());
			}
		}
		
		return result;
	}
	
	private static String remapRows(int row) {
		
		String result = "";
		
		if (row > 26) {
			
			int timesLarger = (int)row/26;
			row = row - (timesLarger * 26);
			
			
			String tempChar;
			
			if (row == 0) {
				
				row = 90;
				
				tempChar = new Character((char) row).toString();
				for(int i=0; i<timesLarger; i++) {
					
					result += tempChar;
				}
			} else {
				
				row += 64;
				
				tempChar = new Character((char) row).toString();
				for(int i=0; i<=timesLarger; i++) {
					
					result += tempChar;
				}
			}
			
		} else {
			
			// A - 65
			// Z - 90
			row += 64;
			result = new Character((char) row).toString();
		}
			
		return result;
	}
}
