import * as Yup from "yup";

const schema = (
  nameRequired,
  invalidEmail,
  emailRequired,
  passwordRequired,
  passwordFailMessage,
  mustAcceptTandT,
  regPasswordMatch,
  required
) =>
  Yup.object().shape({
    name: Yup.string().required( nameRequired ),
    email: Yup.string().email( invalidEmail ).required(emailRequired),
    password: Yup.string()
      .required( passwordRequired )
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*_\-+()[\]{}></|"'.,:;]{8,}$/,
         passwordFailMessage 
      ),
    passwordConfirmation: Yup.string().oneOf([Yup.ref("password")], 
      regPasswordMatch,
    ),
    entity: Yup.string(),
    taxNumber: Yup.string().when("entity", {
      is: "legalPerson",
      then: Yup.string().required( required ),
      otherwise: Yup.string(),
    }),
    address: Yup.string().required( required ),
    acceptedTerms: Yup.boolean()
      .required( required )
      .oneOf([true], mustAcceptTandT ), //
  });

export default schema;
