import React, { useState } from 'react'
import useProducts from './useProducts'
import ListHeader from '../components/listOfProducts/ListHeader'
import React, { useState } from 'react'
import useProducts from '../hooks/useProducts'
import ProductTable from './ProductTable'
import ListElement from './listOfProducts/ListElement'
import Searchbar from './Searchbar'

const FullProductList = () => {
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)
  const { products } = useProducts(sortKey, isSortAscending)
  const [searchKeyword, setSearchKeyword] = useState('')
  const { products } = useProducts(searchKeyword)

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <ListHeader
          sortKey={sortKey}
          isSortAscending={isSortAscending}
          onColumnClick={onColumnClick}
        />
        <tbody>
          {products?.map((product) => (
            <ListElement product={product} key={product.id} />
          ))}
        </tbody>
      </table>
    </div>
    <>
      <Searchbar
        searchKeyword={searchKeyword}
        setSearchKeyword={setSearchKeyword}
      />
      <ProductTable>
        {products?.map((product) => (
          <ListElement
            product={product}
            key={product.id}
            searchKeyword={searchKeyword}
          />
        ))}
      </ProductTable>
    </>
  )
}

export default FullProductList
