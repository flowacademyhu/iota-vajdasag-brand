import React from 'react'
import DeleteModalButton from './DeleteModalButton'
import ApproveButton from './ApproveButton'

const OperationButtons = ({
  user,
  sendRegistrationApproval,
  handleDelete,
  showConfirmDeletion,
  setShowConfirmDeletion,
}) => {
  return user.enabled ? (
    <DeleteModalButton
      userId={user.id}
      handleDelete={handleDelete}
      setShowConfirmDeletion={setShowConfirmDeletion}
      showConfirmDeletion={showConfirmDeletion}
    />
  ) : (
    <ApproveButton
      userId={user.id}
      sendRegistrationApproval={sendRegistrationApproval}
    />
  )
}

export default OperationButtons
