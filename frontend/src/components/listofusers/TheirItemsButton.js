import React from 'react'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'

const TheirItemsButton = ({ user }) => {
  const { t } = useTranslation()
  return user.isApproved ? (
    <NavLink to={`/super-admin/items/${user.id}/`}>
      <Button type="button" variant="success">
        {t('userListElement.items')}
      </Button>
    </NavLink>
  ) : (
    ''
  )
}

export default TheirItemsButton
