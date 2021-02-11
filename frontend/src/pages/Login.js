import React, { useState } from "react";
import { Formik, Form } from "formik";
import { useTranslation } from 'react-i18next';
import * as Yup from 'yup';

import Button from "../components/Button";
import InputField from "../components/InputField";
import { login } from "../communications/userApi";
import { loginMock } from "../communications/mockForUserApi";


loginMock();

const SignUpSchema = (invalidEmail, noEmail, invalidPassword) => (Yup.object().shape({
    email: Yup.string()
        .email(invalidEmail)
        .required(noEmail),
    password: Yup.string()
        .min(8, invalidPassword)
        .required()
}))

const Login = () => {
    const { t } = useTranslation();
    const [isSignInAccepted, setIsSignInAccepted] = useState()
    const [errorMessage, setErrorMessage] = useState()


    const handleResponse = response => {
        sessionStorage.setItem("token", response.data)
    }


    async function handleSubmit(value) {
        try {
            const response = await login(value)
            handleResponse(response)
            setIsSignInAccepted(true)
        } catch (error) {
            setIsSignInAccepted(false)
            if (error.response.status === 404) setErrorMessage(t("login.connectionProblems"))
            else if (error.response.status === 400) setErrorMessage(t("login.invalidData"))
        }
    }

    return (
        <Formik
            initialValues={{
                email: "",
                password: ""
            }}
            onSubmit={handleSubmit}
            validationSchema={SignUpSchema(t("login.invalidemail"), t("login.noEmail"), t("login.invalidPassword"))}
        >
            <Form>
                <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
                    <h3 className="text-center">{t("login.title")}</h3>
                    <div className="my-3">
                        <InputField label={t("login.email")} name="email" id="email" placeholder={t("login.email")} type="email"></InputField>
                    </div>
                    <div className="my-3">
                        <InputField label={t("login.password")} name="password" id="password" placeholder={t("login.password")} type="password"></InputField>
                    </div>
                    <Button className="btn btn-primary btn-block col-12" type="submit">{t("login.buttontext")}</Button>
                    <h1>{window.sessionStorage.getItem("token")}</h1>
                    {!isSignInAccepted ? (
                        <h5 className="text-danger text-center my-3">{errorMessage}</h5>
                    ) : (<></>)}
                </div>
            </Form>
        </Formik>
    );
}


export default Login
