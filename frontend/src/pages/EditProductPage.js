import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form } from 'formik'
import { Button } from 'react-bootstrap'
import { useHistory } from 'react-router-dom'
import InputField from '../components/InputField'
import SelectCategory from '../components/listofproducts/SelectCategory'
import validationEdit from '../communications/validationEdit'
import { updateProductData } from '../communications/userApi'
import ResponseModal from '../components/modals/ResponseModal'

const product = {
  id: '628a5a49-8f5e-448f-8adf-158455cd98f8',
  name: 'Alma',
  score: 34,
  bio: 'Konditorei',
  address: 'Múzeum körút 10.',
  city: 'Budapest',
  category: 'GASTRONOMY',
  coordinateX: '123',
  coordinateY: '456',
  phone: '123456789',
  website: 'www.bubu.hu',
  facebook: 'asdff',
  instagram: 'adgfgf',
  deletedAt: null,
}

const prepareProductForUpdate = (toBeUpdated) => {
  Object.keys(product).forEach((key) => {
    console.log(toBeUpdated[key], product[key])
    return toBeUpdated[key] === ''
      ? product[key]
      : (product[key] = toBeUpdated[key])
  })
  return toBeUpdated
}

const EditProductPage = () => {
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { t } = useTranslation()
  let history = useHistory()

  const handleSubmit = async (value) => {
    try {
      await updateProductData(value)
      setResponseModalTitle(t('editProduct.successfulEdition'))
      setShowResponseModal(true)
    } catch (error) {
      setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
      setShowResponseModal(true)
      // history.push('/super-admin/events')
    }
  }

  return (
    <div className="m-5">
      <Formik
        initialValues={{
          address: '',
          city: '',
          category: '', // product.category,
          coordinateX: '',
          coordinateY: '',
          phone: '',
          website: '',
          facebook: '',
          instagram: '',
        }}
        validationSchema={validationEdit(t('registration.required'))}
        onSubmit={handleSubmit}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
            <h3 className="text-center">{t('editProduct.title')}</h3>
            <div className="my-2">
              <InputField
                label={t('editProduct.address')}
                name="address"
                id="address"
                placeholder={t('editProduct.address')}
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.city')}
                name="city"
                id="city"
                placeholder={t('editProduct.city')}
                type="text"
              />
            </div>
            <div className="my-2">
              <SelectCategory />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.coordinateX')}
                name="coordinateX"
                id="coordinateX"
                placeholder={t('editProduct.coordinateX')}
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.coordinateY')}
                name="coordinateY"
                id="coordinateY"
                placeholder={t('editProduct.coordinateY')}
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.phone')}
                name="phone"
                id="phone"
                placeholder={t('editProduct.phone')}
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.website')}
                name="website"
                id="website"
                placeholder={t('editProduct.website')}
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label="Facebook"
                name="facebook"
                id="facebook"
                placeholder="Facebook"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label="Instagram"
                name="instagram"
                id="instagram"
                placeholder="Instagram"
                type="text"
              />
            </div>
            <Button variant="primary" type="submit" size="lg">
              {t('editProduct.save')}
            </Button>
          </div>
        </Form>
      </Formik>
      <ResponseModal
        setShowResponseModal={setShowResponseModal}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      />
    </div>
  )
}

export default EditProductPage
