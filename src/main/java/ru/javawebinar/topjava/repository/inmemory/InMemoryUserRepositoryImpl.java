package ru.javawebinar.topjava.repository.inmemory;

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

<<<<<<< HEAD
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl extends InMemoryBaseRepositoryImpl<User> implements UserRepository {

    static final int USER_ID = 1;
    static final int ADMIN_ID = 2;

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
=======
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return Collections.emptyList();
>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
    }

    @Override
    public User getByEmail(String email) {
<<<<<<< HEAD
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
=======
        log.info("getByEmail {}", email);
        return null;
    }
}
>>>>>>> db3a0e1282e7f7efd08db6b1d428db72b257aebd
