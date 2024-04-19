package org.thanhngo.retailapp.services;

import org.thanhngo.retailapp.dtos.UserDTO;
import org.thanhngo.retailapp.exception.DataNotFoundException;
import org.thanhngo.retailapp.models.User;

public interface iUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password);
}
