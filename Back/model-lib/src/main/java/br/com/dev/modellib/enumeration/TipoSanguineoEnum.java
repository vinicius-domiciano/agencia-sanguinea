package br.com.dev.modellib.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum TipoSanguineoEnum {
    A_MAIS(0, "A+"),
    A_MENOS(1, "A-"),
    B_MAIS(2, "B+"),
    B_MENOS(2, "B-"),
    AB_MAIS(2, "AB+"),
    AB_MENOS(2, "AB-"),
    O_MAIS(2, "O+"),
    O_MENOS(2, "O-");

    private static Map<String, TipoSanguineoEnum> FORMAT_MAP = Stream
            .of(TipoSanguineoEnum.values())
            .collect(Collectors.toMap(el -> el.desc, Function.identity()));

    private final int cod;
    private final String desc;

    @JsonCreator // This is the factory method and must be static
    public static TipoSanguineoEnum fromString(String string) {
        return Optional
                .ofNullable(FORMAT_MAP.get(string))
                .orElseThrow(() -> new IllegalArgumentException("Valor inválido: " + string));
    }
}
