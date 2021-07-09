package br.com.dev.baselib.utils;

public class ImcUtils {

    public static float calcularImc(float peso, float altura) {
        var alturaPow = String.valueOf(Math.pow(altura, 2));
        return peso / Float.parseFloat(alturaPow);
    }

}
