import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form} from 'formik'
import { Button } from 'react-bootstrap'
import { useHistory } from 'react-router-dom'
import InputField from '../components/InputField'
import SelectCategory from '../components/listofproducts/SelectCategory'
import validationEdit from '../communications/validationEdit'
import { updateProductData, createProduct } from '../communications/userApi'
import ResponseModal from '../components/modals/ResponseModal'

const updateOldFieldsInItem = (toBeUpdated, product) => {
  Object.keys(product).forEach((key) => {
    return toBeUpdated[key] ? (product[key] = toBeUpdated[key]) : product[key]
  })
  return product
}

const AddAndEditProductPage = ({ product, type }) => {
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { t } = useTranslation()
  let history = useHistory()

  const handleSubmit = async (value) => {
    if (type === "update") {
      try {
        await updateProductData(updateOldFieldsInItem(value))
        setResponseModalTitle(t('editProduct.successfulEdition'))
        setShowResponseModal(true)
      } catch (error) {
        setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
        setShowResponseModal(true)
        history.push('/super-admin/products')
      }
    } else {
      try {
        await createProduct(value)
      } catch (error) {
        console.log(error)
      }
    }
  }

  const handleFormInit = (type) => {
    if (type === "update") {
      return {
        address: '',
        city: '',
        category: type === "update" ? product.category : '', // product.category,
        coordinateX: '',
        coordinateY: '',
        phone: '',
        website: '',
        facebook: '',
        instagram: '',
      }
    } else {
      return {
        name: '',
        address: '',
        city: '',
        category: type === "update" ? product.category : '', // product.category,
        bio: '',
        coordinateX: '',
        coordinateY: '',
        score: '',
        phone: '',
        website: '',
        facebook: '',
        instagram: '',
      }
    }
  }


  return (
    <div className="m-5">
      <Formik
        initialValues={{
          address: '',
          city: '',
          category: type === "update" ? product.category : '', // product.category,
          coordinateX: '',
          coordinateY: '',
          score:'50',
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
            <h3 className="text-center">{type === "update" ? t('editProduct.title') : t('editProduct.newAddingTitle')}</h3>
            {type === "create" &&
              <div className="my-2">
                <InputField
                  label={t('editProduct.name')}
                  name="name"
                  id="name"
                  placeholder={t('editProduct.name')}
                  type="text"
                />
              </div>
            }
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
            {type === "create" &&
              <div className="my-2">
                <InputField
                  label={t('editProduct.bio')}
                  name="bio"
                  id="bio"
                  placeholder={t('editProduct.name')}
                  type="textfield"
                />
              </div>
            }
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
            {type === "create" &&
              <div className="my-2">
                <label for="score">{t('editProduct.score')}</label>
                <input name="score" id="score" type="range"></input>
              </div>
            }
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
              {type === "update" ? t('editProduct.save') : t('editProduct.create')}
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

export default AddAndEditProductPage
