import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import useProductsForCompany from './useProductsById'
import ProductListElement from './listOfProducts/ProductListElement'
//import { useLocation } from 'react-router-dom'
import ListHeader from './listofusers/ListHeader'
import UserListElement from './listofusers/UserListElement'
import useUserById from '../hooks/useUserById'

import AddNewProductButton from './listOfProducts/AddNewProductButton'
import Searchbar from './Searchbar'
import { headerCellNamesItems } from '../sortHelpers'

const SingleCompanyProductList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const { ownerId } = useParams()
  const theUser = useUserById(ownerId)
  //let location = useLocation()
  //  const { owner } = location.state
  const {
    companysProducts,
    sortKey,
    setSortKey,
    isSortAscending,
    setAscendingSort,
  } = useProductsForCompany(ownerId, searchKeyword)

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  return (
    <div className="table-responsive">
      {/*<h1 className="text-center">{owner}</h1>*/}
      <AddNewProductButton />
      <Searchbar setSearchKeyword={setSearchKeyword} />
      <table className="table table-striped table-sm">
        <ListHeader />
        <tbody>
          {theUser?.map((user) => (
            <UserListElement user={user} key={user.id} />
          ))}
        </tbody>
        <Searchbar setSearchKeyword={setSearchKeyword} />
      </table>

      <table className="table table-striped table-sm">
        <ListHeader
          onColumnClick={onColumnClick}
          sortKey={sortKey}
          isSortAscending={isSortAscending}
          headerCellNames={headerCellNamesItems}
        />
        <tbody>
          {companysProducts?.map((product) => (
            <ProductListElement
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

export default SingleCompanyProductList
