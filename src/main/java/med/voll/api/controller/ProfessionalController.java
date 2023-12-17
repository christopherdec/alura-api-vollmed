package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.ProfessionalDTO;
import med.voll.api.dto.ProfessionalFormDTO;
import med.voll.api.dto.ProfessionalUpdateDTO;
import med.voll.api.model.Professional;
import med.voll.api.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository repository;

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid ProfessionalFormDTO body) {
        repository.save(new Professional(body));
    }

    @GetMapping
    public Page<ProfessionalDTO> list(@PageableDefault(size = 20, sort = {"name"}) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(ProfessionalDTO::new);
    }

    @Transactional
    @PutMapping
    public void update(@RequestBody @Valid ProfessionalUpdateDTO data) {
        var professional = repository.getReferenceById(data.id());
        professional.merge(data);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
//        repository.deleteById(id);
        var professional = repository.getReferenceById(id);
        professional.setActive(false);
    }

}
