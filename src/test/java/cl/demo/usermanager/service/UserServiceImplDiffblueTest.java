package cl.demo.usermanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.demo.usermanager.dto.UserRequestDTO;
import cl.demo.usermanager.dto.UserResponseDTO;
import cl.demo.usermanager.model.User;
import cl.demo.usermanager.repository.UserRepository;
import cl.demo.usermanager.security.JwtUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserServiceImplTest {
    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserRequestDTO)}
     */
    @Test
    void testCreateUser() {
        when(userRepository.existsByEmail(Mockito.<String>any())).thenReturn(true);

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("jane.doe@example.org");
        userRequestDTO.setName("Name");
        userRequestDTO.setPassword("iloveyou");
        userRequestDTO.setPhones(new ArrayList<>());

        assertThrows(RuntimeException.class, () -> userServiceImpl.createUser(userRequestDTO));
        verify(userRepository).existsByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserRequestDTO)}
     */
    @Test
    void testCreateUser2() {
        when(userRepository.existsByEmail(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("jane.doe@example.org");
        userRequestDTO.setName("Name");
        userRequestDTO.setPassword("iloveyou");
        userRequestDTO.setPhones(new ArrayList<>());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userServiceImpl.createUser(userRequestDTO));
        verify(userRepository).existsByEmail(eq("jane.doe@example.org"));
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserRequestDTO)}
     */
    @Test
    void testCreateUser3() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(jwtUtil.generateToken(Mockito.<String>any())).thenReturn("ABC123");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setActive(true);
        userResponseDTO.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        userResponseDTO.setId(UUID.randomUUID());
        userResponseDTO.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        userResponseDTO.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        userResponseDTO.setToken("ABC123");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponseDTO>>any())).thenReturn(userResponseDTO);

        User user = new User();
        user.setActive(true);
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setId(UUID.randomUUID());
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        when(userRepository.existsByEmail(Mockito.<String>any())).thenReturn(false);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("jane.doe@example.org");
        userRequestDTO.setName("Name");
        userRequestDTO.setPassword("iloveyou");
        userRequestDTO.setPhones(new ArrayList<>());

        // Act
        UserResponseDTO actualCreateUserResult = userServiceImpl.createUser(userRequestDTO);

        // Assert
        verify(userRepository).existsByEmail(eq("jane.doe@example.org"));
        verify(jwtUtil).generateToken(eq("jane.doe@example.org"));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(userRepository).save(isA(User.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertEquals("ABC123", actualCreateUserResult.getToken());
        assertSame(userResponseDTO, actualCreateUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserRequestDTO)}
     */
    @Test
    void testCreateUser4() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(jwtUtil.generateToken(Mockito.<String>any())).thenReturn("ABC123");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<UserResponseDTO>>any()))
                .thenThrow(new RuntimeException("foo"));

        User user = new User();
        user.setActive(true);
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setId(UUID.randomUUID());
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setModified(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        when(userRepository.existsByEmail(Mockito.<String>any())).thenReturn(false);
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("jane.doe@example.org");
        userRequestDTO.setName("Name");
        userRequestDTO.setPassword("iloveyou");
        userRequestDTO.setPhones(new ArrayList<>());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> userServiceImpl.createUser(userRequestDTO));
        verify(userRepository).existsByEmail(eq("jane.doe@example.org"));
        verify(jwtUtil).generateToken(eq("jane.doe@example.org"));
        verify(modelMapper).map(isA(Object.class), isA(Class.class));
        verify(userRepository).save(isA(User.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
    }
}
