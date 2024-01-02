package med.voll.api.domain.client;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity(name = "Client")
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    @Setter
    private boolean active;

    public Client(ClientFormDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.cpf = dto.cpf();
        this.address = new Address(dto.address());
        this.active = true;
    }

    public void merge(ClientUpdateDTO data) {
        if (data.name() != null)
            this.name = data.name();
        if (data.email() != null)
            this.email = data.email();
        if (data.phone() != null)
            this.phone = data.phone();
        if (data.cpf() != null)
            this.cpf = data.cpf();
        if (data.address() != null)
            this.address.merge(data.address());
    }
}
