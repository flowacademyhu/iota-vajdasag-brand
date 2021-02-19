import React from 'react'
import useProducts from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'
//import { getProducts } from '../communications/productApi'

const ProductList = () => {
  const { listOfAllProducts } = useProducts()
  //const listOfAllProducts = getProducts();
  return (
    
    <div className="d-flex flex-row-reverse">
      <div className="col-9">
      {
  console.log(listOfAllProducts)
    }
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
