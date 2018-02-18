package books.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addBook(String title, String description, String author, String isbn, int year) {
            Book book = new Book(title, description, author, isbn, year);
            sessionFactory.getCurrentSession().save(book);
    }

    public void deleteBook(int id) {
        sessionFactory.getCurrentSession().
                createQuery("DELETE Book WHERE id = :id").
                setParameter("id", id).
                executeUpdate();
    }

    public void updateBook(int id, String title, String description, String isbn, int year) {
        sessionFactory.getCurrentSession().
                createQuery("UPDATE Book SET " +
                "title = :title," +
                "description = :description,"+
                "isbn = :isbn," +
                "printYear = :printYear," +
                "readAlready = false "+
                "WHERE id = :id").
                setParameter("id", id).
                setParameter("title", title).
                setParameter("description", description).
                setParameter("isbn", isbn).
                setParameter("printYear", year).
                executeUpdate();
    }

    public void readBook(int id) {
        sessionFactory.getCurrentSession().
                createQuery("UPDATE Book SET " +
                "readAlready = true "+
                "WHERE id = :id").
                setParameter("id", id).
                executeUpdate();
    }

    public int getCount(Filter filter) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Book> root = query.from(Book.class);
        Predicate condition = makePridicates(filter, root, cb);
        if (condition != null) {
            query.where(condition);
        }
        query.select(cb.count(root));
        long result = sessionFactory.getCurrentSession().
                createQuery(query).getSingleResult();
        return (int) result;
    }


    public Book getBook(int id) {
        return (Book) sessionFactory.getCurrentSession().
                createQuery("FROM Book WHERE id = :id").
                setParameter("id", id).getSingleResult();
    }

    public List<Book> getBooks(Filter filter) {
        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        Predicate condition = makePridicates(filter, root, cb);
        if (condition != null) {
            query.where(condition);
        }
        query.select(root);
        return sessionFactory.getCurrentSession().
                createQuery(query).
                setFirstResult(filter.getFirstResult()).
                setMaxResults(10).
                getResultList();
    }

    private Predicate makePridicates(Filter filter, Root<Book> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (filter.isTitleFilter()) {
            predicates.add(cb.like(root.<String>get("title"), filter.getTitle()));
        }

        if (filter.isReadAlreadyFilter()) {
            predicates.add(cb.equal(root.get("readAlready"), filter.isReadAlready()));
        }

        if (filter.isAfterPrintYearFilter()) {
            predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("year"), filter.getPrintYear()));
        }

        if (filter.isBeforePrintYearFilter()) {
            predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("year"), filter.getPrintYear()));
        }

        if (predicates.size()>0) {
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }

        return null;
    }
}
