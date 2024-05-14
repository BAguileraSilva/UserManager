package cl.demo.usermanager.service;

import cl.demo.usermanager.dto.UserRequestDTO;
import cl.demo.usermanager.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
