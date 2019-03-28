package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepositoryImpl implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    private UserExtractor resultSetExtractor = new UserExtractor();

    @Autowired
    public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate,
                                  NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:name, email=:email, password=:password, " +
                        "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id", parameterSource) == 0) {
            return null;
        } else {
            jdbcTemplate.update("DELETE FROM user_roles where user_id=?", user.getId());
        }
        insertRolesBatch(user, new ArrayList<>(user.getRoles()));
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users LEFT JOIN user_roles ON users.id = user_roles.user_id WHERE id=?", resultSetExtractor, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users LEFT JOIN user_roles ON users.id = user_roles.user_id WHERE email=?", resultSetExtractor, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users LEFT JOIN user_roles ON users.id = user_roles.user_id ORDER BY name, email", resultSetExtractor);
    }

//    public List<User> getAllOptional(String name, String email) {
//        StringJoiner where = new StringJoiner(" AND ", " WHERE " , "").setEmptyValue("");
//        if (name != null)
//            where.add("name = :name");
//        if (email != null)
//            where.add("email = :email");
//
//        String sql = "SELECT * FROM users" + where;
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", name);
//        params.put("email", email);
//
//        return jdbcTemplate.query(sql, params, ROW_MAPPER);
//    }

    public class UserExtractor implements ResultSetExtractor<List> {

        @Override
        public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

            Map<Integer, User> map = new LinkedHashMap<>();

            while (rs.next()) {
                User user = ROW_MAPPER.mapRow(rs, rs.getRow());
                user.setRoles(EnumSet.of(Role.valueOf(rs.getString("role"))));

                map.merge(user.getId(), user, (oldUser, newUser) ->
                {
                    oldUser.getRoles().addAll(newUser.getRoles());
                    return oldUser;
                });
            }

            return new ArrayList<>(map.values());
        }
    }


    private void insertRolesBatch(final User user, final List<Role> roles) {

        String sql = "INSERT INTO USER_ROLES (USER_ID, ROLE) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, user.getId());
                ps.setString(2, roles.get(i).toString());
            }

            @Override
            public int getBatchSize() {
                return roles.size();
            }
        });
    }
}
