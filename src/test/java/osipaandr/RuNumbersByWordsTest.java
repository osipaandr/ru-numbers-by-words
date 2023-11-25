package osipaandr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuNumbersByWordsTest {

    void assertInclination(String phrase, int number) {
        assertEquals(phrase, RuNumbersByWords.incline(number));
    }

    @Test
    void testNumbersOnly() {
        assertInclination("один", 1);
        assertInclination("пятнадцать", 15);
        assertInclination("двадцать два", 22);
        assertInclination("сто", 100);
        assertInclination("сто пять", 105);
        assertInclination("двести восемнадцать", 218);
        assertInclination("пятьсот шестьдесят семь", 567);
        assertInclination("одна тысяча восемь", 1008);
        assertInclination("три тысячи", 3000);
        assertInclination("шесть тысяч сорок", 6040);
        assertInclination("семь тысяч одиннадцать", 7011);
        assertInclination("восемь тысяч четыреста", 8400);
        assertInclination("пятнадцать тысяч сто два", 15_102);
        assertInclination("четыре миллиона триста пятьдесят тысяч девятьсот восемьдесят семь", 4_350_987);
        assertInclination("сорок миллионов сорок", 40_000_040);
    }

    void assertInclination(String phrase, int number, Paradigm paradigm) {
        assertEquals(phrase, RuNumbersByWords.incline(number, paradigm));
    }

    @Test
    void testNumbersWithWords() {
        Paradigm pencilParadigm = new Paradigm(Gender.MASCULINE,
                "карандаш",
                "карандаша",
                "карандашей");
        Paradigm penParadigm = new Paradigm(Gender.FEMININE,
                "ручка",
                "ручки",
                "ручек");
        Paradigm windowParadigm = new Paradigm(Gender.NEUTER,
                "окно",
                "окна",
                "окон");

        assertInclination("один карандаш", 1, pencilParadigm);
        assertInclination("две ручки", 2, penParadigm);
        assertInclination("двадцать три карандаша", 23, pencilParadigm);
        assertInclination("сорок окон", 40, windowParadigm);
        assertInclination("девяносто одно окно", 91, windowParadigm);
        assertInclination("четыреста ручек", 400, penParadigm);
        assertInclination("двести три окна", 203, windowParadigm);
        assertInclination("девятьсот девяносто девять карандашей", 999, pencilParadigm);
        assertInclination("одна тысяча тридцать ручек", 1030, penParadigm);
        assertInclination("двадцать тысяч четыреста окон", 20_400, windowParadigm);
        assertInclination("восемнадцать миллионов карандашей", 18_000_000, pencilParadigm);
        assertInclination("двести четыре миллиона сто две ручки", 204_000_102, penParadigm);
    }
}