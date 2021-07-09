package br.com.dev.clinica.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {
    private Long id;
    private Date dataRegistro;
    private Date dataAtualizacao;
}
