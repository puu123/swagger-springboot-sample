package springboot.example.com.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.api.UsersApi;
import io.swagger.model.InlineResponse201;
import io.swagger.model.User;
import lombok.val;
import lombok.var;
import lombok.extern.slf4j.Slf4j;
import springboot.example.com.service.UserService;

@Slf4j
@RestController
public class UsersApiController implements UsersApi {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(value = "/users/list", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        val auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth.getAuthorities().toString());
        val users = this.userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/users/create", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<InlineResponse201> createUser(@Valid User user) {
        var statusCode = HttpStatus.CREATED;
        val result = this.userService.addUser(user);
        if (!result) {
            statusCode = HttpStatus.CONFLICT;
        }
        val res = new InlineResponse201().id(user.getId());
        return new ResponseEntity<>(res, statusCode);
    }

    @Override
    @RequestMapping(value = "/users/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(Long id) {
        var statusCode = HttpStatus.OK;
        val user = this.userService.getUser(id);
        if (user == null) {
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(user, statusCode);
    }

    @Override
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(Long id) {
        this.userService.deleteUser(id);
        return null;
    }

    @Override
    @RequestMapping(value = "/users/{id}", consumes = { "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@Valid User user, Long id) {
        user.setId(id);
        this.userService.updateUser(id, user);
        return null;
    }
}