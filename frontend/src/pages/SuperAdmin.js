import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import FullProductList from '../components/FullProductList'
import EditProductPage from './EditProductPage'
import SingleCompanyProductList from '../components/SingleCompanyProductList'

const SuperAdmin = () => {
  const { t } = useTranslation()

  return (
    <>
      <Route exact path="/super-admin">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route exact path="/super-admin/users">
        <UsersList />
      </Route>
      <Route exact path="/super-admin/user=:ownerId/items">
        <SingleCompanyProductList/>
      </Route>
      <Route  path="/super-admin/items" >
        <FullProductList />
      </Route>
      <Route path="/super-admin/items/edit/:productId" exact>
        <EditProductPage />
      </Route>
    </>
  )
}

export default SuperAdmin
