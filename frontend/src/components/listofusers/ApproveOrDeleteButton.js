import React from 'react'
import useUsers from '../useUsers'
import { useTranslation } from 'react-i18next'
import DeleteModalButton from "./DeleteModalButton"
import { Button } from 'react-bootstrap'

const ApproveOrDeleteButton = ({ user }) => {
  const { sendRegistrationApproval } = useUsers()
  const { t } = useTranslation()

  return !user.isApproved ? (
    <Button
      type="button"
      variant="success"
      onClick={() => sendRegistrationApproval(user.id)}
    >
      {t('userListElement.approveRegistration')}
    </Button>
  ) : (
    <DeleteModalButton/>
  )
}

export default ApproveOrDeleteButton
