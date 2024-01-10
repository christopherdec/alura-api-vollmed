package med.voll.api.controller;

import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.expertise.Expertise;
import med.voll.api.domain.professional.Professional;
import med.voll.api.domain.professional.ProfessionalDTO;
import med.voll.api.domain.professional.ProfessionalFormDTO;
import med.voll.api.repository.ProfessionalRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@WithMockUser
class ProfessionalControllerTest {

    @Autowired
    private JacksonTester<ProfessionalFormDTO> professionalFormJson;

    @Autowired
    private JacksonTester<ProfessionalDTO> professionalJson;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProfessionalRepository professionalRepository;

    @Test
    @DisplayName("Should return 400 on bad parameters")
    void register1() throws Exception {
        var response = mvc
                .perform(
                    post("/professional"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 on valid parameters")
    void register2() throws Exception {

        var dto = new ProfessionalFormDTO(
                "Medico", "medico@voll.med", "4899999999",
                "123456", Expertise.CARDIOLOGIA,
                // todo: criar classe de util para testes com o método fakeAddress
                new AddressDTO("Rua teste", "Teste", "88080000", "Floripa", "SC",
                        "123", null));

        var content = professionalFormJson.write(dto).getJson();

        var professional = new Professional(dto);

        when(professionalRepository.save(professional))
                .thenReturn(professional);

        var response = mvc
                .perform(
                        post("/professional")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andReturn()
                .getResponse();

        var expectedDto = new ProfessionalDTO(professional);
        var expectedJson = professionalJson.write(expectedDto).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}