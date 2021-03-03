import React from 'react'
import ListHeader from './listOfProducts/ListHeader'

const ProductTable = ({children, list}) => {
  return (
    <div className="table-responsive">
      {console.log("list", list)}
      <table className="table table-striped table-sm">
        <ListHeader list={list}/>
        <tbody>{children}</tbody>
      </table>
    </div>
  )
}

export default ProductTable
