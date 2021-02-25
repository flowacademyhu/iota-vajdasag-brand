import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import FullItemList from '../components/FullItemList'
import SingleCompanyItemList from '../components/SingleCompanyItemList'

const SuperAdmin = () => {
  const { t } = useTranslation()

  return (
    <>
      <Route exact path="/super-admin">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/super-admin/users">
        <UsersList />
      </Route>
      <Route path="/super-admin/items/{id}">
        <SingleCompanyItemList />
      </Route>
      <Route exact path="/super-admin/items">
        <FullItemList />
      </Route>
    </>
  )
}

export default SuperAdmin
