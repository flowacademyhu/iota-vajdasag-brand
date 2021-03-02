import React from 'react'
import useProducts from './useProducts'
import ProductTable from './ProductTable'
import ListElement from './listOfProducts/ListElement'
import AddNewProductButton from "./listofproducts/AddNewProductButton";
import EditProductButton from "./listOfProducts/EditProductButton";

const FullProductList = () => {
  const listOfAllProducts = useProducts()

  return (
    <>
      <AddNewProductButton />
      <EditProductButton/>
      <ProductTable>
        {listOfAllProducts?.map((product) => (
          <ListElement product={product} key={product.id} />
        ))}
      </ProductTable>
    </>
  )
}

export default FullProductList
