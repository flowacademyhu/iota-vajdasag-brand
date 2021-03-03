import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import FullProductList from '../components/FullProductList'
import EditProductPage from './EditProductPage'
import EventList from './EventList'

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
        <FullProductList />
      </Route>
      <Route path="/super-admin/items/edit/:productId" exact>
        <EditProductPage />
      </Route>
      <Route path="/super-admin/events" exact>
        <EventList></EventList>
      </Route>
    </>
  )
}

export default SuperAdmin
