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
public class StarHitDAO implements Serializable, DAO<DataBaseStarHit, Integer> {
    public StarHitDAO() {

    }

    @Override
    public List<DataBaseStarHit> getAll() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DataBaseStarHit> criteria = builder.createQuery(DataBaseStarHit.class);
            criteria.from(DataBaseStarHit.class);

            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void create(DataBaseStarHit entity) {
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
