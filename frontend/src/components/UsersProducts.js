import React from 'react'
import { GetProductsById } from './useProducts'
import ListElement from './listOfUsersProducts/ListElement'
import ListHeader from './listOfUsersProducts/ListHeader'

const UsersProducts = () => {
  const { usersProducts } = GetProductsById()
  
  return (
    <div>
      <h3> Itt a cég neve szerepelne </h3>
    <div className="table-responsive">
      <div className="col-9">
      {
  console.log(usersProducts)
    }
      <table className="table table-striped table-sm">
          <ListHeader />
          <tbody>
            {usersProducts?.map((product) => (
              <ListElement product={product} key={product.id} />
            ))}
          </tbody>
        </table>        
      </div>
    </div>
    </div>
  )
}

export default UsersProducts


