package app.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DbUtil {

    public static DataSource getDataSource(String url, String user, String password) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}
