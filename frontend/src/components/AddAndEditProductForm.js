import React from 'react'
import { Formik, Form, Field } from 'formik'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import SelectCategory from '../components/listOfProducts/SelectCategory'
import ResponseModal from '../components/modals/ResponseModal'
import { useTranslation } from 'react-i18next'

const AddAndEditProductForm = ({
  onClose,
  handleSubmit,
  initForm,
  validationEdit,
  showResponseModal,
  responseModalTitle,
  setShowResponseModal,
  title,
}) => {
  const { t } = useTranslation()

  return (
    <>
      <Formik
        initialValues={initForm()}
        validationSchema={validationEdit(t('registration.required'))}
        onSubmit={handleSubmit}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
            <h3 className="text-center">{title}</h3>
            <div className="my-2">
              <InputField
                label={t('editProduct.userId')}
                name="ownerId"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.name')}
                name="name"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.contactName')}
                name="contact"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.address')}
                name="address"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.city')}
                name="city"
                type="text"
              />
            </div>
            <div className="d-flex justify-content-around">
              <div className="my-2 text-center">
                <SelectCategory />
              </div>
              <div className="my-2 text-center">
                <div>
                  <label htmlFor="category">
                    {t('editProduct.selectSubCategory')}
                  </label>
                </div>
                <Field as="select" name="subcategory">
                  <option value="HONOURABLES">
                    {t('editProduct.honourables')}
                  </option>
                  <option value="FAMOUS_BUILDINGS">
                    {t('editProduct.famous_buildings')}
                  </option>
                  <option value="MUSEUMS">{t('editProduct.museums')}</option>
                </Field>
              </div>
            </div>
            <div className="my-2">
              <InputField label={t('editProduct.bio')} name="bio" type="text" />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.coordinateX')}
                name="coordinateX"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.coordinateY')}
                name="coordinateY"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.phone')}
                name="phone"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                label={t('editProduct.website')}
                name="website"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField label="Facebook" name="facebook" type="text" />
            </div>
            <div className="my-2">
              <InputField label="Instagram" name="instagram" type="text" />
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
        title={t(responseModalTitle)}
        onClose={onClose}
      />
    </>
  )
}

export default AddAndEditProductForm
