package med.voll.api.dto;

import med.voll.api.model.Address;
import med.voll.api.model.Expertise;
import med.voll.api.model.Professional;

public record ProfessionalDTO(Long id, String name, String email, String crm, String phone, Expertise expertise, Address address) {

public ProfessionalDTO(Professional professional) {
    this(professional.getId(), professional.getName(), professional.getEmail(), professional.getCrm(),
            professional.getPhone(), professional.getExpertise(), professional.getAddress());
}
}
