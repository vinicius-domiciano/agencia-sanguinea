package br.com.dev.baselib.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    public static Date getData(String data) {
        var calendar = Calendar.getInstance();
        var listDate = MaskUtils.getDate(data);

        calendar.set(
                Integer.valueOf(listDate.get(2)),
                Integer.valueOf(listDate.get(1)) - 1,
                Integer.valueOf(listDate.get(0))
        );

        return calendar.getTime();
    }

    public static Integer getIdade(Date nascimento) {
        var calendar = Calendar.getInstance();
        var calendaNascimento = new GregorianCalendar();
        calendaNascimento.setTime(nascimento);

        var idade = calendar.get(Calendar.YEAR) - calendaNascimento.get(Calendar.YEAR);

        calendaNascimento.add(Calendar.YEAR, idade);

        if (calendar.before(calendaNascimento)) idade --;

        return idade;
    }

    public static Date addAno(int anos) {
        var calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, anos);
        return calendar.getTime();
    }

}
