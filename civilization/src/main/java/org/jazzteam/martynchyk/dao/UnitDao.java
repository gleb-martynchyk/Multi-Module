package org.jazzteam.martynchyk.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Unit getUnit(int unit) {
        //getSession().get(Unit.class,unit);
        getSession().byId();
        return unit;
    }

    public Unit createUnit(Unit unit) {
        getSession().save(unit);
        return unit;
    }
}
