import React, { useState, useEffect } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form } from 'formik'
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

  const renderHandler = () => {
    return type === "add" || product ? true : false
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
      await addProduct(newProductValues)
    }
  }

  const onClose = () => {
    setShowResponseModal(false)
    if (isEditSuccessful) {
      history.push('/super-admin/items')
    }
  }

  return (
    renderHandler() && (
      <div className="m-5">
        <Formik
          initialValues={product}
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
      </div>
    )
  )
}

export default AddAndEditProductPage
