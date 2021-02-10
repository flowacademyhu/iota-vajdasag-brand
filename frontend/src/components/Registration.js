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

  let SignUpSchema = Yup.object().shape({
    name: Yup.string().required(t("registration.nameRequired")),
    email: Yup.string()
      .email(t("registration.invalidemail"))
      .required("Kötelező emailt megadni"),
      
    password: Yup.string().min(6, "hibás email"),
    taxNumber: Yup.string().required().min(),
    address: Yup.string().required()
  });

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
        validationSchema={SignUpSchema}
        onSubmit={(values, { setSubmitting }) => {
          setTimeout(() => {
            alert(JSON.stringify(values, null, 2));
            setSubmitting(false);
          }, 400);
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
            <label htmlFor="password">{t("registration.password")}</label>
            <Field
              name="password"
              placeholder={t("registration.passwordAgain")}
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

    </div>
  );
}
