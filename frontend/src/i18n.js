import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import en from "./i18n/en.json";
import hu from "./i18n/hu.json";
import sr from "./i18n/sr.json";

const resources = { en, hu, sr };

i18n.use(initReactI18next).init({
  resources,
  lng: "en",

  keySeparator: ".",
  defaultNS: "",
  ns: "",

  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
