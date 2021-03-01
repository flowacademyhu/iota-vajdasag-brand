import React from 'react'
import DeleteProductButton from '../components/listofproducts/DeleteProductButton'
import AddNewProductButton from '../components/listofproducts/AddNewProductButton'

const SuperAdminProductsPage = () => {
  return (
    <div>
      <AddNewProductButton />
      <h1>Just for testing</h1>
      <DeleteProductButton />
    </div>
  )
}

export default SuperAdminProductsPage
