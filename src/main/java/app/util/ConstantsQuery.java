package app.util;

/**
 * The class contains database query constants.
 */
public class ConstantsQuery {

    /**
     * The constructor is private.
     */
    private ConstantsQuery() {
    }

    public static final String FIND_ALL_USERS = "SELECT user.id, login, `password`, email, facebook, `account`,  `status`, role.name r, user_language FROM user INNER JOIN role ON user.role_id = role.id ORDER BY id";
    public static final String INSERT_USER = "INSERT INTO user (id, login, password, email, facebook, account, status, role_id, user_language) VALUES (DEFAULT,?,?,?,?,?,?,(SELECT id FROM role WHERE name = ?),?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static final String UPDATE_USER = "UPDATE user SET login=?, password=?, email=?, facebook=?, account=?, status=?, role_id = (SELECT id FROM role WHERE name = ?), user_language=? WHERE id=?";
    public static final String GET_USER_BY_ID = "SELECT user.id, login, `password`, email, facebook, `account`,  `status`, role.name r, user_language FROM user INNER JOIN role ON user.role_id = role.id WHERE user.id = ?";
    public static final String GET_TOPIC_BY_ID = "SELECT * FROM topic WHERE id = ?";
    public static final String GET_TOPIC_BY_ENG_NAME = "SELECT * FROM topic WHERE name_eng = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT user.id, login, `password`, email, facebook, `account`,  `status`, role.name r, user_language FROM user INNER JOIN role ON user.role_id = role.id WHERE login = ?";

    public static final String GET_USER_BY_EMAIL = "SELECT user.id, login, `password`, email, facebook, `account`,  `status`, role.name r, user_language FROM user INNER JOIN role ON user.role_id = role.id WHERE email = ?";
    public static final String INSERT_PERIODICAL = "INSERT INTO periodical (id, title_eng, title_ukr, description_eng, description_ukr, issue, price) VALUES (DEFAULT,?,?,?,?,?,?)";
    public static final String DELETE_PERIODICAL = "DELETE FROM periodical WHERE id = ?";
    public static final String UPDATE_PERIODICAL = "UPDATE periodical SET title_eng=?, title_ukr=?, description_eng=?, description_ukr=?, price=? WHERE id=?";
    public static final String FIND_PERIODICAL_BY_ID = "SELECT * FROM periodical WHERE id = ?";
    public static final String GET_PERIODICALS_BY_TOPIC = "SELECT * FROM periodical WHERE id IN (SELECT periodical_id FROM periodical_has_topic WHERE topic_id IN (SELECT id FROM topic WHERE name_eng = (?))) ORDER BY title_eng ASC";
    public static final String FIND_ALL_PERIODICALS_BY_PRICE = "SELECT * FROM periodical ORDER BY price LIMIT 10 OFFSET ?";
    public static final String FIND_ALL_PERIODICALS_BY_UKR_TITLE = "SELECT * FROM periodical ORDER BY title_ukr LIMIT 10 OFFSET ?";
    public static final String FIND_ALL_PERIODICALS_BY_ENG_TITLE = "SELECT * FROM periodical ORDER BY title_eng LIMIT 10 OFFSET ?";
    public static final String FIND_PERIODICAL_BY_ENG_TITLE = "SELECT * FROM periodical WHERE title_eng = ?";
    public static final String FIND_PERIODICAL_BY_UKR_TITLE = "SELECT * FROM periodical WHERE title_ukr = ?";
    public static final String SET_TOPIC_FOR_PERIODICAL = "INSERT INTO periodical_has_topic (periodical_id, topic_id) VALUES ((SELECT id FROM periodical WHERE title_eng = ?), (SELECT id FROM topic WHERE name_ukr = ?))";
    public static final String FIND_ALL_TOPICS = "SELECT * FROM topic";
    public static final String INSERT_TOPIC = "INSERT INTO topic (id, name_eng, name_ukr) VALUES (DEFAULT,?,?)";
    public static final String DELETE_TOPIC = "DELETE FROM topic WHERE id = ?";
    public static final String UPDATE_TOPIC = "UPDATE topic SET name_eng=?, name_ukr=? WHERE id=?";
    public static final String INSERT_SUBSCRIBE = "INSERT INTO subscribe (id, periodical_id, user_id, final_issue) VALUES (DEFAULT,?,?,?)";
    public static final String DELETE_SUBSCRIBE = "DELETE FROM subscribe WHERE id = ?";
    public static final String UPDATE_SUBSCRIBE = "UPDATE subscribe SET final_issue=? WHERE id=?";
    public static final String FIND_ALL_SUBSCRIBES_BY_USER = "SELECT * FROM subscribe WHERE user_id = ?";
    public static final String FIND_ALL_SUBSCRIBES_BY_PERIODICAL = "SELECT * FROM subscribe WHERE periodical_id = ?";
    public static final String FIND_SUBSCRIBE_BY_ID = "SELECT * FROM subscribe WHERE id = ?";
    public static final String UPDATE_ISSUE = "UPDATE periodical SET issue = ? WHERE id = ?";
    public static final String FIND_ALL_PERIODICALS = "SELECT * FROM periodical ORDER BY id";

}
