import * as Yup from 'yup'
import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import { forgottenPassword } from '../communications/userApi'
import ForgottenPasswordForm from '../components/ForgottenPasswordForm'

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
      await forgottenPassword(value)
      setIsAccepted(true)
    } catch (e) {
      if (e.message === 'no user') setError('forgottenPassword.nouser')
      if (e.message === 'no server') setError('forgottenPassword.noserver')
      else{
        setError('forgottenPassword.other')
      }
    }
  }

  const Page = () => {
    if (isAccepted)
      return <h1 className="text-center">{t('forgottenPassword.accepted')}</h1>
    if (error && !isAccepted)
      return error && <h1 className="text-center">{t(error)}</h1>
    return (
      <ForgottenPasswordForm
        title={t('forgottenPassword.title')}
        email={t('login.email')}
        invalidemail={t('login.invalidemail')}
        noEmail={t('login.noEmail')}
        buttontext={t('forgottenPassword.buttontext')}
        handleSubmit={handleSubmit}
        ForgettenPasswordSchema={ForgettenPasswordSchema}
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
