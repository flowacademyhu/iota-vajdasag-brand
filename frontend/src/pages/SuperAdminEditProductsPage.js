import React, { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import EditProductButton from '../components/listofproducts/EditProductButton'

const SuperAdminEditProductsPage = () => {
  const [products, setProducts] = useState([])

  const getProducts = async () => {
    const fetchedProducts = await fetchProducts()
    setProducts(fetchedProducts)
  }

  useEffect(() => {
    getProducts()
  }, [])

  return (
    <>
      {products?.map((product) => (
        <div key={product.id}>
          <h1>{product.city}</h1>
          <EditProductButton product={product} />
        </div>
      ))}
    </>
  )
}

export default SuperAdminEditProductsPage
