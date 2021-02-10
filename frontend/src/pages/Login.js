import React, { useState, useEffect } from "react";
import { Formik, Form } from "formik";
import { useTranslation } from 'react-i18next';
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import * as Yup from 'yup';

import Button from "../components/Button";
import InputField from "../components/InputField";
import { login } from "../communications/userApi";

const mock = new MockAdapter(axios)

mock.onPost("http://localhost:3000/api", { email: "teszt@teszt.com", password: "1234567" })
    .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv")


const SignUpSchema = (invalidEmail, noEmail, invalidPassword) => (Yup.object().shape({
    email: Yup.string()
        .email(invalidEmail)
        .required(noEmail),
    password: Yup.string().min(6, invalidPassword)
}))

const Login = () => {
    const { t, i18n } = useTranslation();
    const [token, setToken] = useState("");
    const [isSignInAccepted, setIsSignInAccepted] = useState()
    const [errorMessage, setErrorMessage] = useState()


    const handleResponse = response => {
        setToken(response.data)
        console.log(token)
    }


    async function handleSubmit(value) {
        console.log("submitting: ", value)
        try {
            const response = await login(value)
            handleResponse(response)
            setIsSignInAccepted(true)
        } catch (error) {
            setIsSignInAccepted(false)
            if (error.response.status == 404) setErrorMessage(t("login.connectionProblems"))
            else if (error.response.status == 400) setErrorMessage(t("login.invalidData"))
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
                    <Button type="submit">{t("login.buttontext")}</Button>
                    {!isSignInAccepted ? (
                        <h5 className="text-danger text-center my-3">{errorMessage}</h5>
                    ) : (<></>)}
                </div>
            </Form>
        </Formik>
    );
}


export default Login
