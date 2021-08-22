package edu.eci.microservices.restfulapi.service;

import edu.eci.microservices.restfulapi.data.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    private HashMap<String, User> users = new HashMap<>();
    private AtomicInteger counterToId = new AtomicInteger(1);

    @Override
    public User create(User user) {
        String newId = generateNewUserId().toString();
        user.setId(newId);
        users.put(newId, user);
        return user;
    }

    private Integer generateNewUserId() {
        return counterToId.getAndIncrement();
    }

    @Override
    public User findById(String id) {
        return isValidUser(id) ? users.get(id) : null;
    }

    public boolean isValidUser(String userId) {
        return users.containsKey(userId);
    }

    @Override
    public List<User> all() {
        List<User> allUsers = new ArrayList<User>();
        users.forEach((k, v) -> allUsers.add(v));
        return allUsers;
    }

    @Override
    public boolean deleteById(String id) {
        users.remove(id);
        return !isValidUser(id);
    }

    @Override
    public User update(User user, String userId) {
        user.setId(userId);
        Date date = Calendar.getInstance().getTime();
        user.setCreatedAt(date);
        return users.put(userId, user);
    }

}
