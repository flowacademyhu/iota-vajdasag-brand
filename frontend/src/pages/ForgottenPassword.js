import * as Yup from 'yup'
import React, { useState } from 'react'
import { Formik, Form } from 'formik'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import { forgottenpassword } from '../communications/userApi'

const ForgettenPasswordSchema = (invalidEmail, noEmail) =>
  Yup.object().shape({
    email: Yup.string().email(invalidEmail).required(noEmail),
  })

const ForgottenPassword = () => {
  const [error, setError] = useState()
  const [isAccepted, setIsAccepted] = useState(false)
  const { t } = useTranslation()

  const handleSubmit = async (value) => {
    try {
      await forgottenpassword(value)
      setIsAccepted(true)
    } catch (e) {
      if (e.message === 'no user') setError('forgottenpassword.nouser')
      if (e.message === 'no server') setError('forgottenpassword.noserver')
    }
  }
  const MyForm = ({ email, title, invalidemail, noEmail, buttontext }) => (
    <Formik
      initialValues={{
        email: '',
      }}
      onSubmit={handleSubmit}
      validationSchema={ForgettenPasswordSchema(invalidemail, noEmail)}
    >
      <Form>
        <h3 className="text-center">{title}</h3>
        <div className="my-3">
          <InputField
            label={email}
            name="email"
            id="email"
            placeholder={email}
            type="email"
          ></InputField>
        </div>
        <div className="text-center">
          <Button variant="primary" type="submit">
            {buttontext}
          </Button>
        </div>
      </Form>
    </Formik>
  )

  const Page = () => {
    if (isAccepted)
      return <h1 className="text-center">{t('forgottenpassword.accepted')}</h1>
    if (error && !isAccepted)
      return error && <h1 className="text-center">{t(error)}</h1>
    return (
      <MyForm
        title={t('forgottenpassword.title')}
        email={t('login.email')}
        invalidemail={t('login.invalidemail')}
        noEmail={t('login.noEmail')}
        buttontext={t('forgottenpassword.buttontext')}
      />
    )
  }

  return (
    <div className="d-flex flex-column justify-content-center align-content-center mx-auto col-10 col-md-4 min-vh-100">
      <Page />
    </div>
  )
}

export default ForgottenPassword
