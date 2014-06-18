package com.thm.unicap.app.util;

import com.thm.unicap.app.model.SubjectStatus;

import java.util.Calendar;

public class UnicapUtils {

    @SuppressWarnings("SpellCheckingInspection")
    private static String[][] nameExceptions = {
            {"Da", "da"},
            {"De", "de"},
            {"Do", "do"},
    };

    @SuppressWarnings("SpellCheckingInspection")
    private static String[][] exceptions = {
            {"A", "a"},
            {"Administracao", "Administração"},
            {"Algebra", "Álgebra"},
            {"Analise", "Análise"},
            {"Arq", "Arquitetura"},
            {"Basica", "Básica"},
            {"Calculo", "Cálculo"},
            {"Ciencia", "Ciência"},
            {"Computacao", "Computação"},
            {"Conclusao", "Conclusão"},
            {"Conhecimen", "Conhecimento"},
            {"Cr", "Créditos"},
            {"Da", "da"},
            {"De", "de"},
            {"Dif", "Diferencial"},
            {"Dir", "Direito"},
            {"Distribuidos", "Distribuídos"},
            {"Do", "do"},
            {"E", "e"},
            {"Em", "em"},
            {"Eletronica", "Eletrônica"},
            {"Estatistica", "Estatística"},
            {"Estagio", "Estágio"},
            {"Etica", "Ética"},
            {"Fil", "Filosofia"},
            {"Grafica", "Gráfica"},
            {"Ii", "II"},
            {"Iii", "III"},
            {"Iv", "IV"},
            {"Informacao", "Informação"},
            {"Informatica", "Informática"},
            {"Ingles", "Inglês"},
            {"Inteligencia", "Inteligência"},
            {"Introd", "Introdução"},
            {"Introducao", "Introdução"},
            {"Jurid", "Jurídica"},
            {"Ling", "Linguagens"},
            {"Logica", "Lógica"},
            {"Matematica", "Matemática"},
            {"Metodos", "Métodos"},
            {"Multimidia", "Multimídia"},
            {"Numericos", "Numéricos"},
            {"Org", "Organização"},
            {"Paradig", "Paradigmas"},
            {"Portugues", "Português"},
            {"Pratica", "Prática"},
            {"Prat", "Prática"},
            {"Programacao", "Programação"},
            {"Publico", "Público"},
            {"Tecnologico", "Tecnológico"},
            {"Transcendencia", "Transcendência"},
            {"Tributario", "Tributário"},
    };

    public static boolean isRegistrationValid(String email) {

        if(email.length() != 11) return false;

        for (int i = 0; i < email.length(); i++) {
            if(i==9) {
                if(email.charAt(i) != '-') return false;
            } else {
                if(!Character.isDigit(email.charAt(i))) return false;
            }
        }

        return true;
    }

    public static boolean isPasswordValid(String password) {

        if(password.length() != 6) return false;

        for (int i = 0; i < password.length(); i++) {
            if(!Character.isDigit(password.charAt(i))) return false;
        }

        return true;
    }

    public static String replaceNameExceptions (String str) {
        return replaceFromDictionary(str, nameExceptions);
    }

    public static String replaceExceptions (String str) {
        return replaceFromDictionary(str, exceptions);
    }

    public static String replaceFromDictionary (String str, String[][] replacePairs) {

        for (String[] replacePair : replacePairs)
            str = str.replaceAll("(?i)\\b"+replacePair[0]+"\\b", replacePair[1]);

        return str;
    }

    public static SubjectStatus.ScheduleWeekDay getCurrentScheduleWeek() {
        Calendar c = Calendar.getInstance();

        int day_of_week = c.get(Calendar.DAY_OF_WEEK);

        switch (day_of_week) {
            case 1:
                return SubjectStatus.ScheduleWeekDay.Sun;
            case 2:
                return SubjectStatus.ScheduleWeekDay.Mon;
            case 3:
                return SubjectStatus.ScheduleWeekDay.Tue;
            case 4:
                return SubjectStatus.ScheduleWeekDay.Wed;
            case 5:
                return SubjectStatus.ScheduleWeekDay.Thu;
            case 6:
                return SubjectStatus.ScheduleWeekDay.Fri;
            case 7:
                return SubjectStatus.ScheduleWeekDay.Sat;
            default:
                return null;
        }
    }

}
