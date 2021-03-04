import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import AddAndEditProductPage from './AddAndEditProductPage'
import FullProductList from '../components/FullProductList'
import FullEventList from '../components/FullEventList'

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
      <Route path="/super-admin/items/new-item">
        <AddAndEditProductPage />
      </Route>
      <Route path="/super-admin/items" exact>
        <FullProductList />
      </Route>
      <Route path="/super-admin/items/edit/:productId" exact>
        <AddAndEditProductPage />
      </Route>
      <Route path="/super-admin/events">
        <FullEventList/>
      </Route>
    </>
  )
}

export default SuperAdmin
