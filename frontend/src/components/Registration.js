import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { Formik, Form, Field } from "formik";
import { useTranslation } from "react-i18next";
import * as Yup from "yup";
import axios from "axios";
import Button from "../components/Button";

export default function Registration() {
  const [token, setToken] = useState("");
  const { t, i18n } = useTranslation();
  i18n.changeLanguage("en");

  let SignUpSchema = Yup.object().shape({
    name: Yup.string().required(t("registration.nameRequired")),
    email: Yup.string()
      .email(t("registration.invalidemail"))
      .required("Kötelező emailt megadni"),
    password: Yup.string().matches(
      /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*_\-+()[\]{}></|"'.,:;]{8,}$/,
      "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number"
    ),
    passwordConfirmation: Yup.string().oneOf(
      [Yup.ref("password"), null],
      t("registration.passwordMatch")
    ),
    taxNumber: Yup.string().required(),
    address: Yup.string().required(),
  });

  const handleResponse = (response) => {
    setToken(response.data);
    console.log(token);
  };
  async function handleSubmit(value) {
    console.log("submitting: ", value);
    try {
      const response = await axios.post("http://localhost:3000/api", value);
      handleResponse(response);
    } catch (err) {
      handleResponse(err);
    }
  }

  let history = useHistory();
  const redirect = () => {
    history.push("../pages/login");
  };

  return (
    <div>
      <h1 className="text-center">{t("registration.title")}</h1>
      <Formik
        initialValues={{
          name: "",
          email: "",
          password: "",
          address: "",
          taxNumber: "",
        }}
        onSubmit={handleSubmit}
        validationSchema={SignUpSchema}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
            <div className="my-3">
              <label htmlFor="name">{t("registration.fullName")}</label>
              <Field
                name="name"
                placeholder={t("registration.fullName")}
                type="text"
              ></Field>
            </div>
            <div className="my-3">
              <label htmlFor="email">{t("registration.email")}</label>
              <Field
                name="email"
                placeholder={t("registration.email")}
                type="email"
              ></Field>
            </div>
            <div className="my-3">
              <label htmlFor="password">{t("registration.password")}</label>
              <Field
                name="password"
                placeholder={t("registration.password")}
                type="password"
              ></Field>
            </div>
            <div className="my-3">
              <label htmlFor="passwordConfirmation">
                {t("registration.passwordConfirmation")}
              </label>
              <Field
                name="passwordConfirmation"
                placeholder={t("registration.passwordConfirmation")}
                type="password"
              ></Field>
            </div>
            <div className="my-3">
              <label>
                <Field name="entity" type="radio" value="privatePerson" />{" "}
                {t("registration.privatePerson")}
              </label>
              <label>
                <Field name="entity" type="radio" value="legalPerson" />{" "}
                {t("registration.legalPerson")}
              </label>
            </div>
            <div className="my-3">
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
              ></Field>
            </div>
            <div className="my-3">
              <label>
                <Field type="checkbox" name="toggle" />
                {t("registration.aszf")}
              </label>
            </div>
            <div>
              <Button type="submit">{t("registration.buttontext")}</Button>
            </div>
          </div>
        </Form>
      </Formik>
      <hr></hr>
      <div>
        <Button type="button" onClick={redirect}>
          {t("registration.alreadyRegistered")}
        </Button>
      </div>
    </div>
  );
}
