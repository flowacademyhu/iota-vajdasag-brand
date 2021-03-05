import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import useProductsForCompany from './useProductsById'
import ProductListElement from './listOfProducts/ProductListElement'
//import { useLocation } from 'react-router-dom'
import ListHeader from './listOfProducts/ListHeader'
import UserListElement from './listofusers/UserListElement'
import useUserById from '../hooks/useUserById'
import { getProductsByOwnerId } from "../communications/userApi";

import AddNewProductButton from './listOfProducts/AddNewProductButton'
import Searchbar from './Searchbar'
import { headerCellNamesItems } from '../sortHelpers'

const SingleCompanyProductList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const { ownerId } = useParams()
  const [products, setProducts] = useState([])
  const user = useUserById(ownerId)
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

  const fetchingProducts = async () => {
    return await getProductsByOwnerId(ownerId);
  }


  useEffect(() => {
    const response = fetchingProducts();
    setProducts(response)
  }, [])




  return (
    <div className="table-responsive">
      {/*<h1 className="text-center">{owner}</h1>*/}
      <AddNewProductButton />
      <Searchbar setSearchKeyword={setSearchKeyword} />
      {/* <table className="table table-striped table-sm">
        <ListHeader />
        <tbody>
            <UserListElement user={user}/>
          
        </tbody>
        <Searchbar setSearchKeyword={setSearchKeyword} />
      </table> */}

      {console.log('products', products)}
      <table className="table table-striped table-sm">
        <ListHeader
          onColumnClick={onColumnClick}
          sortKey={sortKey}
          isSortAscending={isSortAscending}
          headerCellNames={headerCellNamesItems}
        />
        <tbody>
          <tr>
            {//products.map(product => {
              <>
                <td>{products.name}</td>
                <td>{products.address}</td>
                <td>{products.city}</td>
                <td>{products.category}</td>
                <td>{products.owner}</td>
              </>
              // })
            }
          </tr>
          {/* {companysProducts?.map((product) => (
            <ProductListElement
              product={product}
              key={product.id}
              searchKeyword={searchKeyword}
            />
          ))} */}

        </tbody>
      </table>
    </div>
  )
}

export default SingleCompanyProductList
