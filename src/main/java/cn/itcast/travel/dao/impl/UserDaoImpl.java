package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        // 1. 定义sql
        String sql = "SELECT * FROM tab_user WHERE username = ?";
        // 2. 执行sql
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void save(User user) {
        // 1. 定义sql
        String sql = "INSERT INTO tab_user(username,password,name,birthday,sex,telephone,email)" +
                " VALUES(?,?,?,?,?,?,?)";
        // 2. 执行sql
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail());
    }
}
