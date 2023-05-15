# Projet MicroService Java Spring Boot

---
## Glossaire des annotations:

---

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

### **`@Repository`**
**Annotation pour indiquer qu'il s'agit d'une classe qui gère des données**  

```java
@Repository
public interface UserRepository extends JpaRepository<Product, Long> {
    User findById(int id);
}
```
