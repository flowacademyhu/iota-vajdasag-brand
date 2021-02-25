import React from 'react'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const EditProductButton = ({ product }) => {
  const { t } = useTranslation()

  return (
    <Link to={`products/edit/${product.id}`}>
      <Button> {t('editProduct.editButton')}</Button>
    </Link>
  )
}

export default EditProductButton
