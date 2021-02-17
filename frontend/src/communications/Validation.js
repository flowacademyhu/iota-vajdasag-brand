import { useTranslation } from "react-i18next";
import * as Yup from "yup";


  const schema = Yup.object().shape({
    name: Yup.string().required(t("registration.nameRequired")),
    email: Yup.string()
      .email(t("registration.invalidemail"))
      .required(t("Email is required")),
    password: Yup.string()
      .required(t("Required"))
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*_\-+()[\]{}></|"'.,:;]{8,}$/,
        t(`Must Contain 8 Characters, One Uppercase, One Lowercase, One Number. 
        Optional special characters are: !?@#$%^&*_-+()[]{}></|"'.,:; `)
      ),
    passwordConfirmation: Yup.string().oneOf(
      [Yup.ref("password")],
      t("registration.passwordMatch")
    ),
    entity: Yup.string(),
    taxNumber: Yup.string().when("entity", {
      is: "legalPerson",
      then: Yup.string().required(t("Required")),
      otherwise: Yup.string(),
    }),
    address: Yup.string().required(t("Required")),
    acceptedTerms: Yup.boolean()
      .required(t("Required"))
      .oneOf([true], t("You must accept terms and conditions")),
  });
