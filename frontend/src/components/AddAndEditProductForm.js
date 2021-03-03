import React, { useState } from 'react'
import { Formik, Form, Field } from 'formik'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import SelectCategory from '../components/listOfProducts/SelectCategory'
import ResponseModal from '../components/modals/ResponseModal'
import { useTranslation } from 'react-i18next'
import { getCurrentLanguage } from '../i18n'

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
  const [currentLanguage, setCurrentLanguage] = useState(getCurrentLanguage())

  return (
    <>
      <h3 className="text-center mb-5">{title}</h3>
      <div className="row mb-3">
        <label className="col-auto col-form-label" htmlFor="language">
          {t('editProduct.language')}:
        </label>
        <div className="col-sm-3">
          <select
            className="form-select "
            name="language"
            id="language"
            value={currentLanguage}
            onChange={(event) => setCurrentLanguage(event.target.value)}
          >
            <option value="hu">{t("editProduct.hu")}</option>
            <option value="sr">{t("editProduct.sr")}</option>
            <option value="en">{t("editProduct.en")}</option>
          </select>
        </div>
      </div>
      <Formik
        initialValues={initForm()}
        validationSchema={validationEdit(t('registration.required'))}
        onSubmit={handleSubmit}
      >
        <Form>
          <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
            <div className="my-2">
              <InputField
                label={t('editProduct.userId')}
                name="ownerId"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField
                hidden={currentLanguage !== 'en'}
                label={t('editProduct.name')}
                name="languages.en.name"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'hu'}
                label={t('editProduct.name')}
                name="languages.hu.name"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'sr'}
                label={t('editProduct.name')}
                name="languages.sr.name"
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
            <div className="my-2 row">
              <div className="col">
                <SelectCategory />
                </div>
               
              <div className="col">
                <label htmlFor="category">
                  {t('editProduct.selectSubCategory')}
                </label>
              
              <Field className="form-select" as="select" name="subcategory">
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
              <InputField
                hidden={currentLanguage !== 'en'}
                label={t('editProduct.bio')}
                name="languages.en.bio"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'hu'}
                label={t('editProduct.bio')}
                name="languages.hu.bio"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'sr'}
                label={t('editProduct.bio')}
                name="languages.sr.bio"
                type="text"
              />
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
                hidden={currentLanguage !== 'en'}
                label={t('editProduct.website')}
                name="languages.en.website"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'hu'}
                label={t('editProduct.website')}
                name="languages.hu.website"
                type="text"
              />
              <InputField
                hidden={currentLanguage !== 'sr'}
                label={t('editProduct.website')}
                name="languages.sr.website"
                type="text"
              />
            </div>
            <div className="my-2">
              <InputField label="Facebook" name="facebook" type="text" />
            </div>
            <div className="my-2">
              <InputField label="Instagram" name="instagram" type="text" />
            </div>
            <Button className="mt-3" variant="primary" type="submit" size="lg">
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
