package cl.demo.usermanager.config;

import cl.demo.usermanager.dto.PhoneDTO;
import cl.demo.usermanager.dto.UserResponseDTO;
import cl.demo.usermanager.model.Phone;
import cl.demo.usermanager.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<User, UserResponseDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setLastLogin(source.getLastLogin());
                map().setActive(source.isActive());
                map().setCreated(source.getCreated());
                map().setModified(source.getModified());
            }
        });

        // Mapeo personalizado para Phone a PhoneDTO
        modelMapper.addMappings(new PropertyMap<Phone, PhoneDTO>() {
            @Override
            protected void configure() {
                map().setNumber(source.getNumber());
                map().setCityCode(source.getCityCode());
                map().setCountryCode(source.getCountryCode());
            }
        });

        return modelMapper;
    }

    @Bean
    public ModelMapper modelMapperDefault() {
        return new ModelMapper();
    }
}
