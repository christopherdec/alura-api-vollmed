package med.voll.api.repository;

import med.voll.api.domain.professional.Professional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Page<Professional> findAllByActiveTrue(Pageable pageable);
}
