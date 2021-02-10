import React, { useState } from "react";
import { Formik, Form, Field } from "formik";
import { useTranslation } from "react-i18next";
import * as Yup from "yup";

export default function Registration() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [taxNumber, setTaxNumber] = useState("");
  const [address, setAddress] = useState("");
  const { t, i18n } = useTranslation();
  i18n.changeLanguage("en");

  let validationSchema = Yup.object().shape({
    name: Yup.string()
      .required(t("registration.nameRequired")),
    email: Yup.string()
      .email(t("registration.invalidemail"))
      .required("Kötelező emailt megadni"),
    password: Yup.string()
    // .matches(
    //   /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/,
    //   "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and one special case Character"
    // ),
  
    .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*\_\-+()[\]{}></|"'.,:;]{8,}$/, "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number"),
    passwordConfirmation: Yup.string().oneOf([Yup.ref("password"), null], t("registration.passwordMatch")),
    taxNumber: Yup.string().required(),
    address: Yup.string().required(),
  });

//   legalább 8 karakter;
// legalább egy nagybetű és egy kisbetű; (?=.*[A-Za-z])
// legalább egy szám;
// szóközt nem tartalmazhat;
// érvényes egyéb karakterek:~ ! ? @ # $ % ^ & * _ - + ( ) [ ] { } > < / \ | " ' . , : ;

  return (
    <div>
      <h1>{t("registration.title")}</h1>
      <Formik
        initialValues={{
          name: "",
          email: "",
          password: "",
          address: "",
          taxNumber: "",
        }}
     
      >
        <Form>
          <div>
            <label htmlFor="name">{t("registration.fullName")}</label>
            <Field
              name="name"
              placeholder={t("registration.fullName")}
              type="text"
              value={name}
            ></Field>
          </div>
          <div>
            <label htmlFor="email">{t("registration.email")}</label>
            <Field
              name="email"
              placeholder={t("registration.email")}
              type="email"
              value={email}
            ></Field>
          </div>
          <div>
            <label htmlFor="password">{t("registration.password")}</label>
            <Field
              name="password"
              placeholder={t("registration.password")}
              type="password"
              value={password}
            ></Field>
          </div>
          <div>
            <label htmlFor="passwordConfirmation">{t("registration.passwordConfirmation")}</label>
            <Field
              name="passwordConfirmation"
              placeholder={t("registration.passwordConfirmation")}
              type="password"
              value={password}
            ></Field>
          </div>
          <div>
            <label>
              <Field name="entity" type="radio" value="privatePerson" />{" "}
              {t("registration.privatePerson")}
            </label>
            <label>
              <Field name="entity" type="radio" value="legalPerson" />{" "}
              {t("registration.legalPerson")}
            </label>
          </div>
          <div>
            <Field name="taxNumber">
              {({ field, form: { values }, meta }) =>
                values.entity === "legalPerson" && (
                  <>
                    <label htmlFor="taxNumber">
                      {t("registration.taxNumber")}
                    </label>
                    <input
                      type="text"
                      placeholder={t("registration.taxNumber")}
                      {...field}
                    />
                  </>
                )
              }
            </Field>
          </div>
          <div>
            <label htmlFor="address">{t("registration.address")}</label>
            <Field
              name="address"
              placeholder={t("registration.address")}
              type="text"
              values={address}
            ></Field>
          </div>
          <div>
            <label>
              <Field type="checkbox" name="toggle" />
              {t("registration.aszf")}
            </label>
          </div>
          <Button type="submit">{t("login.buttontext")}</Button>
        </Form>
      </Formik>
      <hr></hr>
    </div>
  );
}
