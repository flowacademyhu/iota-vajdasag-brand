import React, { useState } from 'react'
import useProducts from './useProducts'
import ListHeader from '../components/listOfProducts/ListHeader'
import ListElement from './listOfProducts/ListElement'

const FullProductList = () => {
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)
  const { listOfAllProducts } = useProducts(sortKey, isSortAscending)

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <ListHeader
          setSortKey={setSortKey}
          setAscendingSort={setAscendingSort}
          onColumnClick={onColumnClick}
        />
        {listOfAllProducts?.map((product) => (
          <ListElement product={product} key={product.id} />
        ))}
      </table>
    </div>
  )
}

export default FullProductList
