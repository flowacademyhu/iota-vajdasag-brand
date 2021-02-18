import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'

const SuperAdmin = () => {
  const { t } = useTranslation()

  return (
    <div className="col-9">
      <Route exact path="/super-admin">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/super-admin/users">
        <UsersList />
      </Route>
    </div>
  )
}

export default SuperAdmin
