import React from 'react'
import { useParams } from 'react-router-dom'
import useProductsForCompany from './useProductsById'
import ListElement from './listOfProducts/ListElement'
import ProductTable from './ProductTable'
//import { useLocation } from 'react-router-dom'
import ListHeader from './listofusers/ListHeader'
import UserListElement from './listofusers/UserListElement'
import useUserById from '../hooks/useUserById'

const SingleCompanyProductList = () => {
  const { ownerId } = useParams()
  const companysProducts = useProductsForCompany(ownerId)
  const theUser = useUserById(ownerId)
  //let location = useLocation()
  //  const { owner } = location.state

  return (
    <div>
      {/*<h1 className="text-center">{owner}</h1>*/},
      <table className="table table-striped table-sm">
      <ListHeader />
      <tbody>
          {theUser?.map((user) => (
            <UserListElement
              user={user}
              key={user.id}
            />
          ))}
        </tbody>
      </table>
      <ProductTable list="single">
        {companysProducts?.map((product) => (
          <ListElement product={product} key={product.id} />
        ))}
      </ProductTable>
    </div>
  )
}

export default SingleCompanyProductList
