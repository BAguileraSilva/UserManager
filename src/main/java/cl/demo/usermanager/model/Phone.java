package cl.demo.usermanager.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "PHONES")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID")
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
    private String number;

    @Column(name = "CITY_CODE", nullable = false, length = 5)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", nullable = false, length = 5)
    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @ToString.Exclude
    private User user;

}
