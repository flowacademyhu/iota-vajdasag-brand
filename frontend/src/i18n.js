import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import en from "./i18n/en.json";
import hu from "./i18n/hu.json";
import sr from "./i18n/sr.json";

// the translations
// (tip move them in a JSON file and import them)
const resources = {
  en: {
    translation: {
      "registration.title": "Registration",
      "registration.fullName": "Name",
      "registration.privatePerson": "Magánszemély",
      "registration.legalPerson": "Jogi személy",
      "registration.taxNumber": "Tax number",
      "registration.password": "Password",
      "registration.passwordConfirmation": "Confirm password",
      "registration.email": "Email",
      "registration.buttontext": "Registration",
      "registration.alreadyRegistered": "Already registered?",
      "registration.agreement": "I understood and agree with the terms and conditions",
      "registration.address": "Address",
      "registration.invalidemail": "Invalid email",
      "registration.login": "Login",
    },
  },
  fr: {
    translation: {
      "Welcome to React": "Bienvenue à React et react-i18next",
    },
  },
};
const resources = { en, hu, sr };

Object.keys(resources)
  .forEach(key => resources[key] = { translation: resources[key] })

i18n.use(initReactI18next).init({
  resources,
  lng: "en",

  keySeparator: ".",

  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
