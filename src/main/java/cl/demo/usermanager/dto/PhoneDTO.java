package cl.demo.usermanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PhoneDTO {
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "\\d+", message = "El número de teléfono debe ser numérico")
    @Size(max = 12, message = "El número de teléfono no debe exceder los 30 caracteres")
    private String number;

    @NotBlank(message = "El código de ciudad no puede estar vacío")
    @Pattern(regexp = "\\d*", message = "El código de ciudad debe ser numérico")
    @Size(max = 3, message = "El código de ciudad no debe exceder los 3 caracteres")
    private String cityCode;

    @NotBlank(message = "El código de país no puede estar vacío")
    @Pattern(regexp = "\\d*", message = "El código de país debe ser numérico")
    @Size(max = 2, message = "El código de país no debe exceder los 2 caracteres")
    private String countryCode;

}