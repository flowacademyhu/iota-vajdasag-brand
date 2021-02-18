import React, { useState } from 'react'
import useProducts from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'

const ProductList = () => {
  const { listOfAllProducts } = useProducts()

  return (
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
        <table className="table table-striped">
          <ListHeader />
          <tbody>
            {listOfAllProducts?.map((product) => (
              <ListElement product={product} key={product.id} />
            ))}
          </tbody>
        </table>
        
      </div>
    </div>
  )
}

export default ProductList
