package com.fatec.sigvs.model;

import java.util.InputMismatchException;

public class ValidaCpf {
    private String umCpf;

    public ValidaCpf(String c) {
        this.umCpf = c;
    }

    public boolean isValido() {
        if (umCpf.equals("00000000000") || umCpf.equals("11111111111") || umCpf.equals("22222222222")
                || umCpf.equals("33333333333") || umCpf.equals("44444444444") || umCpf.equals("55555555555")
                || umCpf.equals("66666666666") || umCpf.equals("77777777777") || umCpf.equals("88888888888")
                || umCpf.equals("99999999999") || (umCpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (umCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;

            for (i = 0; i < 10; i++) {
                num = (umCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == umCpf.charAt(9)) && (dig11 == umCpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public String getCpf() {
        return umCpf;
    }
}