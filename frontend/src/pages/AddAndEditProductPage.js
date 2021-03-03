import React, { useState, useEffect } from 'react'
import { useTranslation } from 'react-i18next'
import { useParams, useHistory, useLocation } from 'react-router-dom'
import validationEdit from '../validations/validationEdit'
import { updateProductData, fetchOneProduct, addProduct } from '../communications/userApi'
import AddAndEditProductForm from "../components/AddAndEditProductForm";

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
        console.log('product', product)
        console.log('newProductValues', newProductValues)
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
      try {
        newProductValues.score = 0
        console.log('product', product)
        console.log('newProductValues', newProductValues)
        await addProduct(newProductValues)
        setResponseModalTitle(t('editProduct.successfulEdition'))
        setShowResponseModal(true)
        setEditSuccessful(true)
      } catch (error) {
        setResponseModalTitle(t('editProduct.unsuccessfulEdition'))
        setShowResponseModal(true)
        setEditSuccessful(false)
      }
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
        <AddAndEditProductForm
          onClose={onClose}
          handleSubmit={handleSubmit}
          initForm={initForm}
          validationEdit={validationEdit}
          showResponseModal={showResponseModal}
          responseModalTitle={responseModalTitle}
          setShowResponseModal={setShowResponseModal}
          title={type === "update" ? t('editProduct.title') : t('editProduct.addNewItemTitle')}
        />
      </div>
    )
  )
}

export default AddAndEditProductPage
