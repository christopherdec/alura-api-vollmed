package med.voll.api.domain.professional;

import med.voll.api.domain.expertise.Expertise;

public record ProfessionalListingDTO(Long id, String name, String email, String crm, Expertise expertise) {

    public ProfessionalListingDTO(Professional professional) {
        this(professional.getId(), professional.getName(), professional.getEmail(), professional.getCrm(), professional.getExpertise());
    }
}
