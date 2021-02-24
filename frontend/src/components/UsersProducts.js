import React from 'react'
import { GetProductsById } from './useProducts'
import ListElement from './listOfProducts/ListElement'
import ListHeader from './listOfProducts/ListHeader'

const UsersProducts = () => {
  const { usersProducts } = GetProductsById()
  return (
    <div>
      <h2
        style={{
          padding: '20px',
        }}
      >
        {usersProducts[0]?.name}
      </h2>
      <div className="table-responsive">
        <div className="col-9">
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
