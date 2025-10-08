package com.example.biblioteca.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.service.UserService;
import com.example.biblioteca.web.dtos.requestDTO.CreateUserRequestDTO;
import com.example.biblioteca.web.dtos.responseDTO.UserResponseDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/v1/user")
public class UserController {

    
    
    private final UserService userService;
    //private final SessionRegistry sessionRegistry;

    private UserController(UserService userService
    //, SessionRegistry sessionRegistry
    ){
        this.userService = userService;
        //this.sessionRegistry = sessionRegistry;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody CreateUserRequestDTO user) {
        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }
    

    // @GetMapping("/session")
    // public ResponseEntity<?> getDetailsSession() {
    //     String sessionId = "";
    //     User userObject = null;

    //     List<Object> sessions = sessionRegistry.getAllPrincipals();

    //     for (Object session : sessions) {
    //         if(session instanceof User){
    //             userObject = (User) session;
    //         }

    //         List<SessionInformation> userSessions = sessionRegistry.getAllSessions(session, false);

    //         for(SessionInformation sessionInformation: userSessions){
    //             sessionId = sessionInformation.getSessionId();
    //         }
    //     }

    //     Map<String, Object> response = new HashMap<>();
    //     response.put("response", "hellow world");
    //     response.put("sessionId", sessionId);
    //     response.put("sessionUser", userObject);

    //     return  ResponseEntity.ok(response);
    // }


}
