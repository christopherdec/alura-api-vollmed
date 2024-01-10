package med.voll.api.controller;

import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentListingDto;
import med.voll.api.domain.appointment.AppointmentScheduleDto;
import med.voll.api.domain.client.Client;
import med.voll.api.domain.expertise.Expertise;
import med.voll.api.domain.professional.Professional;
import med.voll.api.service.appointment.AppointmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentScheduleDto> appointmentScheduleJson;

    @Autowired
    private JacksonTester<AppointmentListingDto> appointmentListingJson;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return 400 on invalid parameters")
    @WithMockUser
    void schedule1() throws Exception {
        var response = mvc
                .perform(post("/appointment"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 on valid parameters")
    @WithMockUser
    void schedule2() throws Exception {

        var date = LocalDateTime.now().plusHours(1).plusDays(4);
        var expertise = Expertise.CARDIOLOGIA;
        var professional = Professional.builder().id(1L).build();
        var client = Client.builder().id(1L).build();

        var dto = new AppointmentScheduleDto(1L, 1L, date, expertise);
        var content = appointmentScheduleJson.write(dto).getJson();

        when(appointmentService.schedule(dto))
                .thenReturn(new Appointment(1L, professional, client, date, null));

        var response = mvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andReturn()
                .getResponse();

        var expectedDto = new AppointmentListingDto(1L, 1L, 1L, date);
        var expectedJson = appointmentListingJson.write(expectedDto).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}