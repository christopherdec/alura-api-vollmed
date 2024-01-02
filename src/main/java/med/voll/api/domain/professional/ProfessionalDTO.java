package med.voll.api.domain.professional;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.expertise.Expertise;

public record ProfessionalDTO(Long id, String name, String email, String crm, String phone, Expertise expertise, Address address) {

public ProfessionalDTO(Professional professional) {
    this(professional.getId(), professional.getName(), professional.getEmail(), professional.getCrm(),
            professional.getPhone(), professional.getExpertise(), professional.getAddress());
}
}
