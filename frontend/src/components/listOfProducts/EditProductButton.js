import React from 'react'
import { useTranslation } from 'react-i18next'
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const EditProductButton = ({ productId }) => {
  const { t } = useTranslation()

  return (
    <Link to={`items/edit/${productId}`}>
      <Button className="mx-2"> {t('editProduct.editButton')}</Button>
    </Link>
  )
}

export default EditProductButton
