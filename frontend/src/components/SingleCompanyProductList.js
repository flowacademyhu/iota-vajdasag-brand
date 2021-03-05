import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
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


  const fetchingProducts = async () => {
    const response = await getProductsByOwnerId(ownerId);
    setProducts(response.data)    
    console.log('response', response)
  }


  useEffect(() => {
      fetchingProducts()    

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
      <table className="table table-striped table-sm">
        <tbody>
          {products? 
              products.map((product) => (
                <>
                <td>{product.name}</td>
                <td>{product.address}</td>
                <td>{product.category}</td>
                </>
              ))
              
        :(<h1>kjfbhve</h1>)
            }
      {/* {companysProducts?.map((product) => (
            <ProductListElement
              product={product}
              key={product.id}
              searchKeyword={searchKeyword}
            />
          ))} */}

        </tbody>
      </table >
    </div >
  )
}

export default SingleCompanyProductList
