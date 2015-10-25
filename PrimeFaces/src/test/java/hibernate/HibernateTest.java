package hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.util.IOUtils;


public class HibernateTest {

	public static void main(String[] args) throws IOException {
		
		URL url = new URL("http://ia.media-imdb.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg");
		InputStream is = url.openStream();
		byte[] byteData = null;
		byteData = IOUtils.toByteArray(is);
		System.out.println(byteData);
		
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
	}
}
