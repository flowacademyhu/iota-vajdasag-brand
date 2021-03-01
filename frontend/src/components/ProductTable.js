import React from 'react'
import ListHeader from './listOfProducts/ListHeader'

const ProductTable = (props) => {
  return (
    <div className="table-responsive">
      <table className="table table-striped table-sm">
        <ListHeader />
        <tbody>{props.children}</tbody>
      </table>
    </div>
  )
}

export default ProductTable
