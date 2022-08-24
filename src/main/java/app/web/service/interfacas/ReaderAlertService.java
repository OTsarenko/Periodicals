package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Periodical;

public interface ReaderAlertService {
    void update (Periodical periodical) throws DbException;
}
