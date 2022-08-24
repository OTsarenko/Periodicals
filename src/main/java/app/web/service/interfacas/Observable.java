package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Event;

import java.util.List;

public interface Observable {

    List<Observer> findObserver(int eventType) throws DbException;
    void notifyObserver(Event event) throws DbException;
}
