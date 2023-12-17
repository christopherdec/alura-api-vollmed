package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.ClientDTO;
import med.voll.api.dto.ClientFormDTO;
import med.voll.api.dto.ClientUpdateDTO;
import med.voll.api.model.Client;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid ClientFormDTO body) {
        repository.save(new Client(body));
    }

    @GetMapping
    public Page<ClientDTO> list(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(ClientDTO::new);
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody @Valid ClientUpdateDTO data) {
        var client = repository.getReferenceById(data.id());
        client.merge(data);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        var client = repository.getReferenceById(id);
        client.setActive(false);
    }
}
