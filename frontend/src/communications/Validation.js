import * as Yup from "yup";

const schema = (
  required,
  invalidEmail,
  passwordFailMessage,
  regPasswordMatch,
) =>
  Yup.object().shape({
    name: Yup.string().required(required),
    email: Yup.string().email(invalidEmail).required(required),
    password: Yup.string()
      .required(required)
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*_\-+()[\]{}></|"'.,:;]{8,}$/,
        passwordFailMessage
      ),
    passwordConfirmation: Yup.string().oneOf(
      [Yup.ref("password")],
      regPasswordMatch
    ),
    entity: Yup.string(),
    taxNumber: Yup.string().when("entity", {
      is: "legalPerson",
      then: Yup.string().required(required),
      otherwise: Yup.string(),
    }),
    address: Yup.string().required(required),
    acceptedTerms: Yup.boolean()
      .required(required)
      .oneOf([true], required),
  });

export default schema;
