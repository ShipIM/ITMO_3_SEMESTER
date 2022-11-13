package DataBase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class TriangleHitDAO implements Serializable, DAO<DataBaseTriangleHit, Integer> {
    public TriangleHitDAO() {

    }

    @Override
    public List<DataBaseTriangleHit> getAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DataBaseTriangleHit> criteria = builder.createQuery(DataBaseTriangleHit.class);
            criteria.from(DataBaseTriangleHit.class);

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void create(DataBaseTriangleHit entity) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}
