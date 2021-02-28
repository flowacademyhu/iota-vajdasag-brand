import React from 'react'
import useProducts from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ProductTable from './ProductTable'

const FullProductList = () => {
  const { listOfAllProducts } = useProducts()

  return (
    <ProductTable>
      {listOfAllProducts?.map((product) => (
        <ListElement product={product} key={product.id} />
      ))}
    </ProductTable>
  )
}

export default FullProductList
