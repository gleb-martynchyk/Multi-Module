package org.jazzteam.martynchyk.services.implementation;

import org.jazzteam.martynchyk.dao.implementation.UnitDao;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitService implements BaseService<Unit> {

    @Autowired
    private UnitDao unitDao;

    @Override
    public Unit create(Unit object) {
        return unitDao.create(object);
    }

    @Override
    public Unit find(long id) {
        return unitDao.find(id);
    }

    @Override
    public List<Unit> findAll() {
        return unitDao.findAll();
    }

    @Override
    public Unit update(Unit object) {
        return unitDao.update(object);
    }

    @Override
    public void delete(long id) {
        unitDao.delete(id);
    }
}
