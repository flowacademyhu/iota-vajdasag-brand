import React from 'react'
import DeleteProductButton from './DeleteProductButton'

const ListElement = ({ product }) => {
  return (
    <div>
      {product ? (
    <tr>
      <td>{product.id}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>{product.category}</td>
      {/*
      <td>{product.subcategory}</td>
      
      <td>{product.companyName}</td>
      */}
      <td>
        <DeleteProductButton />
      </td>
    </tr>
    ) : (
      <div>betöltés</div>
    )}
    </div>
  )
}

export default ListElement
