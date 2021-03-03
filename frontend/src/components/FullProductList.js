import React from 'react'
import useProducts from './useProducts'
import ProductTable from './ProductTable'
import ListElement from './listOfProducts/ListElement'

const FullProductList = () => {
  const listOfAllProducts = useProducts()

  return (
    <ProductTable list="full">
      {listOfAllProducts?.map((product) => (
        <ListElement product={product} key={product.id} fullList />
      ))}
    </ProductTable>
  )
}

export default FullProductList
