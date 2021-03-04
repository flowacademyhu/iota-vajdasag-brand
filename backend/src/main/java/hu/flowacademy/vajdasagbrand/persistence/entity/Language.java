package hu.flowacademy.vajdasagbrand.persistence.entity;

import org.cyrlat.CyrillicLatinConverter;

public enum Language {
    hu {
        @Override
        public String translate(String toTranslate) {
            return toTranslate;
        }
    }, en {
        @Override
        public String translate(String toTranslate) {
            return toTranslate;
        }
    }, rs {
        @Override
        public String translate(String toTranslate) {
            return CyrillicLatinConverter.cyrilicToLatin(toTranslate);
        }
    };


    public abstract String translate(String toTranslate);
}
