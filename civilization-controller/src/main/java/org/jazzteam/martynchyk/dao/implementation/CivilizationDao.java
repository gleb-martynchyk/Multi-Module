package org.jazzteam.martynchyk.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.dao.BaseDao;
import org.jazzteam.martynchyk.entity.Civilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CivilizationDao implements BaseDao<Civilization> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Civilization create(Civilization civilization) {
        Session session = getSession();
        session.persist(civilization);
        session.flush();
        return civilization;
    }

    @Override
    public Civilization find(long id) {
        return getSession().get(Civilization.class, id);
    }

    @Override
    public List<Civilization> findAll() {
        Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Civilization> cq = cb.createQuery(Civilization.class);
        Root<Civilization> rootEntry = cq.from(Civilization.class);
        CriteriaQuery<Civilization> all = cq.select(rootEntry);

        TypedQuery<Civilization> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public Civilization update(Civilization civilization) {
        Session session = getSession();
        session.merge(civilization);
        session.flush();
        return civilization;
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        Civilization civilization = session.getReference(Civilization.class, id);
        session.delete(civilization);
        session.flush();
    }
}
