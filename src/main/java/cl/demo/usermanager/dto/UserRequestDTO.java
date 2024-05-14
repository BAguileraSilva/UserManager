package cl.demo.usermanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 30, message = "El nombre no debe exceder los 30 caracteres")
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de correo no válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{12,}$", message = "La contraseña debe tener al menos 12 caracteres y debe incluir al menos una letra mayúscula, una letra minúscula y un número.")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    private List<PhoneDTO> phones;
}