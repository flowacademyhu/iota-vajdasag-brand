import React, { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import EditProductButton from '../components/listOfProducts/EditProductButton'

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
          <EditProductButton productId={product.id} />
        </div>
      ))}
    </>
  )
}

export default SuperAdminEditProductsPage
