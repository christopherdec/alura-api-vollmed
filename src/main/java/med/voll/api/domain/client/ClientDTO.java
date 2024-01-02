package med.voll.api.domain.client;

import med.voll.api.domain.address.Address;

public record ClientDTO(Long id, String name, String email, String phone, String cpf, Address address) {

    public ClientDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getCpf(),
                client.getAddress());
    }
}