package com.github.osipaandr;

/**
 * Класс предоставляет статические методы для склонения числительных и
 * сопутствующих слов в различные формы, такие как именительный, родительный и т. д.
 * для использования в текстовых представлениях, например, при формировании документации,
 * отчетов или других локализованных строк.
 */
public class RuNumbersByWords {

    private RuNumbersByWords() {
    }

    private static final Paradigm thousandParadigm =
            new Paradigm(Gender.FEMININE,
                    "тысяча",
                    "тысячи",
                    "тысяч");

    private static final Paradigm millionParadigm =
            new Paradigm(Gender.MASCULINE,
                    "миллион",
                    "миллиона",
                    "миллионов");

    private static String inclineLessThan20(int num, Gender gender) {
        switch (num) {
            case 0:
                return null;
            case 1:
                switch (gender) {
                    case FEMININE:
                        return "одна";
                    case MASCULINE:
                        return "один";
                    case NEUTER:
                        return "одно";
                }
            case 2:
                switch (gender) {
                    case FEMININE:
                        return "две";
                    case MASCULINE:
                    case NEUTER:
                        return "два";
                }
            case 3:
                return "три";
            case 4:
                return "четыре";
            case 5:
                return "пять";
            case 6:
                return "шесть";
            case 7:
                return "семь";
            case 8:
                return "восемь";
            case 9:
                return "девять";
            case 10:
                return "десять";
            case 11:
                return "одиннадцать";
            case 12:
                return "двенадцать";
            case 13:
                return "тринадцать";
            case 14:
                return "четырнадцать";
            case 15:
                return "пятнадцать";
            case 16:
                return "шестнадцать";
            case 17:
                return "семнадцать";
            case 18:
                return "восемнадцать";
            case 19:
                return "девятнадцать";
            default:
                throw new IllegalArgumentException("Argument must be less than 20");
        }
    }

    private static String tensToStr(int num) {
        int tens = num / 10 % 10;
        switch (tens) {
            case 2:
                return "двадцать";
            case 3:
                return "тридцать";
            case 4:
                return "сорок";
            case 5:
                return "пятьдесят";
            case 6:
                return "шестьдесят";
            case 7:
                return "семьдесят";
            case 8:
                return "восемьдесят";
            case 9:
                return "девяносто";
            default:
                throw new IllegalArgumentException(String.format("%d -> %d", num, tens));
        }
    }

    private static String hundredsToStr(int num) {
        int hundreds = num / 100 % 10;
        switch (hundreds) {
            case 1:
                return "сто";
            case 2:
                return "двести";
            case 3:
                return "триста";
            case 4:
                return "четыреста";
            case 5:
                return "пятьсот";
            case 6:
                return "шестьсот";
            case 7:
                return "семьсот";
            case 8:
                return "восемьсот";
            case 9:
                return "девятьсот";
            default:
                throw new IllegalArgumentException(String.format("%d -> %d", num, hundreds));
        }
    }

    private static String inclineLessThan100(int num, Gender gender) {
        if (num < 20)
            return inclineLessThan20(num, gender);

        String tens = tensToStr(num);
        String appendix = inclineLessThan20(num % 10, gender);
        return appendix == null
                ? tens
                : String.format("%s %s", tens, appendix);
    }

    private static String inclineLessThan1000(int num, Gender gender) {
        if (num < 100)
            return inclineLessThan100(num, gender);

        String hundreds = hundredsToStr(num);
        String appendix = inclineLessThan100(num % 100, gender);
        return appendix == null
                ? hundreds
                : String.format("%s %s", hundreds, appendix);
    }

    private static String inclineLessThanMillion(int num, Gender gender) {
        if (num < 1000)
            return inclineLessThan1000(num, gender);

        int modOf1000 = num % 1000;
        int divOf1000 = num / 1000;
        if (modOf1000 == 0)
            return incline(divOf1000, thousandParadigm);

        String rest = incline(modOf1000, gender);
        return rest == null
                ? incline(divOf1000, thousandParadigm)
                : String.format("%s %s", incline(divOf1000, thousandParadigm), rest);
    }

    private static String determineForm(int num, Paradigm paradigm) {
        int partBefore100 = num % 100;

        if (10 <= partBefore100 && partBefore100 <= 19)
            return paradigm.pluralGenitive;

        switch (partBefore100 % 10) {
            case 1:
                return paradigm.singularNominative;
            case 2:
            case 3:
            case 4:
                return paradigm.singularGenitive;
            default:
                return paradigm.pluralGenitive;
        }
    }

    /**
     * @param num Число, которое требуется представить числительным.
     * @return Строковое представление числительного.
     */
    public static String incline(int num) {
        if (num >= 1_000_000_000)
            throw new IllegalArgumentException("Argument must be less than 1 billion");

        return incline(num, Gender.MASCULINE);
    }

    /**
     * Склоняет переданное числительное в соответствии с заданным родом.
     *
     * @param num Число, которое требуется представить числительным.
     * @param gender Род, в котором числительное должно быть склонено.
     * @return Строковое представление числительного в склоненной форме, согласованной с указанным родом.
     */
    public static String incline(int num, Gender gender) {
        if (num < 1)
            throw new IllegalArgumentException("Argument must be positive");

        if (num < 1_000_000)
            return inclineLessThanMillion(num, gender);

        int modOfMillion = num % 1_000_000;
        int divOfMillion = num / 1_000_000;
        if (modOfMillion == 0)
            return incline(divOfMillion, millionParadigm);

        String rest = inclineLessThanMillion(modOfMillion, gender);
        return rest == null
                ? incline(divOfMillion, millionParadigm)
                : String.format("%s %s", incline(divOfMillion, millionParadigm), rest);
    }

    /**
     * Склоняет переданное числительное в соответствии с заданным образцом склонения.
     *
     * @param num Число, которое требуется представить числительным.
     * @param paradigm Образец склонения, содержащий информацию о роде и формах существительного в различных падежах.
     * @return Строковое представление числительного в склоненной форме, согласованной с переданным образцом.
     */
    public static String incline(int num, Paradigm paradigm) {
        if (num < 1) {
            System.out.printf("Num = %s%n", num);
            throw new IllegalArgumentException("Argument must be positive");
        }

        return String.format("%s %s",
                incline(num, paradigm.gender),
                determineForm(num, paradigm));
    }
}