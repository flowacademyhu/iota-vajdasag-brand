import * as Yup from 'yup'

const eventAddValidation = (
  requiredMessage,
  maxHundredChars,
  maxThousandChars
) =>
  Yup.object().shape({
    name: Yup.string().max(100, maxHundredChars).required(requiredMessage),
    bio: Yup.string().max(1000, maxThousandChars).required(requiredMessage),
    place: Yup.string().max(100, maxHundredChars).required(requiredMessage),
  })

export default eventAddValidation
