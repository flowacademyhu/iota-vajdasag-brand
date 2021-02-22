import React from 'react'
import useUsers from '../useUsers'
import useProducts from '../useProducts'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'

const TheirProductsButton = ({ user, products }) => {
  const { t } = useTranslation()
  return user.isApproved ? (
    <NavLink to={`/super-admin/users/${user.id}/products`}>
      <Button type="button" variant="success">
        {t('userListElement.products')}
      </Button>
    </NavLink>
  ) : (
    ''
  )
}

export default TheirProductsButton
