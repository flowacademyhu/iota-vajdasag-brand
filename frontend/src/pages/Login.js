import React, { useState } from 'react'
import { Formik, Form } from 'formik'
import { useTranslation } from 'react-i18next'
import * as Yup from 'yup'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import { login } from '../communications/userApi'
import useTokenStateHandler from '../hooks/useTokenStateHandler'
import { useHistory, Link } from 'react-router-dom'

const SignUpSchema = (invalidEmail, noEmail, invalidPassword) =>
  Yup.object().shape({
    username: Yup.string().email(invalidEmail).required(noEmail),
    password: Yup.string().min(8, invalidPassword).required(),
  })

const Login = () => {
  const { t } = useTranslation()
  const [isSignInAccepted, setIsSignInAccepted] = useState()
  const [errorMessage, setErrorMessage] = useState()
  const { writeToken } = useTokenStateHandler()

  const handleResponse = (response) => {
    writeToken(response.data.access_token)
    setIsSignInAccepted(true)
  }

  let history = useHistory()
  const handleRegButton = () => {
    history.push('/registration')
  }

  async function handleSubmit(value) {
    try {
      const response = await login(value)
      handleResponse(response)
    } catch (error) {
      setIsSignInAccepted(false)
      if (error.response.status === 404)
        setErrorMessage(t('login.connectionProblems'))
      else if (error.response.status === 400)
        setErrorMessage(t('login.invalidData'))
    }
  }

  return (
    <div>
      <Formik
        initialValues={{
          username: '',
          password: '',
        }}
        onSubmit={handleSubmit}
        validationSchema={SignUpSchema(
          t('login.invalidemail'),
          t('login.noEmail'),
          t('login.invalidPassword')
        )}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
            <h3 className="text-center">{t('login.title')}</h3>
            <div className="my-3">
              <InputField
                label={t('login.email')}
                name="username"
                id="email"
                placeholder={t('login.email')}
                type="email"
              ></InputField>
            </div>
            <div className="my-3">
              <InputField
                label={t('login.password')}
                name="password"
                id="password"
                placeholder={t('login.password')}
                type="password"
              ></InputField>
            </div>
            <Button variant="primary" type="submit">
              {t('login.buttontext')}
            </Button>
            <Link
              className="text-center text-decoration-none"
              to="/forgottenpassword"
            >
              {t('login.forgottenPassword')}
            </Link>
            {!isSignInAccepted && (
              <h5 className="text-danger text-center my-3">{errorMessage}</h5>
            )}
            <h6 className="text-center">
              {t('registration.alreadyRegistered')}
            </h6>
            <Button type="button" onClick={handleRegButton}>
              {t('registration.title')}
            </Button>
          </div>
        </Form>
      </Formik>
    </div>
  )
}

export default Login
