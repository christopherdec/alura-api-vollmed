package med.voll.api.repository;

import med.voll.api.domain.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findAllByActiveTrue(Pageable pageable);

    @Query("""
        select c.active
        from Client c
        where c.id = :id
        """)
    Optional<Boolean> findActiveById(Long id);
}
