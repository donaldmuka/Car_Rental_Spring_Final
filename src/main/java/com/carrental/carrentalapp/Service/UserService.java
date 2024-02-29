package com.carrental.carrentalapp.Service;


import com.carrental.carrentalapp.Model.AppUser;
import com.carrental.carrentalapp.Model.Role;
import com.carrental.carrentalapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
//    Use @Lazy so this Password encoder does not conflict with the encoder in the MockData
//    If you will not use the mocked data you can remove the annotation
    @Autowired
    @Lazy
    private PasswordEncoder encoder;
//    returns the user by id or throws an error if the user is not found in the database
    public AppUser getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User with id "+ id+ " not found "));
    }
//    Saves the user to the database
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }
//    Update the user in the database except the username which is used to authenticate the user
    public AppUser updateUser(AppUser appUser) {
        AppUser realUser=new AppUser();
        try {
           realUser=userRepository.findByUsername(appUser.getUsername());
        }catch(Exception e){
            e.getMessage();
        }
        realUser.setPassword(encoder.encode(appUser.getPassword()));
        realUser.setFirstname(appUser.getFirstname());
        realUser.setLastname(appUser.getLastname());
        realUser.setEmail(appUser.getEmail());
        return userRepository.save(realUser);
    }
//    Returns the user by Email
    public AppUser getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
//    Deletes the user from the database
    public void deleteUserById(Long id){
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            e.getMessage();
        }
    }
//    Use this simple login if you want to remove Spring Security from the app
    public AppUser loginUser(String username, String password) {
        AppUser appUser = userRepository.findUserByEmail(username);
        System.out.println(appUser);
        if (appUser.getEmail().isEmpty()){
            return null;
        }
        else {
            if (appUser.getPassword().equals(password)){
                return appUser;
            }else{
                return new AppUser();
            }
        }
    }
//    Returns the user by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
//    Saves a new user to the database and assigns the USER role to it
    public AppUser saveNewUser(AppUser appUser) {
        Role role = roleService.findByRoleName("ROLE_USER");
        appUser.setRole(Set.of(role));
        appUser.setPassword(encoder.encode(appUser.getPassword()));
    return userRepository.save(appUser);
    }
}
