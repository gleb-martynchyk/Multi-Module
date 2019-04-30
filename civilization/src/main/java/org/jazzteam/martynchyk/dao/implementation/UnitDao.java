package org.jazzteam.martynchyk.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.dao.BaseDao;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitDao implements BaseDao<Unit> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Unit create(Unit unit) {
        Session session = getSession();
        session.save(unit);
        session.flush();
        return unit;
    }

    @Override
    public Unit find(long id) {
        return getSession().find(Settler.class, id);
    }

    @Override
    public List<Unit> findAll() {
        return getSession()
                .createQuery("SELECT unit FROM Unit unit", Unit.class)
                .getResultList();
    }

    @Override
    public Unit update(Unit unit) {
        Session session = getSession();
        session.update(unit);
        session.flush();
        return unit;
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        Unit unit = session.getReference(Settler.class, id);
        session.delete(unit);
        session.flush();
    }
}
