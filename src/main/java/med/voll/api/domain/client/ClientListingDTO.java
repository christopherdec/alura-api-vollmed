package med.voll.api.domain.client;

public record ClientListingDTO(Long id, String name, String email, String phone, String cpf) {

    public ClientListingDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.getCpf());
    }
}
