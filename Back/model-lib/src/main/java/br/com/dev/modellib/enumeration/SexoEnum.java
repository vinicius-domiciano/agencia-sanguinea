package br.com.dev.modellib.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexoEnum {

    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino");

    private final int cod;
    private final String desc;

}
