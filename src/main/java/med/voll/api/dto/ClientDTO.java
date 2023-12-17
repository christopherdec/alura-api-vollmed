package med.voll.api.dto;

import med.voll.api.model.Address;
import med.voll.api.model.Client;

public record ClientDTO(Long id, String name, String email, String phone, String cpf, Address address) {

    public ClientDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getCpf(),
                client.getAddress());
    }
}