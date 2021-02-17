import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { Formik, Form, Field } from "formik";
import { useTranslation } from "react-i18next";

import Button from "../components/Button";
import InputField from "../components/InputField";
import { signUp } from "../communications/userApi";
import "../communications/mockForUserApi";
import PopUpModal from "../components/PopUpModal";
import Validation from "../communications/Validation";

export default function Registration() {
  const { t } = useTranslation();
  const [isSignUpAccepted, setIsSignUpAccepted] = useState();
  const [modalShow, setModalShow] = React.useState(false);

  const handleSubmit = async (value) => {
    try {
      console.log(value);
      const response = await signUp(value);
      console.log(response.status);
      setIsSignUpAccepted(response.status);
      console.log(isSignUpAccepted);
    } catch (error) {
      console.log(error);
      console.log("error in handlesubmit");
      setIsSignUpAccepted(error.status);
    } finally {
      setModalShow(true);
    }
  };

  let history = useHistory();
  const redirect = () => {
    history.push("/login");
  };

  return (
    <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
      <Formik
        initialValues={{
          name: "",
          email: "",
          password: "",
          passwordConfirmation: "",
          address: "",
          taxNumber: "",
          acceptedTerms: false,
          entity: "privatePerson",
        }}
        onSubmit={handleSubmit}
        //validationSchema={{Validation}}
      >
        {({ errors, touched }) => (
          <Form>
            <h1 className="text-center">{t("registration.title")}</h1>
            <div>
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
              <div className="my-3 form-check-inline">
                <div>
                  <label>
                    <Field name="entity" type="radio" value="privatePerson" />{" "}
                    {t("registration.privatePerson")}
                  </label>
                </div>
                <div>
                  <label>
                    <Field name="entity" type="radio" value="legalPerson" />{" "}
                    {t("registration.legalPerson")}
                  </label>
                </div>
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
              <div className="my-3 form-check">
                <Field
                  className={`form-check-input ${
                    errors.acceptedTerms && touched.acceptedTerms
                      ? "is-invalid"
                      : ""
                  }`}
                  type="checkbox"
                  name="acceptedTerms"
                  id="acceptedTerms"
                />
                <label className="form-check-label" htmlFor="acceptedTerms">
                  {t("registration.agreement")}
                  <a
                    rel="noopener noreferrer"
                    target="_blank"
                    href="https://jusoft.hu/docs/Adatkezelesi%20tajekoztato_Jusoft.pdf"
                  >
                    {t("registration.linkadat")}
                  </a>
                  {t("registration.and")}
                  <a
                    rel="noopener noreferrer"
                    target="_blank"
                    href="https://jusoft.hu/docs/Adatkezelesi%20tajekoztato_Jusoft.pdf"
                  >
                    {t("registration.linkAszf")}
                  </a>
                </label>
                {errors.acceptedTerms && touched.acceptedTerms ? (
                  <div className="invalid-feedback">{errors.acceptedTerms}</div>
                ) : null}
              </div>
              <div>
                <Button
                  className="btn btn-primary btn-block col-12"
                  type="submit"
                >
                  {t("registration.buttontext")}
                </Button>
                {isSignUpAccepted === 201 ? (
                  <PopUpModal
                    modalTitle={t("registration.modalTitle")}
                    modalBody={t("registration.modalBodyOK")}
                    modalButton={t("registration.modalButton")}
                    show={modalShow}
                    onHide={() => setModalShow(false)}
                  />
                ) : (
                  <PopUpModal
                    modalTitle={t("registration.modalTitle")}
                    modalBody={t("registration.modalBodyError")}
                    modalButton={t("registration.modalButton")}
                    show={modalShow}
                    onHide={() => setModalShow(false)}
                  />
                )}
              </div>
            </div>
          </Form>
        )}
      </Formik>
      <hr></hr>
      <div>
        <p className="text-center">{t("registration.alreadyRegistered")}</p>
      </div>
      <div>
        <Button
          className="btn btn-primary btn-block col-12"
          type="button"
          onClick={redirect}
        >
          {t("registration.login")}
        </Button>
      </div>
    </div>
  );
}
