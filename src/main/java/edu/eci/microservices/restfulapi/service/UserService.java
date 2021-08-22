package edu.eci.microservices.restfulapi.service;

import edu.eci.microservices.restfulapi.data.User;

import java.util.List;

public interface UserService {
    User create(User user );

    User findById( String id );

    List<User> all();

    boolean deleteById(String id );

    User update( User user, String userId );
}
