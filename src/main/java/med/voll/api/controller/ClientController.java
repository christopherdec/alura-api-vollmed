package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.ClientDTO;
import med.voll.api.dto.ClientFormDTO;
import med.voll.api.dto.ClientListingDTO;
import med.voll.api.dto.ClientUpdateDTO;
import med.voll.api.model.Client;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<ClientDTO> register(
            @RequestBody @Valid ClientFormDTO body,
            UriComponentsBuilder uriBuilder
    ) {
        var client = repository.save(new Client(body));
        var uri = uriBuilder.path("/client/{id}")
                .buildAndExpand(client.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(new ClientDTO(client));
    }

    @GetMapping
    public ResponseEntity<Page<ClientListingDTO>> list(@PageableDefault(sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ClientListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long id) {
        var client = repository.getReferenceById(id);
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ClientDTO> update(@RequestBody @Valid ClientUpdateDTO data) {
        var client = repository.getReferenceById(data.id());
        client.merge(data);
        return ResponseEntity.ok(new ClientDTO(client));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var client = repository.getReferenceById(id);
        client.setActive(false);
        return ResponseEntity.noContent().build();
    }
}
