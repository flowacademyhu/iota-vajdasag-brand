import React, { useState, useEffect } from "react";
import { Formik, Form } from "formik";
import { useTranslation } from 'react-i18next';
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import * as Yup from 'yup';

import Button from "../components/Button";
import InputField from "../components/InputField";

const mock = new MockAdapter(axios)

mock.onPost("http://localhost:3000/api", { email: "teszt@teszt.com", password: "1234567" })
    .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv")

const Login = () => {
    const { t, i18n } = useTranslation();
    const [token, setToken] = useState("");

    const SignUpSchema = Yup.object().shape({
        email: Yup.string()
            .email(t("login.invalidemail"))
            .required(t("login.noEmail")),
        password: Yup.string().min(6, t("login.invalidPassword"))
    })

    const handleResponse = response => {
        setToken(response.data)
        console.log(token)
    }


    async function handleSubmit(value) {
        console.log("submitting: ", value)
        try {
            const response = await axios.post("http://localhost:3000/api", value);
            handleResponse(response)
        } catch (err) {
            handleResponse(err)
        }
    }

    return (
        <Formik
            initialValues={{
                email: "",
                password: ""
            }}
            onSubmit={handleSubmit}
            validationSchema={SignUpSchema}
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
                </div>
            </Form>
        </Formik>
    );
}


export default Login
