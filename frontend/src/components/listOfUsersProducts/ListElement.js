import React from 'react'
import { useTranslation } from 'react-i18next'

const ListElement = ({ product }) => {

  return (
    <tr>
      <td>{product.title}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>{product.category}</td>
      <td>{product.name}</td>
    </tr>
  )
}

export default ListElement
