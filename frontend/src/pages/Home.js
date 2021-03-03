import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import FullProductList from '../components/FullProductList'
import EditProductPage from './EditProductPage'

const Home = () => {
  const { t } = useTranslation()

  return (
    <>
      <Route exact path="/home">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/users">
        <UsersList />
      </Route>
      <Route path="/items" exact>
        <FullProductList />
      </Route>
      <Route path="/items/edit/:productId" exact>
        <EditProductPage />
      </Route>
    </>
  )
}

export default Home
