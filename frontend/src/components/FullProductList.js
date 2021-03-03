import React, { useState } from 'react'
import { SortDown, SortUpAlt } from 'react-bootstrap-icons'
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

  const SortingSign = (value) => {
    if (sortKey === value) {
      return isSortAscending ? <SortUpAlt /> : <SortDown />
    }
  }

  return (
    <div className="table-responsive">
      <Searchbar setSearchKeyword={setSearchKeyword} />
      <table className="table table-striped table-sm">
        <ListHeader onColumnClick={onColumnClick} SortingSign={SortingSign} />
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
