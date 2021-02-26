import React, { useState, useEffect } from 'react'
import { useTranslation } from 'react-i18next'
import { Formik, Form } from 'formik'
import { useParams, useHistory } from 'react-router-dom'
import { Button } from 'react-bootstrap'
import InputField from '../components/InputField'
import SelectCategory from '../components/listofproducts/SelectCategory'
import validationEdit from '../communications/validationEdit'
import { updateProductData, fetchOneProduct } from '../communications/userApi'
import EditResponseModal from '../components/modals/EditResponseModal'

const updateOldFieldsInItem = (newProductValues, product) => {
  Object.keys(product).forEach((key) => {
    return newProductValues[key]
      ? (product[key] = newProductValues[key])
      : product[key]
  })
  return product
}

const EditProductPage = () => {
  const [product, setProduct] = useState('')
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const [isEditSuccessful, setEditSuccessful] = useState(false)
  const { productId } = useParams()
  const { t } = useTranslation()
  let history = useHistory()

  // These can be later organized into a useProducts hook when product list is ready
  useEffect(() => {
    const getProduct = async () => {
      const response = await fetchOneProduct(productId)
      setProduct(response.data)
    }
    getProduct()
  }, [productId])

  const handleSubmit = async (newProductValues) => {
    const updatedProduct = updateOldFieldsInItem(newProductValues, product)
    try {
      await updateProductData(product.id, updatedProduct)
      setResponseModalTitle(t('editProduct.successfulEdition'))
      setShowResponseModal(true)
      setEditSuccessful(true)
    } catch (error) {
      setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
      setShowResponseModal(true)
      setEditSuccessful(false)
    }
  }

  const onClose = () => {
    setShowResponseModal(false)
    if (isEditSuccessful) {
      history.push('/super-admin/products')
    }
  }

  return (
    <div className="m-5">
      <Formik
        initialValues={{
          address: '',
          city: '',
          category: product.category,
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
      <EditResponseModal
        setShowResponseModal={setShowResponseModal}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
        onClose={onClose}
      />
    </div>
  )
}

export default EditProductPage
