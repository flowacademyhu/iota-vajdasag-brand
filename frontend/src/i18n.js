import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import TranslationsFromJson from "./translations.json";

const resources = TranslationsFromJson;

i18n.use(initReactI18next).init({
  resources,
  lng: "hu",

  keySeparator: false,

  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
