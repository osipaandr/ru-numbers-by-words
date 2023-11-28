package com.osipaandr;

public class Paradigm {
    Gender gender;
    String singularNominative;
    String singularGenitive;
    String pluralGenitive;

    public Paradigm(Gender gender, String singularNominative, String singularGenitive, String pluralGenitive) {
        this.gender = gender;
        this.singularNominative = singularNominative;
        this.singularGenitive = singularGenitive;
        this.pluralGenitive = pluralGenitive;
    }
}
