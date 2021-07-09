package br.com.dev.clinica.models.dto.imc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImcInfoDTO {

    private List<PercentualImcDTO> percentualImcDTO;
    private List<MediaImcDTO> mediaImc;

}
