import React from 'react'
import DeleteProductButton from './DeleteProductButton'
import EditProductButton from './EditProductButton'

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
        <DeleteProductButton productId={product.id} />
        <EditProductButton productId={product.id} />
      </td>
    </tr>
  )
}

export default ListElement
