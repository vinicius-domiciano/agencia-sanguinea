package br.com.dev.modellib.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContatoEnum {
    CELULAR(0, "CELULAR"),
    FIXO(0, "TELEFONE");

    private final int cod;
    private final String desc;

}
