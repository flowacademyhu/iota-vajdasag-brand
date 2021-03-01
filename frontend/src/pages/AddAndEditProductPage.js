import React, { useState, useEffect } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form, Field } from 'formik'
import { useParams, useHistory, useLocation } from 'react-router-dom'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import SelectCategory from '../components/listofproducts/SelectCategory'
import validationEdit from '../validations/validationEdit'
import { updateProductData, fetchOneProduct, addProduct } from '../communications/userApi'
import ResponseModal from '../components/modals/ResponseModal'

const AddAndEditProductPage = () => {
  let location = useLocation();
  const type = location.pathname.includes("edit") ? "update" : "add"
  const [product, setProduct] = useState('')
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const [isEditSuccessful, setEditSuccessful] = useState(false)
  const { productId } = useParams()
  const { t } = useTranslation()
  let history = useHistory()

  useEffect(() => {
    if (type === "update") {
      const getProduct = async () => {
        const response = await fetchOneProduct(productId)
        setProduct(response.data)
      }
      getProduct()
    }
  }, [productId, type])

  const initForm = () => {
    if (type === "update") {
      return product
    } else {
      return {
        name: '',
        contact: '',
        address: '',
        city: '',
        coordinateX: '',
        coordinateY: '',
        phone: '',
        website: '',
        score: '',
        subcategory: ''
      }
    }
  }

  const handleSubmit = async (newProductValues) => {
    if (type === "update") {
      try {
        await updateProductData(product.id, newProductValues)
        setResponseModalTitle(t('editProduct.successfulEdition'))
        setShowResponseModal(true)
        setEditSuccessful(true)
      } catch (error) {
        setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
        setShowResponseModal(true)
        setEditSuccessful(false)
      }
    } else {
      newProductValues.score = 0
      await addProduct(newProductValues)
        .then((response) => {
          setResponseModalTitle(t('editProduct.successfulEdition'))
          setShowResponseModal(true)
          setEditSuccessful(true)
        }).catch((err) => {
          setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
          setShowResponseModal(true)
          setEditSuccessful(false)
        });
    }
  }

  const onClose = () => {
    setShowResponseModal(false)
    if (isEditSuccessful) {
      history.push('/super-admin/items')
    }
  }

  return (
    (type === 'add' || product) && (
      <div className="m-5">
        <Formik
          initialValues={initForm()}
          validationSchema={validationEdit(t('registration.required'))}
          onSubmit={handleSubmit}
        >
          <Form>
            <div className="d-flex flex-column justify-content-center align-content-center mx-auto">
              <h3 className="text-center">{type === "update" ? t('editProduct.title') : t('editProduct.addNewItemTitle')}</h3>
              <div className="my-2">
                {type === "add" && (
                  <InputField
                    label={t('editProduct.name')}
                    name="name"
                    type="text"
                  />
                )}
                {type === "add" && (
                  <InputField
                    label={t('editProduct.contactName')}
                    name="contact"
                    type="text"
                  />
                )}
                {type === "add" && (
                  <div className="my-2">
                    <InputField
                      name="score"
                      id="score"
                      placeholder={t('editProduct.name')}
                      type="text"
                      disabled
                      hidden
                    />
                  </div>
                )}
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
              <div className="my-2">
                <SelectCategory />
              </div>
              {type === "add" && (
                <>
                  <label htmlFor="category">{t('editProduct.selectCategory')}</label>
                  <Field as="select" name="subcategory">
                    <option value="HONOURABLES">{t('editProduct.HONOURABLES')}</option>
                    <option value="FAMOUS_BUILDINGS">{t('editProduct.FAMOUS_BUILDINGS')}</option>
                    <option value="MUSEUMS">{t('editProduct.MUSEUMS')}</option>
                  </Field>
                </>)}
              <div className="my-2">
                {type === "add" && (
                  <InputField
                    label={t('editProduct.bio')}
                    name="bio"
                    type="text"
                  />
                )}
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
      </div>
    )
  )
}

export default AddAndEditProductPage
