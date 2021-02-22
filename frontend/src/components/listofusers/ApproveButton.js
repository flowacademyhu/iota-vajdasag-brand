import React from 'react'
import { Button } from 'react-bootstrap'
import useUsers from '../useUsers'
import { useTranslation } from 'react-i18next'

const ApproveButton = ({userId}) => {
  const { sendRegistrationApproval } = useUsers()
  const { t } = useTranslation()

  return (
    <Button
      type="button"
      variant="success"
      onClick={() => sendRegistrationApproval(userId)}
    >
      {t('userListElement.approveRegistration')}
    </Button>
  )
}

export default ApproveButton
