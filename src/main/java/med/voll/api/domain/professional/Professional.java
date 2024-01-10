package med.voll.api.domain.professional;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.expertise.Expertise;

@Table(name = "professional")
@Entity(name = "Professional")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(value = EnumType.STRING)
    private Expertise expertise;
    @Embedded
    private Address address;
    @Setter
    private boolean active;

    public Professional(ProfessionalFormDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.expertise = dto.expertise();
        this.address = new Address(dto.address());
        this.active = true;
    }

    public void merge(ProfessionalUpdateDTO data) {
        if (data.name() != null)
            this.name = data.name();
        if (data.phone() != null)
            this.phone = data.phone();
        if (data.address() != null)
            this.address.merge(data.address());
    }

}
