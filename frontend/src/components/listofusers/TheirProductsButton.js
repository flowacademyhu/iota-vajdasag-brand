import React from 'react'
import useUsers from '../useUsers'
import useProducts from '../useProducts'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
{/*
const ApproveButton = ({ user }) => {
  const { sendRegistrationApproval } = useUsers()
  const { t } = useTranslation()

  return !user.isApproved ? (
    <Button
      type="button"
      variant="success"
      onClick={() => sendRegistrationApproval(user.id)}
    >
      {t('approveRegistration')}
    </Button>
  ) : (
    ''
  )
}

export default ApproveButton


const TheirProducts = ({user, })
*/}

const TheirProductsButton = ({user, products}) => {
  const 
  return (
    <Button
    type="button"
      variant="success"
      onClick={() => (user.id)}
      >
      {t('products')}
      </Button>
  ) : (
    ''
  )
}