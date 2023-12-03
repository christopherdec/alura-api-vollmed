package med.voll.api.controller;

import med.voll.api.dto.ClientFormDTO;
import med.voll.api.model.Client;
import med.voll.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public void register(@RequestBody ClientFormDTO body) {
        clientRepository.save(new Client(body));
    }
}
