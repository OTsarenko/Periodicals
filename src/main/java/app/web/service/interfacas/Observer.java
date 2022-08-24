package app.web.service.interfacas;

import app.entity.Event;

public interface Observer {
    void update(Event event);
}
