import React from 'react'
import useProductsForCompany from './useProductsByID'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'

const SingleCompanyProductList = () => {
  const { companysProducts } = useProductsForCompany(1)
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
