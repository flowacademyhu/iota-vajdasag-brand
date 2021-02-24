import React from 'react'
import { GetProducts } from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'

const FullProductList = () => {
  const { listOfAllProducts } = GetProducts()
  return (
    <div>
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

export default FullProductList
