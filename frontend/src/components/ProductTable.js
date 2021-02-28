import React from 'react'
import ListHeader from './listOfProducts/ListHeader'

const ProductTable = (props) => {
  return (
    <div className="table-responsive">
      <div className="col-9">
        <table className="table table-striped table-sm">
          <ListHeader />
          <tbody>{props.children}</tbody>
        </table>
      </div>
    </div>
  )
}

export default ProductTable
