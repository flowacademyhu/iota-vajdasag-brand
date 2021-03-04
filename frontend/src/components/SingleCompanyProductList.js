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
//  const { owner } = location.state

  return (
    <div>
      {/*<h1 className="text-center">{owner}</h1>*/}
      <ProductTable list="single">
        {companysProducts?.map((product) => (
          <ListElement product={product} key={product.id} />
        ))}
      </ProductTable>
    </div>
  )
}

export default SingleCompanyProductList
