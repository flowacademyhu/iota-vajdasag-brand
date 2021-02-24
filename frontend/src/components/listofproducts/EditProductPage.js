import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form } from 'formik'
import { Button } from 'react-bootstrap'
import InputField from '../InputField'
import SelectCategory from './SelectCategory'
import validationEdit from '../../communications/validationEdit'
import { updateProductData } from '../../communications/userApi'
import ResponseModal from '../modals/ResponseModal'

const EditProductPage = () => {
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { t } = useTranslation()

  const handleSubmit = async (value) => {
    try {
      await updateProductData(value)
      setResponseModalTitle(t('editProduct.successfulEdition'))
    } catch (error) {
      console.log(error)
      setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
    }
    setShowResponseModal(true)
  }

  return (
    <div className="m-5">
      <Formik
        initialValues={{
          //   id: '82efc7bc-547e-4800-b4b2-68b742bdd33e',
          //   name: 'Bubuka',
          //   score: 2,
          //   bio: 'Konditorei',
          address: '',
          city: '',
          category: '',
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
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.city')}
                name="city"
                id="city"
                placeholder={t('editProduct.city')}
                type="text"
              ></InputField>
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
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.coordinateY')}
                name="coordinateY"
                id="coordinateY"
                placeholder={t('editProduct.coordinateY')}
                type="text"
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.phone')}
                name="phone"
                id="phone"
                placeholder={t('editProduct.phone')}
                type="text"
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.website')}
                name="website"
                id="website"
                placeholder={t('editProduct.website')}
                type="text"
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label="Facebook"
                name="facebook"
                id="facebook"
                placeholder="Facebook"
                type="text"
              ></InputField>
            </div>
            <div className="my-2">
              <InputField
                label="Instagram"
                name="instagram"
                id="instagram"
                placeholder="Instagram"
                type="text"
              ></InputField>
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
