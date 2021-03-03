import * as Yup from 'yup'

const eventAddValidation = () =>
  Yup.object().shape({
    name: Yup.string().max(50),
    bio: Yup.string().max(1000),
    eventstart: Yup.number().min(-90).max(90),
    eventend: Yup.number().min(-180).max(180),
    place: Yup.string(),
  })

export default eventAddValidation
