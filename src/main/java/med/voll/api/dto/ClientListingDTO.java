package med.voll.api.dto;

import med.voll.api.model.Client;

public record ClientListingDTO(Long id, String name, String email, String phone, String cpf) {

    public ClientListingDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getCpf());
    }
}
