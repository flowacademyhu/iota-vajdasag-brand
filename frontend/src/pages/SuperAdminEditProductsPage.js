import React, { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import EditProductButton from '../components/listofproducts/EditProductButton'
import DeleteProductButton from "../components/listofproducts/DeleteProductButton";

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
        !product.deletedAt &&
        <div key={product.id} className="border-bottom d-flex justify-content-around">
          <div className="col-1">
            <h5>{product.name}</h5>
          </div>
          <div className="col-1">
            <h5>{product.city}</h5>
          </div>
          <div className="col-1">
            <h5>{product.address}</h5>
          </div>
          <div className="col-1">
            <h5>{product.contact}</h5>
          </div>

          <EditProductButton productId={product.id} />
          <div>
            <DeleteProductButton productId={product.id} />
          </div>
        </div>
      ))}
    </>
  )
}

export default SuperAdminEditProductsPage
