import React, { useState } from 'react';
import { useTranslation } from 'react-i18next'
import { string } from "yup";
import { Button } from 'react-bootstrap'



const ForgetPassword = () => {
    const { t } = useTranslation()
    const [email, setEmail] = useState("");
    const [valid, setValid] = useState();

    const handleSubmit = () => {
        setValid(
            string()
                .email()
                .isValidSync(email)
        );
        if (valid) {
            console.log(email)
        }
    }

    const handleChange = e => {
        const value = e.currentTarget.value;
        setEmail(value);
    };

    return (
        <>
            <input type="text" value={email} onChange={handleChange} />
            <h1>{valid ? "is email" : "is not email"}</h1>
            <Button variant="primary" type="submit" onClick={handleSubmit}>
                {t('login.buttontext')}
            </Button>
        </>
    );
}

export default ForgetPassword;