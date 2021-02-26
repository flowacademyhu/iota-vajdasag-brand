import React from 'react'
import { Link } from 'react-router-dom'
import { Button } from 'react-bootstrap'

const AddAndEditProductButton = () => {
  return (
    <Link to="/super-admin/products/create">
      <Button>title</Button>
    </Link>
  )
}
export default AddAndEditProductButton
