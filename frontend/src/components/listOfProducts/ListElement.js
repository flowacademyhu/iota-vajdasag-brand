import React from 'react'

const ListElement = ({ product }) => {
  return (
    <tr>
      <td>{product.title}</td>
      <td>{product.address}</td>
      <td>{product.city}</td>
      <td>{product.category}</td>
      <td>{product.subcategory}</td>
      <td>{product.name}</td>
    </tr>
  )
}

export default ListElement
