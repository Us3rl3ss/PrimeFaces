package hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public final class HibernateUtil {

	private HibernateUtil() {
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * buildSessionFactory
	 * @return
	 */
	public static SessionFactory buildSessionFactory() {

		try {
			return new AnnotationConfiguration().configure(new File("src/main/java/hibernate.cfg.xml")).buildSessionFactory();
		}
		catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * shutdown
	 */
	public static void shutdown() {
		// Close caches and connection pools
		getSessionfactory().close();
	}

	/**
	 * @return the sessionfactory
	 */
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}