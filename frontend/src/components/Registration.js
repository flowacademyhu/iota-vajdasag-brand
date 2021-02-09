import React, { useState } from "react";
import { Formik, Form, Field } from "formik";
import { useTranslation } from "react-i18next";

export default function Registration() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [taxNumber, setTaxNumber] = useState("");
  const [address, setAddress] = useState("");
  const { t, i18n } = useTranslation();
  i18n.changeLanguage("en");

  return (
    <div>
      <h1>{t("registration.title")}</h1>
      <Formik
        initialValues={{ name: "", email: "", password: "" }}
        validate={(values) => {
          const errors = {};
          if (!values.email) {
            errors.email = "Required";
          } else if (
            !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
          ) {
            errors.email = "Invalid email address";
          }
          return errors;
        }}
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
                      type="number"
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
        </Form>
      </Formik>
    </div>
  );
}
