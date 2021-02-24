import * as Yup from 'yup'

const validationEdit = (required) =>
  Yup.object().shape({
    address: Yup.string().required(required),
    coordinateY: Yup.number().min(-90).max(90).required(required),
    coordinateX: Yup.number().min(-180).max(180).required(required),
    phone: Yup.number().required(required),
    website: Yup.string().url(),
    facebook: Yup.string().url()
  })

export default validationEdit
