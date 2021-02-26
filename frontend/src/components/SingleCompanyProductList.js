import React from 'react'
import { GetProductsById } from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'

const SingleCompanyProductList = () => {
  const { companysProducts } = GetProductsById()
  return (
    <div>
      <h2
        style={{
          padding: '20px',
        }}
      >
        {companysProducts[0]?.owner}
      </h2>
      <div className="table-responsive">
        <div className="col-9">
          <table className="table table-striped table-sm">
            <ListHeader />
            <tbody>
              {companysProducts?.map((product) => (
                <ListElement product={product} key={product.id} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  )
}

export default SingleCompanyProductList
