package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.professional.ProfessionalDTO;
import med.voll.api.domain.professional.ProfessionalFormDTO;
import med.voll.api.domain.professional.ProfessionalListingDTO;
import med.voll.api.domain.professional.ProfessionalUpdateDTO;
import med.voll.api.domain.professional.Professional;
import med.voll.api.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("professional")
@SecurityRequirement(name = "bearer-key")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<ProfessionalDTO> register(@RequestBody @Valid ProfessionalFormDTO body, UriComponentsBuilder uriBuilder) {
        var professional = repository.save(new Professional(body));
        var uri = uriBuilder.path("/professional/{id}")
                .buildAndExpand(professional.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new ProfessionalDTO(professional));
    }

    @GetMapping
    public ResponseEntity<Page<ProfessionalListingDTO>> list(@PageableDefault(size = 20, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ProfessionalListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> get(@PathVariable Long id) {
        var professional = repository.getReferenceById(id);
        return ResponseEntity.ok(new ProfessionalDTO(professional));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ProfessionalDTO> update(@RequestBody @Valid ProfessionalUpdateDTO data) {
        var professional = repository.getReferenceById(data.id());
        professional.merge(data);
        return ResponseEntity.ok(new ProfessionalDTO(professional));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        repository.deleteById(id);
        var professional = repository.getReferenceById(id);
        professional.setActive(false);
        return ResponseEntity.noContent().build();
    }

}
