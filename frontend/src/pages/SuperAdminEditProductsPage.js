import React, { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import EditProductButton from '../components/listofproducts/EditProductButton'

const SuperAdminEditProductsPage = () => {
  const [product, setProduct] = useState('')

  const getProducts = async () => {
    const response = await fetchProducts()
    console.log(response.data.user)
    setProduct(response.data.user)
  }

  useEffect(() => {
    getProducts()
  }, [])

  return (
    <>
      <h1> {product.name}</h1>
      <EditProductButton product={product} />
    </>
  )
}

export default SuperAdminEditProductsPage
