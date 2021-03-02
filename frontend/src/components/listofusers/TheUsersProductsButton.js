import React from 'react'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'

const TheUsersProductsButton = ({ user }) => {
  const { t } = useTranslation()

  return (
        <NavLink to={`/super-admin/items/${user.id}/`}>
          <Button type="button" variant="success">
            {t('userListElement.products')}
          </Button>
        </NavLink>
  )
}

export default TheUsersProductsButton
