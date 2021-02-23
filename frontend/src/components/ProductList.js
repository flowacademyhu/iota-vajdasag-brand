import React from 'react'
import { GetProducts } from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'


const ProductList = () => {
  const { listOfAllProducts } = GetProducts()
  return (
    <div>
      <h3> A létező összes termék </h3>
    <div className="table-responsive">
      <div className="col-9">
      
      <table className="table table-striped table-sm">
          <ListHeader />
          <tbody>
            {listOfAllProducts?.map((product) => (
              <ListElement product={product} key={product.id} />
            ))}
          </tbody>
        </table>        
      </div>
    </div>
    </div>
  )
}

export default ProductList


