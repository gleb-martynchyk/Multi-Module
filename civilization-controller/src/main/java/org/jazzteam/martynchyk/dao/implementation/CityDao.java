package org.jazzteam.martynchyk.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jazzteam.martynchyk.dao.BaseDao;
import org.jazzteam.martynchyk.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDao implements BaseDao<City> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public City create(City city) {
        Session session = getSession();
        session.save(city);
        session.flush();
        return city;
    }

    @Override
    public City find(long id) {
        return getSession().find(City.class, id);
    }

    @Override
    public List<City> findAll() {
        return getSession()
                .createQuery("SELECT city FROM City city", City.class)
                .getResultList();
    }

    @Override
    public City update(City city) {
        Session session = getSession();
        session.update(city);
        session.flush();
        return city;
    }

    @Override
    public void delete(long id) {
        Session session = getSession();
        City city = session.getReference(City.class, id);
        session.delete(city);
        session.flush();
    }
}
