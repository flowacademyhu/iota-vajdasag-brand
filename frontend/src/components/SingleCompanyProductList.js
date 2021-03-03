import React from 'react'
import { useParams } from 'react-router-dom'
import useProductsForCompany from './useProductsById'
import ListElement from './listOfProducts/ListElement'
import ProductTable from './ProductTable'
import { useLocation } from 'react-router-dom'

const SingleCompanyProductList = () => {
  const { ownerId } = useParams()
  const companysProducts = useProductsForCompany(ownerId)
  let location = useLocation()
  const {owner} = location.state

  return (
    <div>
      <h2
        style={{
          padding: '20px',
        }}
      >
        {owner}
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
