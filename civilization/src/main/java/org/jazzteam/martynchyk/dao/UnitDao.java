package org.jazzteam.martynchyk.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Unit get(Long id) {
        return getSession().find(Settler.class, id);
    }

    public Unit create(Unit unit) {
        getSession().save(unit);
        return unit;
    }
}
