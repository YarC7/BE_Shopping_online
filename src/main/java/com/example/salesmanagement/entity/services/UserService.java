package com.example.salesmanagement.entity.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.salesmanagement.entity.repositories.AddressRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;
import com.example.salesmanagement.entity.models.User;
import com.example.salesmanagement.entity.models.Address;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUserById(String id) {
        Optional<User> one_User = userRepository.findById(id);
        return one_User.orElse(null);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<User> updateUser(String id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
    
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        User existingUser = optionalUser.get();
    
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserName(user.getUserName());
        existingUser.setUserPhone(user.getUserPhone());
        existingUser.setUserAddress(user.getUserAddress());
        existingUser.setUserNationality(user.getUserNationality());
        existingUser.setUserGender(user.getUserGender());

        // existingUser.setUserShippingAddress(user.getUserShippingAddress());
        Address updatedAddress = user.getUserShippingAddress();
    
            // Check if existing address is null and create a new instance if needed
            Address existingAddress = existingUser.getUserShippingAddress();
            if (existingAddress == null) {
                existingAddress = new Address();
                existingUser.setUserShippingAddress(existingAddress);
            }
    
            // Update address details
            existingAddress.setStreetAddress(updatedAddress.getStreetAddress());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setZipCode(updatedAddress.getZipCode());
        // existingUser.setUserPaymentMethod(user.getUserPaymentMethod());
        existingUser.setUpdateAt(Time.getCurrentDate());

        User updatedUser = userRepository.save(existingUser);
    
        return ResponseEntity.ok(updatedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<User> addUserShippingAddress(String id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
    
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = optionalUser.get();
        Address updatedAddress = user.getUserShippingAddress();
    
            // Check if existing address is null and create a new instance if needed
            Address existingAddress = existingUser.getUserShippingAddress();
            if (existingAddress == null) {
                existingAddress = new Address();
                existingUser.setUserShippingAddress(existingAddress);
            }
    
            // Update address details
            existingAddress.setStreetAddress(updatedAddress.getStreetAddress());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setZipCode(updatedAddress.getZipCode());

        User addedUserShippingAddress = userRepository.save(existingUser);
        
        return ResponseEntity.ok(addedUserShippingAddress);
    }

    public String uploadUserImage(String path,String id, MultipartFile file) throws IOException {

        // construct file path using User ID as file name
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = path+ File.separator+fileName;

        // create image folder if it doesn't exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
            folder.setExecutable(true, false);
            folder.setReadable(true, false);
            folder.setWritable(true, false);
        }

        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }
    public String updateUserImage(String path, String id, MultipartFile file) throws IOException {
        // construct file path using User ID as file name
        String fileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filePath = path + File.separator + fileName;
    
        // save image file to folder
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
    
        return fileName;
    }

    public boolean deleteUserImage(String path, String id) {
        // construct file path using User ID as file name
        String fileName = id + ".png";
        String filePath = path + File.separator + fileName;
        
        // delete file if it exists
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }
    public byte[] getUserImage(String path, String id) throws IOException {
        String fileName = id + ".png"; // assuming all User images are PNG files
        String filePath = path + File.separator + fileName;
        File imageFile = new File(filePath);
        return Files.readAllBytes(imageFile.toPath());
    }

}
