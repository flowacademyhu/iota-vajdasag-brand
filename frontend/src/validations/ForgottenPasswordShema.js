import * as Yup from 'yup'


const ForgettenPasswordSchema = (invalidEmail, noEmail) =>
  Yup.object().shape({
    email: Yup.string().email(invalidEmail).required(noEmail),
  })

  export default ForgettenPasswordSchema