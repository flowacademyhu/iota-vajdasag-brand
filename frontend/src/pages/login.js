import React, { useState } from "react";
import { Formik, Form, Field } from "formik";
import { useTranslation } from 'react-i18next';
import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import * as Yup from 'yup';

import Button from "../components/Button";



export default () => {
    const { t, i18n } = useTranslation();
    const [token, setToken] = useState();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    i18n.changeLanguage('hu');


    const SignUpSchema = Yup.object().shape({
        email: Yup
            .string()
            .email(t("login.invalidemail"))
            .required("Kötelező emailt megadni"),
        password: Yup.string().min(6, "hibás email")
    })

    const mock = new MockAdapter(axios)

    mock.onGet("http://localhost:3000/api", { params: { email: "teszt@teszt.com", password: "1234567" } })
        .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv")

    return (
        <Formik
            initialValues={{
                email: '',
                password: ''
            }}
            validationSchema={SignUpSchema}
            onSubmit={values => {
                console.log(values)
                axios
                    .get("http://localhost:3000/api", {
                        params: {
                            email: values.email,
                            password: values.password
                        }
                    })
                    .then(response => setToken(response))
                    .catch(e => {
                        console.error(e);
                    })
            }}>
            <Form className="container">
                <div className="justify-content-center mx-auto col-6">
                    <h3>{t("login.title")}</h3>
                    <div className="my-3">
                        <label htmlFor="email">{t("login.email")}</label>
                        <Field className="form-control" name="email" placeholder={t("login.email")} type="email" values={email}></Field>
                    </div>
                    <div className="my-3">
                        <label htmlFor="password">{t("login.password")}</label>
                        <Field className="form-control" name="password" placeholder={t("login.password")} type="password" values={password}></Field>
                    </div>
                    <Button type="submit">{t("login.buttontext")}</Button>
                </div>
            </Form>
        </Formik>
    );
}