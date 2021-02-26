import React, { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import EditProductButton from '../components/listofproducts/EditProductButton'

const SuperAdminEditProductsPage = () => {
  const [products, setProducts] = useState([])

  const getProducts = async () => {
    const response = await fetchProducts()
    setProducts(response.data)
  }

  useEffect(() => {
    getProducts()
  }, [])

  return (
    <>
      {products?.map((product) => (
        <div>
          <h1>{product.name}</h1>
          <EditProductButton product={product} key={product.id} />
        </div>
      ))}
    </>
  )
}

export default SuperAdminEditProductsPage
