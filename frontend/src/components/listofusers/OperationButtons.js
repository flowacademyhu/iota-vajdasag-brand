import React from 'react'
import DeleteModalButton from './DeleteModalButton'
import ApproveButton from './ApproveButton'

const OperationButtons = ({ user }) => {
  return user.isApproved ? (
    <DeleteModalButton userId={user.id} />
  ) : (
    <ApproveButton userId={user.id} />
  )
}

export default OperationButtons
