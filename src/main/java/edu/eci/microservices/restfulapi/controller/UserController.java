package edu.eci.microservices.restfulapi.controller;

import edu.eci.microservices.restfulapi.data.User;
import edu.eci.microservices.restfulapi.dto.UserDto;
import edu.eci.microservices.restfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto userDto )
    {
        Date date = Calendar.getInstance().getTime();
        User newUser = new User (userDto.getName(),userDto.getEmail(), userDto.getLastName(), date );
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(newUser));
    }

    @GetMapping
    public ResponseEntity<List<User>> all()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById( @PathVariable String id )
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {
        User newUser = new User(userDto.getName(), userDto.getEmail(), userDto.getLastName());
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(newUser, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
    }

}
