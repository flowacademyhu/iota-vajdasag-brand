import React from 'react'
import DeleteProductButton from './DeleteProductButton'

const ListElement = ({ product }) => {
  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>
        {product.subcategory
          ? product.category + ': ' + product.subcategory
          : product.category}
      </td>
      <td>{product.owner}</td>
      <td>
        <DeleteProductButton />
      </td>
    </tr>
  )
}

export default ListElement
