import i18n from "i18next";
import { initReactI18next } from "react-i18next";

const resources = {
  en: {
    translation: {
      "Welcome to React": "Hi",
    },
  },
  sr: {
    translation: {
      "Welcome to React": "Zdravo",
    },
  },
  hu: {
    translation: {
      "Welcome to React": "Udv",
    },
  },
};

i18n.use(initReactI18next).init({
  resources,
  lng: "hu",

  keySeparator: false,

  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
