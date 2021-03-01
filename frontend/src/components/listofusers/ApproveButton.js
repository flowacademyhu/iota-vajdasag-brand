import React from 'react'
import { Button } from 'react-bootstrap'
import { useTranslation } from 'react-i18next'

const ApproveButton = ({ userId, sendRegistrationApproval }) => {
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
