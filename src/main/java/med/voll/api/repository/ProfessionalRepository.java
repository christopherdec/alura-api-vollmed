package med.voll.api.repository;

import med.voll.api.domain.expertise.Expertise;
import med.voll.api.domain.professional.Professional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    Page<Professional> findAllByActiveTrue(Pageable pageable);

    @Query("""
        select p from Professional p
            left join Appointment a on 
                a.date = :date and 
                p.id = a.professional.id and 
                a.cancelReason is null
        where p.active = true and p.expertise = :expertise and a.professional.id is null
        order by rand()
        limit 1
        """)
    Optional<Professional> findRandomAvailableByExpertiseAndDate(Expertise expertise, LocalDateTime date);

    @Query("""
        select p.active
        from Professional p
        where p.id = :id
        """)
    Optional<Boolean> findActiveById(Long id);
}
