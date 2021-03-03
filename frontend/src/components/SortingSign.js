import React from 'react'
import { SortDown, SortUpAlt } from 'react-bootstrap-icons'

const SortingSign = ({ value, isSortAscending, sortKey }) => {
  return (
    <>
      {sortKey === value ? isSortAscending ? <SortUpAlt /> : <SortDown /> : ''}
    </>
  )
}

export default SortingSign
