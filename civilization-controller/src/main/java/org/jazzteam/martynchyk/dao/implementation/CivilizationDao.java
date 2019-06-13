package org.jazzteam.martynchyk.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.dao.BaseDao;
import org.jazzteam.martynchyk.entity.Civilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        session.save(civilization);
        session.flush();
        return civilization;
    }

    @Override
    public Civilization find(long id) {
        return getSession().find(Civilization.class, id);
    }

    @Override
    public List<Civilization> findAll() {
        return getSession()
                .createQuery("SELECT civilization FROM Civilization civilization", Civilization.class)
                .getResultList();
    }

    @Override
    public Civilization update(Civilization civilization) {
        Session session = getSession();
        session.update(civilization);
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
