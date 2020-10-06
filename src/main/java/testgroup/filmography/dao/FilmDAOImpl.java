package testgroup.filmography.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Film;

import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {
    private SessionFactory sessionFactory;
    //private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    //private static Map<Integer, Film> films = new HashMap<>();

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Film> allFilms(int page, int pageLength) {
        //return new ArrayList<>(films.values());

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Film")
                .setFirstResult(pageLength * (page - 1))
                .setMaxResults(pageLength)
                .list();
    }

    @Override
    public void add(Film film) {
        /*
        int id = AUTO_ID.getAndIncrement();

        film.setId(id);
        films.put(id, film);
        */

        Session session = sessionFactory.getCurrentSession();

        session.persist(film);
    }

    @Override
    public void delete(Film film) {
        //films.remove(film.getId());

        Session session = sessionFactory.getCurrentSession();

        session.delete(film);
    }

    @Override
    public void edit(Film film) {
        //films.put(film.getId(), film);

        Session session = sessionFactory.getCurrentSession();

        session.update(film);
    }

    @Override
    public Film getById(int id) {
        //return films.get(id);

        Session session = sessionFactory.getCurrentSession();

        return session.get(Film.class, id);
    }

    @Override
    public int filmsCount() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("SELECT COUNT(*) FROM Film", Number.class).getSingleResult().intValue();
    }
}
