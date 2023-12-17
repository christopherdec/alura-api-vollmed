package med.voll.api.dto;

import med.voll.api.model.Expertise;
import med.voll.api.model.Professional;

public record ProfessionalListingDTO(Long id, String name, String email, String crm, Expertise expertise) {

    public ProfessionalListingDTO(Professional professional) {
        this(professional.getId(), professional.getName(), professional.getEmail(), professional.getCrm(), professional.getExpertise());
    }
}
