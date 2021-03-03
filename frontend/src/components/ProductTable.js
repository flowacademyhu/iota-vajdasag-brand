import React from 'react'
import ListHeader from './listOfProducts/ListHeader'

const ProductTable = ({ children, list }) => {
  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <ListHeader list={list} />
        <tbody>{children}</tbody>
      </table>
    </div>
  )
}

export default ProductTable
