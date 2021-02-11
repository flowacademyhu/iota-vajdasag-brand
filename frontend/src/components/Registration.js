import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { Formik, Form, Field } from "formik";
import { useTranslation } from "react-i18next";
import * as Yup from "yup";
import Button from "../components/Button";
import InputField from "../components/InputField";
import { signUp } from "../communications/userApi";

export default function Registration() {
  const [token, setToken] = useState("");
  const { t } = useTranslation();
  const [isSignUpAccepted, setIsSignUpAccepted] = useState();
  const [errorMessage, setErrorMessage] = useState();

  const schema = Yup.object().shape({
    name: Yup.string().required(t("registration.nameRequired")),
    email: Yup.string()
      .email(t("registration.invalidemail"))
      .required("Email is required"),
    password: Yup.string()
      .required("Required")
      .matches(
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!?@#$%^&*_\-+()[\]{}></|"'.,:;]{8,}$/,
        `Must Contain 8 Characters, One Uppercase, One Lowercase, One Number. 
        Optional special characters are: !?@#$%^&*_-+()[]{}></|\"'.,:; `
      ),
    passwordConfirmation: Yup.string().oneOf(
      [Yup.ref("password")],
      t("registration.passwordMatch")
    ),
    taxNumber: Yup.string().required("Required"),
    address: Yup.string().required("Required"),
    acceptedTerms: Yup.boolean()
      .required("Required")
      .oneOf([true], "You must accept terms and conditions"),
  });

  const handleResponse = (response) => {
    setToken(response.data);
    console.log(token);
  };

  async function handleSubmit(value) {
    console.log("submitting: ", value);
    try {
      const response = await signUp(value);
      handleResponse(response);
      setIsSignUpAccepted(true);
    } catch (error) {
      setIsSignUpAccepted(false);
      if (error.response.status === 404)
        setErrorMessage(t("registration.connectionProblems"));
      else if (error.response.status === 400)
        setErrorMessage(t("registration.invalidData"));
    }
  }

  let history = useHistory();
  const redirect = () => {
    history.push("/login");
  };

  return (
    <div>
      <Formik
        initialValues={{
          name: "",
          email: "",
          password: "",
          address: "",
          taxNumber: "",
          acceptedTerms: false,
          entity: "privatePerson",
        }}
        onSubmit={handleSubmit}
        validationSchema={schema}
      >
        {({ errors, touched }) => (
          <Form>
            <h1 className="text-center">{t("registration.title")}</h1>
            <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
              <div className="my-3">
                <InputField
                  label={t("registration.fullName")}
                  name="name"
                  placeholder={t("registration.fullName")}
                  type="text"
                ></InputField>
              </div>
              <div className="my-3">
                <InputField
                  label={t("registration.email")}
                  name="email"
                  placeholder={t("registration.email")}
                  type="email"
                ></InputField>
              </div>
              <div className="my-3">
                <InputField
                  label={t("registration.password")}
                  name="password"
                  placeholder={t("registration.password")}
                  type="password"
                ></InputField>
              </div>
              <div className="my-3">
                <InputField
                  label={t("registration.passwordConfirmation")}
                  name="passwordConfirmation"
                  placeholder={t("registration.passwordConfirmation")}
                  type="password"
                ></InputField>
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
                          className={`form-control ${
                            meta.touched && meta.error ? "is-invalid" : ""
                          }`}
                        />
                        {meta.touched && meta.error && (
                          <div className="invalid-feedback">{meta.error}</div>
                        )}
                      </>
                    )
                  }
                </Field>
              </div>
              <div>
                <InputField
                  label={t("registration.address")}
                  name="address"
                  placeholder={t("registration.address")}
                  type="text"
                ></InputField>
              </div>
              <div className="my-3">
                <label>
                  <Field type="checkbox" name="acceptedTerms" />
                  {t("registration.aszf")}
                </label>
              </div>
              <div>
                <Button type="submit">{t("registration.buttontext")}</Button>
                {!isSignUpAccepted ? (
                  <h5 className="text-danger text-center my-3">
                    {errorMessage}
                  </h5>
                ) : (
                  <></>
                )}
              </div>
            </div>
          </Form>
        )}
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
