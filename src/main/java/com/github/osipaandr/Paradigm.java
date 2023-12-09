package com.github.osipaandr;

/**
 * Представляет собой образец склонения существительного в русском языке с учетом рода и падежей.
 * Этот класс используется для определения форм существительного в единственном и множественном числе
 * в различных грамматических падежах.
 *
 * @see Gender
 */
public class Paradigm {

    /**
     * Род существительного (женский, мужской, средний).
     */
    Gender gender;

    /**
     * Форма существительного в именительном падеже в единственном числе.
     */
    String singularNominative;

    /**
     * Форма существительного в родительном падеже в единственном числе.
     */
    String singularGenitive;

    /**
     * Форма существительного в родительном падеже во множественном числе.
     */
    String pluralGenitive;

    /**
     * @param gender              Род существительного.
     * @param singularNominative  Форма в именительном падеже в единственном числе.
     * @param singularGenitive    Форма в родительном падеже в единственном числе.
     * @param pluralGenitive      Форма в родительном падеже во множественном числе.
     */
    public Paradigm(Gender gender, String singularNominative, String singularGenitive, String pluralGenitive) {
        this.gender = gender;
        this.singularNominative = singularNominative;
        this.singularGenitive = singularGenitive;
        this.pluralGenitive = pluralGenitive;
    }
}
