# Projet MicroService Java Spring Boot

## Glossaire des annotations:

### `@SpringBootApplication`  
**Encapsulation de trois annotations:**  

- **`@Configuration`**  
Permet de définir des configurations via des Beans.  


- **`@EnnableAutoConfiguration`**  
Permet de générer les configurations nécessaires au démarrage.  


- **`@ComponentScan`**  
Indique qu'il faut scanner les classes de ce package afin de trouver des Beans de configuration.

```java
@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```
---

### `@RestController`  
**Combinaison de deux annotations pour traiter les rêquetes et répondre directement en Json**  

- **`@Controller`**  
Qui designe une classe comme contrôleur qui pourra traiter les rêquetes GET, POST...  


- **`@ResponseBody`**  
Annotation ajouté aux méthodes du controller pour retourner la réponse sans passer par une vue.

```java
@RestController
public class ProductController {
    //
}
```
---

### **`@Mapping`**
**Permet pour chaque méthodes GET, POST, PUT, DELETE de ne spécifier que l'URL**

- **`@GetMapping`**
- **`@PostMapping`**
- **`@PutMapping`**
- **`@DeleteMapping`**


- **`@PathVarible`**  
Permet de spécifier le type d'une varible passer dans l'url

```java
@RestController
public class User {
    
@GetMapping("/user/{id}")
public User getUserById(@PathVariable int id){
        return userRepository.findById(id);
        }
}
```
---

### **`@ApiIgnore`**
**Dans un controller sous une methode `@...Mapping` permet de filtrer le endpoint pour ne pas l'afficher dans la doc de Swagger**  
```java
    @PostMapping("/users")
    @ApiIgnore
```
---
### **`@RequestBody`**
**Convertie le body d'une rêquete en objet java**  
Dans l'expemple le body de la rêquete contient 
```
    // Body de la rêquete POST sur l'url /users
{
        "license_number": 125469875423,
        "first_name": "plouf",
        "last_name": "plouf",
        "license_date": "1970-01-01",
        "email": "plouf@gmail.com"
    }
```

```java
@RestController
public class UserController {
    
    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userDao.save(user);
    }
}
```
### **`@JsonIgnore`** **&&** **`@JsonIgnoreProperties`**
**Ces deux annotations servent dans le model pour spécifier des attributs à masquer lors des réponses de l'api en Json**

```java
public class User {
    @JsonIgnore
    private String password;

    @JsonIgnoreProperties(value = {"password", "creditCartNumber"})
    private Long creditCartNumber;
}

```

### **`@JsonFilter`**
**Annotation pour pouvoir filtrer dynamiquement des champs en fonction des urls**

Ici on définit un filtre sur le champ email pour la methode get sur l'url /users
```java
public class UserController {
    @GetMapping("/users")
    public MappingJacksonValue getAll() {
        //Récupère les users grace au repository.
        List<User> users = userDao.findAll();

        // On definit ici les règles du filtrage serializeAllExcept qui exclut uniquement les propriétés que nous souhaitons ignorer.
        SimpleBeanPropertyFilter emailFilter = SimpleBeanPropertyFilter.serializeAllExcept("email");

        //SimpleFilterProvider permet de déclarer que les règles de filtrage peuvent s'appliquer à tous les Beans qui sont annotés avec filterByEmail.
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("filterByEmail", emailFilter);

        //MappingJacksonValue. Cela permet de donner accès aux méthodes qui nous intéressent, comme setFilters qui applique les filtres que nous avons établis à la liste de User
        MappingJacksonValue usersFilter = new MappingJacksonValue(users);

        usersFilter.setFilters(filterProvider);
        
        return usersFilter;
    }
}
```

Annotation `@JsonFilter` ce place dans le model que l'on souhaite filtrer

```java
@JsonFilter('filterByEmail')
public class User {
    private String email;
}
```

### **`@Repository`**
**Annotation pour indiquer qu'il s'agit d'une classe qui gère des données**  

```java
@Repository
public interface UserRepository extends JpaRepository<Product, Long> {
    User findById(int id);
}
```
---

### **`@EnableSwagger2`**
**Annotation à placer dans la classe main en dessous de `@SpringBootApplication` pour pouvoir utiliser swagger et générer une doc api.**
Il faut aussi ajouter: `spring.mvc.pathmatch.matching-strategy=ant_path_matcher` dans application properties.  
On pourra ensuite placer `@EnableSwagger2` lorsque l'on veut utiliser les méthodes de swagger comme dans un fichier de config par exemple.
---