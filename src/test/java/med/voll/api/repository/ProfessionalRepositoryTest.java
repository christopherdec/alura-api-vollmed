package med.voll.api.repository;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.client.Client;
import med.voll.api.domain.expertise.Expertise;
import med.voll.api.domain.professional.Professional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProfessionalRepositoryTest {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should return empty when no professional is found")
    void findRandomAvailableByExpertiseAndDate1() {
        var expertise = Expertise.CARDIOLOGIA;
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var professional = professionalRepository.findRandomAvailableByExpertiseAndDate(expertise, nextMondayAt10);
        assertTrue(professional.isEmpty());
    }

    @Test
    @DisplayName("Should return empty when the professional is already occupied")
    void findRandomAvailableByExpertiseAndDate2() {
        var expertise = Expertise.CARDIOLOGIA;
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var professional = createProfessional(expertise);
        var client = createClient();
        createAppointment(professional, client, nextMondayAt10);

        var result = professionalRepository.findRandomAvailableByExpertiseAndDate(expertise, nextMondayAt10);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return the available professional")
    void findRandomAvailableByExpertiseAndDate3() {
        var expertise = Expertise.CARDIOLOGIA;
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var professional = createProfessional(expertise);

        var result = professionalRepository.findRandomAvailableByExpertiseAndDate(expertise, nextMondayAt10);
        assertTrue(result.isPresent());
        assertEquals(professional, result.get());
    }

    private Professional createProfessional(Expertise expertise) {
        var professional = Professional.builder()
                .name("Médico")
                .email("medico@voll.med")
                .crm("123456")
                .active(true)
                .phone("4899999999")
                .expertise(expertise)
                .address(fakeAddress())
                .build();
        return entityManager.persist(professional);
    }

    private Address fakeAddress() {
        return new Address("Rua teste", "Teste", "88080000", "Florianópolis", "SC",
                "123", null);
    }

    private Client createClient() {
        var client = Client.builder()
                .name("Cliente")
                .email("cliente@voll.med")
                .cpf("12345678910")
                .active(true)
                .phone("4899999999")
                .address(fakeAddress())
                .build();
        return entityManager.persist(client);
    }

    private Appointment createAppointment(Professional professional, Client client, LocalDateTime date) {
        var appointment = new Appointment(professional, client, date);
        return entityManager.persist(appointment);
    }
}