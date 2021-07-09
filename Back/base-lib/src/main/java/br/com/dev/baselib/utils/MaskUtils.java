package br.com.dev.baselib.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaskUtils {

    private static final String MASK_CELULAR = "\\(([\\d]{2})\\) ([\\d]{5})-([\\d]{4})";
    private static final String MASK_TELEFONE_FIXO = "\\(([\\d]{2})\\) ([\\d]{4})-([\\d]{4})";
    private static final String MASK_CEP = "([\\d]{5})-([\\d]{3})";
    private static final String MASK_DATA = "([\\d]{2})/([\\d]{2})/([\\d]{4})";
    private static final String MASK_CPF = "([\\d]{3})\\.([\\d]{3})\\.([\\d]{3})-(\\d{2})";
    private static final String MASK_RG = "([\\d]{2})\\.([\\d]{3})\\.([\\d]{3})-(\\d)";

    public static List<String> quebrarTelefone(String regex, String valor) {
        var list = new ArrayList<String>();
        var ddd = valor.replaceAll(regex, "$1");
        var tel = valor.replaceAll(regex, "$2$3");

        list.add(ddd);
        list.add(tel);
        return list;
    }

    public static List<String> quebrarTelefoneFixo(String telefone) {
        return quebrarTelefone(MASK_TELEFONE_FIXO, telefone);
    }

    public static List<String> quebrarTelefoneCelular(String telefone) {
        return quebrarTelefone(MASK_CELULAR, telefone);
    }

    public static String getCep(String cep) {
        return cep.replaceAll(MASK_CEP, "$1$2");
    }

    public static String getCpf(String cpf) {
        return cpf.replaceAll(MASK_CPF, "$1$2$3$4");
    }

    public static String getRg(String rg) {
        return rg.replaceAll(MASK_RG, "$1$2$3$4");
    }

    public static List<String> getDate(String date) {
        var list = new ArrayList<String>();
        list.add(date.replaceAll(MASK_DATA, "$1"));
        list.add(date.replaceAll(MASK_DATA, "$2"));
        list.add(date.replaceAll(MASK_DATA, "$3"));
        return list;
    }

}
