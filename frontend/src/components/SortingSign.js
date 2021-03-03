import React from 'react'
import { SortDown, SortUpAlt } from 'react-bootstrap-icons'

const SortingSign = ({ value, isSortAscending, sortKey }) => {
  console.log(value, sortKey, isSortAscending)
  return (
    <>
      {sortKey === value ? isSortAscending ? <SortUpAlt /> : <SortDown /> : ''}
    </>
  )
}

export default SortingSign
