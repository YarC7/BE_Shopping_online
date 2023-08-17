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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.salesmanagement.entity.repositories.AddressRepository;
import com.example.salesmanagement.entity.repositories.UserRepository;
import com.example.salesmanagement.entity.utilities.Time;
import lombok.RequiredArgsConstructor;

import com.example.salesmanagement.entity.models.Address;
import com.example.salesmanagement.entity.models.User;


@Service
@RequiredArgsConstructor
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;
    
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUserById(String id) {
        Optional<User> one_User = userRepository.findById(id);
        return one_User.orElse(null);
    }

    public User getUserDetailsBySelf(Authentication authentication){
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        return one_User.orElse(null);
    }
    public User getUserByEmail(String userEmail) {
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
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
        existingUser.setUserFirstName(user.getUserFirstName());
        existingUser.setUserLastName(user.getUserLastName());
        existingUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        existingUser.setUserPhone(user.getUserPhone());
        existingUser.setUserAddress(user.getUserAddress());
        existingUser.setUserNationality(user.getUserNationality());
        existingUser.setUserGender(user.getUserGender());
        existingUser.setUpdatedAt(Time.getCurrentDate());
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    // public ResponseEntity<User> updateUserByThemSelves(User user) {
    //     Optional<User> optionalUser = userRepository.findById(user.getUserId());
    //     if (!optionalUser.isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     User existingUser = optionalUser.get();
    //     existingUser.setUserFirstName(user.getUserFirstName());
    //     existingUser.setUserLastName(user.getUserLastName());
    //     existingUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
    //     existingUser.setUserPhone(user.getUserPhone());
    //     existingUser.setUserAddress(user.getUserAddress());
    //     existingUser.setUserNationality(user.getUserNationality());
    //     existingUser.setUserGender(user.getUserGender());
    //  
    //     existingUser.setUpdatedAt(Time.getCurrentDate());
    //     User updatedUser = userRepository.save(existingUser);
    //     return ResponseEntity.ok(updatedUser);
    // }

    public ResponseEntity<User> updateUserBySelf(Authentication authentication, User user) {
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        if (!one_User.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = one_User.get();
        existingUser.setUserFirstName(user.getUserFirstName());
        existingUser.setUserLastName(user.getUserLastName());
        existingUser.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        existingUser.setUserPhone(user.getUserPhone());
        existingUser.setUserAddress(user.getUserAddress());
        existingUser.setUserNationality(user.getUserNationality());
        existingUser.setUserGender(user.getUserGender());
        existingUser.setUpdatedAt(Time.getCurrentDate());
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }


    //Address
    public ResponseEntity<Address> addUserShippingAddress(Authentication authentication,Address address){
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        if (!one_User.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = one_User.get();
        List<Address> listAddress = new ArrayList<>();
        Address existingAddress = new Address();
        existingAddress.setTypeAddress(address.getTypeAddress());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setWard(address.getWard());
        existingAddress.setDistrict(address.getDistrict());
        existingAddress.setProvince(address.getProvince());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setNote(address.getNote());
        existingAddress.setUser(existingUser);
        addressRepository.save(existingAddress);
        listAddress.add(existingAddress);
        existingUser.setUserShippingAddress(listAddress);
        return ResponseEntity.ok(existingAddress);
    }

    public ResponseEntity<?> updateAddress(Authentication authentication, Address address , Integer index){
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        if (!one_User.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User existingUser = one_User.get();
        Address existingAddress = existingUser.getUserShippingAddress().get(index);
        existingAddress.setTypeAddress(address.getTypeAddress());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setWard(address.getWard());
        existingAddress.setDistrict(address.getDistrict());
        existingAddress.setProvince(address.getProvince());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setNote(address.getNote());
        addressRepository.save(existingAddress);
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }


    public List<Address> showAddress(Authentication authentication){
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        
        User existingUser = one_User.get();
        List<Address> existingAddress = new ArrayList<>();
        existingUser.getUserShippingAddress().forEach(existingAddress::add);
        return existingAddress;
    }

    public void deleteAddress(Authentication authentication, Integer index) {
        String userEmail = authentication.getName();
        Optional<User> one_User = userRepository.findByUserEmail(userEmail);
        User existingUser = one_User.get();
        Address existingAddress =  existingUser.getUserShippingAddress().get(index);
        existingUser.getUserShippingAddress().remove(0);
        String id = existingAddress.getAddressId().toString().trim();
        addressRepository.deleteById(id);
    }


    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public String uploadUserImage(String path,String id, MultipartFile file) throws IOException {

        // construct file path using User ID as file name
        String originalFilename = file.getOriginalFilename();
        String fileName = null;
        if (originalFilename != null) {
            fileName = id + originalFilename.substring(originalFilename.lastIndexOf("."));
        }
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
        String originalFilename = file.getOriginalFilename();
        String fileName = null;
        if (originalFilename != null) {
            fileName = id + originalFilename.substring(originalFilename.lastIndexOf("."));
        }
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
        if (fileName != id + ".png") {
            fileName =id + ".jpg"; // if not .png here , change to jpg
        }
        String filePath = path + File.separator + fileName;
        File imageFile = new File(filePath);
        return Files.readAllBytes(imageFile.toPath());
    }

}
