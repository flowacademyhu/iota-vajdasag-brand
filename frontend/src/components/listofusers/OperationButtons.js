import React from 'react'
import DeleteModalButton from './DeleteModalButton'
import ApproveButton from './ApproveButton'

const OperationButtons = ({ user }) => {
  return user.enabled ? (
    <DeleteModalButton userId={user.id} />
  ) : (
    <ApproveButton userId={user.id} />
  )
}

export default OperationButtons
