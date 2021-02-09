import React, { useState } from "react";
import { Formik, Form, Field } from "formik";
import { useTranslation } from 'react-i18next';
import axios from "axios";

import Button from "../components/Button";


export default () => {
    const { t, i18n } = useTranslation();
    const [token, setToken] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    i18n.changeLanguage('hu');

    return (
        <Formik
            initialValues={{
                email: '',
                password: '',
            }}
            onSubmit={values => {
                console.log(values)
                axios
                    .get("/", {
                        params: {
                            email: values.email,
                            password: values.password
                        }
                    })
                    .then(response => console.log(response))
                    .catch(e => {
                        console.error(e.response.data.error);
                    })
            }}>
            <Form className="container">
                <div className=" row justify-content-center mx-auto col-6">
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