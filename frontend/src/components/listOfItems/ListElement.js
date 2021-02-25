import React from 'react'

const ListElement = ({ item }) => {
  return (
    <tr>
      <td>{item.title}</td>
      <td>{item.address}</td>
      <td>{item.city}</td>
      <td>{item.category}</td>
      <td>{item.subcategory}</td>
      <td>{item.name}</td>
    </tr>
  )
}

export default ListElement
