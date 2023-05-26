package com.micro.demo.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.micro.demo.Exception.UnderAgeException;
import com.micro.demo.Model.User;
import com.micro.demo.Services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;


/**
 * Classe controller pour le user
 */

@Api("Api pour les opérations CRUD sur les users")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "Récupère la liste de tous les users")
	@GetMapping("/users")
	public MappingJacksonValue getAll() {
		Iterable<User> users = userService.getAll();
		SimpleBeanPropertyFilter emailFilter = SimpleBeanPropertyFilter.serializeAllExcept("email");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filterByEmail", emailFilter);
		MappingJacksonValue usersFilter = new MappingJacksonValue(users);
		usersFilter.setFilters(filterProvider);
		return usersFilter;
	}

	@ApiOperation(value = "Récupère un user via son numero de permis")
	@GetMapping("/users/{id}")
	public User getOneUser(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@ApiOperation(value = "Permet d'ajouter un user")
	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody User user) throws UnderAgeException {
		LocalDate useBirthDate = user.getBirthDate();
		LocalDate currentDate = LocalDate.now();
		int UserAge = Period.between(useBirthDate, currentDate).getYears();
		if (UserAge > 25) {
			return userService.addUser(user);
		} else {
			throw new UnderAgeException("User is too young, minimal age is 25");
		}
	}

	@ApiOperation(value = "Permet de modifier un user via son numero de permis")
	@PutMapping("/users")
	ResponseEntity<String> replaceUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@ApiOperation(value = "Permet de supprimer un user via son numero de permis")
	@DeleteMapping("/user/{id}")
	ResponseEntity<String> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	@ExceptionHandler(UnderAgeException.class)
	public ResponseEntity<Object> handleWrongDateException(UnderAgeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("{\"AgeInvalid\":\"" + ex.getMessage() + "\"}");
	}

}
