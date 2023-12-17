package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Address(AddressDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }

    public void merge(AddressDTO dto) {
        if (dto.logradouro() != null)
            this.logradouro = dto.logradouro();
        if (dto.bairro() != null)
            this.bairro = dto.bairro();
        if (dto.cep() != null)
            this.cep = dto.cep();
        if (dto.cidade() != null)
            this.cidade = dto.cidade();
        if (dto.uf() != null)
            this.uf = dto.uf();
        if (dto.numero() != null)
            this.numero = dto.numero();
        if (dto.complemento() != null)
            this.complemento = dto.complemento();
    }
}
