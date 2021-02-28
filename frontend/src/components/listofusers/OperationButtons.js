import React from 'react'
import DeleteModalButton from './DeleteModalButton'
import ApproveButton from './ApproveButton'

const OperationButtons = ({
  user,
  sendRegistrationApproval,
  deleteUser,
}) => {
  return user.enabled ? (
    <DeleteModalButton
      userId={user.id}
      deleteUser={deleteUser}
    />
  ) : (
    <ApproveButton
      userId={user.id}
      sendRegistrationApproval={sendRegistrationApproval}
    />
  )
}

export default OperationButtons
