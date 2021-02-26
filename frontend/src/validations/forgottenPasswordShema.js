import * as Yup from 'yup'

const forgettenPasswordSchema = (invalidEmail, noEmail) =>
  Yup.object().shape({
    email: Yup.string().email(invalidEmail).required(noEmail),
  })

export default forgettenPasswordSchema
