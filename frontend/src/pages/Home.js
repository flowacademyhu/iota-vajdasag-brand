import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersListAdmin from '../components/UsersList'
import FullProductList from '../components/FullProductList'
import EditProductPage from './EditProductPage'

const Home = () => {
  const { t } = useTranslation()

  return (
    <>
      <Route exact path="/home">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/home/users" exact>
        <UsersListAdmin />
      </Route>
      <Route path="/home/items" exact>
        <FullProductList />
      </Route>
      <Route path="/home/items/edit/:productId" exact>
        <EditProductPage />
      </Route>
    </>
  )
}

export default Home
