import React, { useState } from 'react'
import useProducts from '../hooks/useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'
import Searchbar from './Searchbar'

const FullProductList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const {
    products,
    sortKey,
    setSortKey,
    isSortAscending,
    setAscendingSort,
  } = useProducts(searchKeyword)

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  return (
    <div className="table-responsive">
      <Searchbar setSearchKeyword={setSearchKeyword} />
      <table className="table table-striped table-sm">
        <ListHeader
          onColumnClick={onColumnClick}
          sortKey={sortKey}
          isSortAscending={isSortAscending}
        />
        <tbody>
          {products?.map((product) => (
            <ListElement
              product={product}
              key={product.id}
              searchKeyword={searchKeyword}
            />
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default FullProductList
