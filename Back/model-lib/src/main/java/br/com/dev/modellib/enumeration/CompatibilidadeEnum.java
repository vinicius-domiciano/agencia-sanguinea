package br.com.dev.modellib.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CompatibilidadeEnum {
    DOADOR(0, "DOADOR"),
    RECEPTOR(0, "RECEPTOR");

    private final int cod;
    private final String desc;
}
