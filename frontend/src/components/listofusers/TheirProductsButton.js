import React from 'react'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'

const TheirProductsButton = ({ user }) => {
  const { t } = useTranslation()
  
  return (
    <div>
      {user.isApproved && (
        <NavLink to={`/super-admin/items/${user.id}/`}>
          <Button type="button" variant="success">
            {t('userListElement.products')}
          </Button>
        </NavLink>
      )}
    </div>
  )
}

export default TheirProductsButton
