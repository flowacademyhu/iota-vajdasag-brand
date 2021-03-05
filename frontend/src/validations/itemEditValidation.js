import * as Yup from 'yup'

const itemEditValidation = () =>
  Yup.object().shape({
    address: Yup.string(),
    city: Yup.string(),
    coordinateY: Yup.number().min(-90).max(90),
    coordinateX: Yup.number().min(-180).max(180),
    phone: Yup.number(),
    website: Yup.string().url(),
    facebook: Yup.string().url(),
    instagram: Yup.string().url(),
  })

export default itemEditValidation
