package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.ProfessionalFormDTO;
import med.voll.api.model.Professional;
import med.voll.api.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professional")
public class ProfessionalResource {

    @Autowired
    private ProfessionalRepository repository;

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid ProfessionalFormDTO body) {
        repository.save(new Professional(body));
    }

}
