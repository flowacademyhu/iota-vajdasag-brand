import React from 'react'
//import { useParams } from 'react-router-dom'
import useProductsForCompany from './useProductsByID'
//import useUsers from './useUsers'
import ListElement from './listOfProducts/ListElement'
import ProductTable from './ProductTable'

const SingleCompanyProductList = () => {
  //const { userId } = useParams()
  const { companysProducts } = useProductsForCompany(1)
  //const { users } = useUsers(1)

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
