import i18n from "i18next";
import { initReactI18next } from "react-i18next";

// the translations
// (tip move them in a JSON file and import them)
const resources = {
  en: {
    translation: {
      "login.title": "Sign In",
      "login.email": "Email",
      "login.password":"Password",
      "login.buttontext":"Sign in",
      "login.invalidemail":"Bad email",
      "login.invalidPassword":"The password is min. 8 character",
      "login.noEmail":"Give email",
      "login.connectionProblems":"Connection problems",
      "login.invalidData":"Wrong email or password"

    }
  },
  hu: {
    translation: {
      "login.title": "Bejelentkezés",
      "login.email": "Email cím",
      "login.password":"Jelszó",
      "login.buttontext":"Belépés",
      "login.invalidemail":"Hibás email cím",
      "login.invalidPassword":"A jelszó minimum 8 karater",
      "login.noEmail":"Szükséges emailt megadni",
      "login.connectionProblems":"Internet kapcsolati probléma",
      "login.invalidData":"Hibás email cím vagy jelszó"
    }
  }
};

i18n
  .use(initReactI18next) // passes i18n down to react-i18next
  .init({
    resources,
    lng: "en",

    keySeparator: false, // we do not use keys in form messages.welcome

    interpolation: {
      escapeValue: false // react already safes from xss
    }
  });

  export default i18n;