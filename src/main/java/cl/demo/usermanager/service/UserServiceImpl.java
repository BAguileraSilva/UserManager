package cl.demo.usermanager.service;


import cl.demo.usermanager.dto.UserRequestDTO;
import cl.demo.usermanager.dto.UserResponseDTO;
import cl.demo.usermanager.model.Phone;
import cl.demo.usermanager.model.User;
import cl.demo.usermanager.repository.PhoneRepository;
import cl.demo.usermanager.repository.UserRepository;
import cl.demo.usermanager.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * Crea un nuevo usuario basado en la información proporcionada en UserRequestDTO.
     * @param userRequestDTO DTO con información del usuario.
     * @return UserResponseDTO con información del usuario creado.
     * @throws RuntimeException si el correo electrónico ya está registrado.
     */
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO)  {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }
        User user = modelMapper.map(userRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());

        user = userRepository.save(user);

        savePhones(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return mapToResponseDTO(user, token);
    }

    private UserResponseDTO mapToResponseDTO(User user, String token) {
        UserResponseDTO responseDTO = modelMapper.map(user, UserResponseDTO.class);
        responseDTO.setToken(token);
        return responseDTO;
    }

    public void savePhones(User user){
        if (user.getPhones() != null && !user.getPhones().isEmpty()) {
            phoneRepository.saveAll(user.getPhones());
        }
    }
}