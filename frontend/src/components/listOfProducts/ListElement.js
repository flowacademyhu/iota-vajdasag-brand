import React from 'react'
import { useTranslation } from 'react-i18next'

const ListElement = ({ product }) => {
  const { t } = useTranslation()

  return (
    <tr>
      <td>{product.title}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>{t(product.category)}</td>
    </tr>
  )
}

export default ListElement
