import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import SuperAdminEditProductsPage from './SuperAdminEditProductsPage'
import UsersList from '../components/UsersList'
import SuperAdminProductsPage from '../pages/SuperAdminProductsPage'
import EditProductPage from './AddAndEditProductPage'

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
      <Route path="/super-admin/items" exact>
        <SuperAdminProductsPage />
        <SuperAdminEditProductsPage />
      </Route>
      <Route path="/super-admin/items/edit/:productId" exact>
        <EditProductPage />
      </Route>
    </>
  )
}

export default SuperAdmin
