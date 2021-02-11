import i18n from "i18next";
import { initReactI18next } from "react-i18next";

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
      "registration.email": "Email",
      "registration.buttontext": "Registration",
      "registration.alreadyRegistered": "Already registered?",
      "registration.aszf": "I understood everything...",
      "registration.address": "Address",
      "registration.invalidemail": "Invalid email",
    },
  },
  fr: {
    translation: {
      "Welcome to React": "Bienvenue à React et react-i18next",
    },
  },
};

i18n
  .use(initReactI18next) // passes i18n down to react-i18next
  .init({
    resources,
    lng: "en",

    keySeparator: false, // we do not use keys in form messages.welcome

    interpolation: {
      escapeValue: false, // react already safes from xss
    },
  });

export default i18n;
