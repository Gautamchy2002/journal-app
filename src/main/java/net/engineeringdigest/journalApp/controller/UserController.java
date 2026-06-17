package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


@Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }


    //postmapping ko mai public controller me daal rha hu taki user koi v create kr ske ispe spring security wali chize na lge
//@PostMapping
//    public void createUser(@RequestBody User user){
//
//        userService.saveEntry(user);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userInDb = userService.findByUserName(userName);

            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword(user.getPassword());
        if(user.getPassword() != null && !user.getPassword().isEmpty()){
            userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
        }
//        userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
//            userService.saveNewUser(userInDb);
        userService.saveUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
userRepository.deleteByUserName(authentication.getName());


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting ="";
if (weatherResponse !=null){
    greeting = "Weather feels like  "+weatherResponse.getMain().getFeelsLike();
}
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }


}
