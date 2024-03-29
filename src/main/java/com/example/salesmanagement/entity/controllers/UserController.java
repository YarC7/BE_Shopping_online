package com.example.salesmanagement.entity.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.example.salesmanagement.entity.models.Address;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('administrator:read')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('administrator:read')")
    @GetMapping("/{id}/show")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('customer:read')")
    @GetMapping("/user-detail")
    public ResponseEntity<User> getUserDetailsBySelf(Authentication authentication){
        String userEmail = authentication.getName();
        User user = userService.getUserByEmail(userEmail);
        if (user == null) {
            // Handle the case where the user is not found
            return ResponseEntity.notFound().build();
        }
        // Return the user's information as a response
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('administrator:create')")
    @PostMapping("/store")
    public  ResponseEntity<?> store(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('administrator:update')")
    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

    // @PreAuthorize("hasAuthority('customer:update')")
    // @PutMapping("/self-update")
    // public ResponseEntity<?> updateUserByThemSelves(HttpServletRequest request,
    //         @RequestBody User user) {
    //     return userService.updateUserByThemSelves(user);
    // }

    @PreAuthorize("hasAuthority('customer:update')")
    @PutMapping("/update-by-self")
    public ResponseEntity<?> updateUserBySelf(HttpServletRequest request,
            @RequestBody User user,Authentication authentication) {
        return userService.updateUserBySelf(authentication,user);
    }

    @PreAuthorize("hasAuthority('customer:update')")
    @PostMapping("/add-address")
    public ResponseEntity<?> addAddress(HttpServletRequest request,Authentication authentication,@RequestBody Address address){
        return userService.addUserShippingAddress(authentication, address);
    }




    @PreAuthorize("hasAuthority('administrator:delete')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Value("${project.image}")
    private String path; 
    
    @PostMapping("/{id}/uploadimage")
    @PreAuthorize("hasAuthority('customer:update')")
    public ResponseEntity<String> uploadUserImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = userService.uploadUserImage(path, id, file);
            String fileUrl = "http://localhost:1707/api/images/users/" + fileName; // replace with your domain name
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload User image");
        }
    }

    @PutMapping("/{id}/updateimage")
    @PreAuthorize("hasAuthority('customer:update')")
    public ResponseEntity<String> updateUserImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = userService.updateUserImage("C:\\Users\\Cankkun\\BE_Shopping_online\\images", id, file);
            String fileUrl = "http://localhost:1707/api/images/users/" + fileName; // modify this URL with your actual domain and image folder path
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update User image");
        }
    }

    @DeleteMapping("/{id}/deleteimage")
    @PreAuthorize("hasAuthority('customer:update')")
    public ResponseEntity<String> deleteUserImage(@PathVariable String id) {
        boolean deleted = userService.deleteUserImage("C:\\Users\\Cankkun\\BE_Shopping_online\\images", id);
        if (deleted) {
            return ResponseEntity.ok("User image deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete User image");
        }
    }

    @GetMapping("/{id}/showimage")
    @PreAuthorize("hasAuthority('customer:update')")
    public ResponseEntity<byte[]> getUserImage(@PathVariable String id) {
        try {
            byte[] imageBytes = userService.getUserImage("C:\\Users\\Cankkun\\BE_Shopping_online\\images", id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // assuming all user images are PNG files
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
