package cl.demo.usermanager.repository;

import cl.demo.usermanager.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}