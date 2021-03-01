import React from 'react'
import useProducts from './useProducts'
import ProductTable from './ProductTable'
import ListElement from './listOfProducts/ListElement'

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
