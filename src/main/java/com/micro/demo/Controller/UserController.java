package com.micro.demo.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.micro.demo.Model.User;
import com.micro.demo.dao.UserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Classe controller pour le user
 */

@Api("Api pour les opérations CRUD sur les users")
@RestController
public class UserController {

    private final UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @ApiOperation(value = "Récupère la liste de tous les users")
    @GetMapping("/users")
    public MappingJacksonValue getAll() {
        List<User> users = userDao.findAll();
        SimpleBeanPropertyFilter emailFilter = SimpleBeanPropertyFilter.serializeAllExcept("email");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filterByEmail", emailFilter);
        MappingJacksonValue usersFilter = new MappingJacksonValue(users);
        usersFilter.setFilters(filterProvider);
        return usersFilter;
    }

    @ApiOperation(value = "Récupère un user via son numero de permis")
    @GetMapping("/users/{id}")
    public MappingJacksonValue getOneUser(@PathVariable Long id) {
        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.setFailOnUnknownId(false);
        MappingJacksonValue usersFilter = new MappingJacksonValue(userDao.findById(id));
        usersFilter.setFilters(filters);
        return usersFilter;
    }

    @ApiOperation(value = "Permet d'ajouter un user")
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {

        if (this.checkLicense(user.getLicense_number()))
        {
            User userAdded = userDao.save(user);
            if (Objects.isNull(userAdded)) {
                return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userAdded.getLicense_number())
                    .toUri();
            return ResponseEntity.created(location).body("User has been created");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error invalid license number");
    }

    @ApiOperation(value = "Permet de modifier un user via son numero de permis")
    @PutMapping("/users/{id}")
    String replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User user = userDao.findById(id);
        int userIndex = userDao.findAll().indexOf(user);

        if (!Objects.isNull(newUser)) {
            userDao.findAll().set(userIndex, newUser);
            return "user update";
        }
        return "Error please check the sending data";
    }

    @ApiOperation(value = "Permet de supprimer un user via son numero de permis")
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        userDao.delete(userDao.findById(id));
        return "user delete";
    }

    public Boolean checkLicense(Long licenseId)
    {
        String url = "http://localhost:8081/licenses/" + licenseId;
        return restTemplate.getForObject(url, Boolean.class);
    }

    @GetMapping("/li/{id}")
    public Boolean getLi(@PathVariable Long id)
    {
        return this.checkLicense(id);
    }

}
