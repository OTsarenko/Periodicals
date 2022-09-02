package app.dao.mysql;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.dao.interfaces.TopicDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.Topic;
import app.util.ConnectionDataSource;
import app.util.ConstantsQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * The class, that represents periodicals DAO.
 */
public class PeriodicalDAOImpl implements PeriodicalDAO {

    private static final Logger LOGGER = LogManager.getLogger(PeriodicalDAOImpl.class);

    Connection getConnection() {
        return ConnectionDataSource.getInstance().getConnection();
    }

    @Override
    public boolean insertPeriodical(Periodical periodical) throws DbException {

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_PERIODICAL)) {
            setPeriodicalParameters(periodical, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't insert periodical:", e);
            throw new DbException(e);
        }
    }

    @Override
    public boolean updateIssue(Periodical periodical) throws DbException {

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_ISSUE)) {
            preparedStatement.setInt(1, periodical.getIssue());
            preparedStatement.setInt(2, periodical.getId());
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't update issue:", e);
            throw new DbException(e);
        }
    }

    @Override
    public boolean updatePeriodical(Periodical periodical) throws DbException {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_PERIODICAL)) {
            preparedStatement.setString(1, periodical.getEngTitle());
            preparedStatement.setString(2, periodical.getUkrTitle());
            preparedStatement.setString(3, periodical.getEngDescription());
            preparedStatement.setString(4, periodical.getUkrDescription());
            preparedStatement.setBigDecimal(5, periodical.getPrice());
            preparedStatement.setInt(6, periodical.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't update periodical:", e);
            throw new DbException(e);
        }
    }

    @Override
    public boolean deletePeriodical(int id) throws DbException {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_PERIODICAL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't delete periodical:", e);
            throw new DbException(e);
        }
    }

    @Override
    public Periodical getPeriodicalById(int id) throws DbException {
        Periodical periodical = null;

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_PERIODICAL_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                periodical = getPeriodicalExecute(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find periodical by id:", e);
            throw new DbException(e);
        }
        return periodical;
    }

    @Override
    public Periodical getPeriodicalByUkrTitle(String titleUkr) throws DbException {
        Periodical periodical = null;

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_PERIODICAL_BY_UKR_TITLE)) {
            preparedStatement.setString(1, titleUkr);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                periodical = getPeriodicalExecute(rs);

            }
        } catch (SQLException e) {
            LOGGER.error("Can't find periodical by ukr title :", e);
            throw new DbException(e);
        }
        return periodical;
    }

    @Override
    public Periodical getPeriodicalByEngTitle(String titleEng) throws DbException {
        Periodical periodical = null;
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_PERIODICAL_BY_ENG_TITLE)) {
            preparedStatement.setString(1, titleEng);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                periodical = getPeriodicalExecute(rs);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find periodical by english title:", e);
            throw new DbException(e);
        }
        return periodical;
    }

    @Override
    public List<Periodical> getPeriodicalsByTopic(Topic topic) throws DbException {
        List<Periodical> periodicalsByTopic = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_PERIODICALS_BY_TOPIC)) {
            preparedStatement.setString(1, topic.getEngTopicName());
            getPeriodicalsListExecute(periodicalsByTopic, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find periodicals:", e);
            throw new DbException(e);
        }
        return periodicalsByTopic;
    }

    @Override
    public boolean setTopicForNewPeriodicalTransaction(Periodical periodical, Topic... topics) throws DbException {

        Connection connection = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try {
                insertPeriodical(periodical);
            } catch (DbException e) {
                e.printStackTrace();
            }
            for (Topic t : topics) {
                Topic topic = new TopicDAOImpl().getTopicById(t.getId());
                addTopicsForPeriodical(connection, periodical, topic);
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
            LOGGER.error("Can't set topics for periodical:", e);
            throw new DbException(e);
        } finally {
            close(connection);
        }
    }

    private void addTopicsForPeriodical(Connection con, Periodical periodical, Topic topic) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            Periodical periodical1 = null;
            try {
                periodical1 = getPeriodicalByEngTitle(periodical.getEngTitle());
            } catch (DbException e) {
                e.printStackTrace();
            }
            preparedStatement = con.prepareStatement(ConstantsQuery.SET_TOPIC_FOR_PERIODICAL);
            preparedStatement.setString(1, periodical1.getEngTitle());
            preparedStatement.setString(2, topic.getUkrTopicName());
            preparedStatement.executeUpdate();

        } finally {
            close(preparedStatement);
        }
    }


    @Override
    public List<Periodical> getAllPeriodicalsByEngTitle(int offset) throws DbException {
        List<Periodical> periodicals = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_PERIODICALS_BY_ENG_TITLE)) {
            preparedStatement.setInt(1, offset);
            periodicals = getPeriodicalsListExecute(periodicals, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find all periodicals by english title:", e);
            throw new DbException(e);
        }
        return periodicals;
    }

    @Override
    public List<Periodical> getAllPeriodicalsByUkrTitle(int offset) throws DbException {
        List<Periodical> periodicals = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_PERIODICALS_BY_UKR_TITLE)) {
            preparedStatement.setInt(1, offset);
            periodicals = getPeriodicalsListExecute(periodicals, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find all periodicals by ukrainian title:", e);
            throw new DbException(e);
        }
        return periodicals;
    }

    @Override
    public List<Periodical> getAllPeriodicalsByPrice(int offset) throws DbException {
        List<Periodical> periodicals = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_PERIODICALS_BY_PRICE)) {
            preparedStatement.setInt(1, offset);
            periodicals = getPeriodicalsListExecute(periodicals, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find all periodicals by price:", e);
            throw new DbException(e);
        }
        return periodicals;
    }

    @Override
    public Map<Topic, List<Periodical>> getAllPeriodicalsByTopics(List<Topic> topics) throws DbException {
        Map<Topic, List<Periodical>> periodicalsByTopics = new HashMap<>();

        for (Topic t : topics) {
            List<Periodical> periodicals = getPeriodicalsByTopic(t);
            periodicalsByTopics.put(t, periodicals);
        }
        return periodicalsByTopics;
    }

    @Override
    public List<Periodical> getAllPeriodicalsBySubscribes(List<Subscribe> subscribes) throws DbException {
        List<Periodical> periodicals = new ArrayList<>();

        for (Subscribe s : subscribes) {
            Periodical periodical = getPeriodicalById(s.getPeriodicalID());
            periodicals.add(periodical);
        }
        return periodicals;
    }

    @Override
    public List<Periodical> getAllPeriodicals() throws DbException {
        List<Periodical> periodicals = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_PERIODICALS)) {
            getPeriodicalsListExecute(periodicals, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find all periodicals:", e);
            throw new DbException(e);
        }
        return periodicals;
    }

    private void setPeriodicalParameters(Periodical periodical, PreparedStatement preparedStatement) throws
            SQLException {
        preparedStatement.setString(1, periodical.getEngTitle());
        preparedStatement.setString(2, periodical.getUkrTitle());
        preparedStatement.setString(3, periodical.getEngDescription());
        preparedStatement.setString(4, periodical.getUkrDescription());
        preparedStatement.setInt(5, periodical.getIssue());
        preparedStatement.setBigDecimal(6, periodical.getPrice());
    }

    private Periodical getPeriodicalExecute(ResultSet rs) throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setId(rs.getInt("id"));
        periodical.setEngTitle(rs.getString("title_eng"));
        periodical.setUkrTitle(rs.getString("title_ukr"));
        periodical.setEngDescription(rs.getString("description_eng"));
        periodical.setUkrDescription(rs.getString("description_ukr"));
        periodical.setIssue(rs.getInt("issue"));
        periodical.setPrice(rs.getBigDecimal("price"));
        return periodical;
    }

    private List<Periodical> getPeriodicalsListExecute(List<Periodical> periodicals, PreparedStatement
            preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Periodical periodical = getPeriodicalExecute(rs);
            periodicals.add(periodical);
        }
        return periodicals;
    }

    private void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void close(AutoCloseable con) {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

