import React from 'react'
import useProductsForCompany from './useProductsByID'
import ListElement from './listOfProducts/ListElement'
import ProductTable from './ProductTable'

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
      <ProductTable>
        {companysProducts?.map((product) => (
          <ListElement product={product} key={product.id} />
        ))}
      </ProductTable>
    </div>
  )
}

export default SingleCompanyProductList
