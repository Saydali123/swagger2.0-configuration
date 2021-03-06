package aa;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "User Rest Controller", description = "REST API for User")
@RequestMapping("/api")
@RestController
public class Swagger2RestController {

    List<User> users = new ArrayList<User>();

    {
        users.add(new User(1, "TechGeekNext-User1", "ADMIN", "user1@test.com"));
        users.add(new User(2, "TechGeekNext-User2", "SUPERVISOR", "user2@test.com"));
        users.add(new User(3, "TechGeekNext-User3", "USER", "user3@test.com"));
        users.add(new User(4, "TechGeekNext-User4", "USER", "user4@test.com"));
    }

    @ApiOperation(value = "Get Users ", response = Iterable.class, tags = "getUsers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"),
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!")})

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return users;
    }

    @ApiOperation(value = "Get User by User Id ", response = User.class, tags = "getUserById")
    @RequestMapping(value = "/get-one/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") int id) {
        return users.stream().filter(x -> x.getId() == (id)).toList().get(0);
    }

    @ApiOperation(value = "Get User by role ", response = User.class, tags = "getUserByRole")
    @RequestMapping(value = "/getUser/role/{role}", method = RequestMethod.GET)
    public List<User> getUserByRole(@PathVariable(value = "role") String role) {
        return users.stream().filter(x -> x.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Create user", response = User.class, tags = "create_user")
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

}
