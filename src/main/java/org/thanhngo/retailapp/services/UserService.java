package org.thanhngo.retailapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.thanhngo.retailapp.dtos.UserDTO;
import org.thanhngo.retailapp.exception.DataNotFoundException;
import org.thanhngo.retailapp.models.Role;
import org.thanhngo.retailapp.models.User;
import org.thanhngo.retailapp.repositories.RoleRepository;
import org.thanhngo.retailapp.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements iUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User createUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("User already exists");
        }
        //convert UserDTO to User
        User user = User.builder()
                .fullName(userDTO.getFullName())
                .address(userDTO.getAddress())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .birthOfBirth(userDTO.getBirthOfBirth())
                .googleAccountId(userDTO.getGoogleAccountId())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .build();
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(()-> new DataNotFoundException("Role not found"));
        user.setRole(role);
        // if accountId is existed then password is not required
        if(userDTO.getGoogleAccountId() == 0 && userDTO.getFacebookAccountId() ==0 ){
            String password = userDTO.getPassword();
            //String encryptedPassword = passwordEncoder.encode(password);
            //user.setPassword(encryptedPassword);
        }
        return userRepository.save(user);
    }

    @Override
    public String login(String phoneNumber, String password) {
        //Security
        return null;
    }
}
