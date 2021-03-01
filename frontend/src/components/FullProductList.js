import React, { useState } from 'react'
import useProducts from '../hooks/useProducts'
import ProductTable from './ProductTable'
import ListElement from './listOfProducts/ListElement'
import Searchbar from './Searchbar'

const FullProductList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const { products } = useProducts(searchKeyword)

  return (
    <>
      <Searchbar
        searchKeyword={searchKeyword}
        setSearchKeyword={setSearchKeyword}
      />
      <ProductTable>
        {products?.map((product) => (
          <>
            <ListElement
              product={product}
              key={product.id}
              searchKeyword={searchKeyword}
            />
          </>
        ))}
      </ProductTable>
    </>
  )
}

export default FullProductList
