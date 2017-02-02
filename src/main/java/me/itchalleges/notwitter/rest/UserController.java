package me.itchalleges.notwitter.rest;

import me.itchalleges.notwitter.model.rest.FollowRequest;
import me.itchalleges.notwitter.model.rest.UserRequest;
import me.itchalleges.notwitter.service.IMessageService;
import me.itchalleges.notwitter.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

/**
 * Date: 11/05/2016 1:07 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);

    @Autowired
    private IMessageService messages;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        try {
            String id = userService.create(userRequest);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> getUserName(@RequestParam("userId") String userId) {
        try {
            String userName = userService.getUserName(userId);
            return ResponseEntity.ok(userName);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        try {
            String id = userService.login(userRequest);
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> logout(@RequestParam("tokenId") String tokenId) {
        try {
            userService.logout(tokenId);
            return ResponseEntity.ok(messages.getMessage("bye.bye"));
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> follow(@RequestBody FollowRequest followRequest) {
        try {
            String message = userService.follow(followRequest);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            String message = messages.getMessage("error.general", e.getMessage());
            LOG.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
}
