import React from 'react'
import { useTranslation } from 'react-i18next'
import { Button } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'

const TheUsersProductsButton = ({ user }) => {
  const { t } = useTranslation()
  //const owner = user.full_name

  return (
    <NavLink
      // to={{
      //   pathname: `/super-admin/user=${user.id}/items`,
      //   state: { owner: owner },
      // }}
      to={`/super-admin/user=${user.id}/items`}
    >
      <Button>{t('userListElement.products')}</Button>
    </NavLink>
  )
}

export default TheUsersProductsButton
