import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import UsersList from '../components/UsersList'
import AddAndEditProductPage from './AddAndEditProductPage'
import SingleCompanyProductList from '../components/SingleCompanyProductList'
import UsersListAdmin from '../components/UsersList'
import FullProductList from '../components/FullProductList'
import useLoggedInUser from "../hooks/useLoggedInUser";


const Home = () => {
  const { t } = useTranslation()
  const userRole = useLoggedInUser().role


  return (
    <>
      <Route exact path="/home">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/home/users" exact>
        <UsersListAdmin />
      </Route>
      <Route path="/home/items" exact>
        {userRole==="SuperAdmin" && <FullProductList />}
        {userRole==="CegAdmin" && <h1>Hello lista</h1>}
      </Route>
      <Route path="/home/items/edit/:productId" exact>
        <AddAndEditProductPage/>
      </Route>
      <Route path="/home/items/new-item" exact>
        <AddAndEditProductPage/>
      </Route>
      <Route exact path="/home/user=:ownerId/items">
        <SingleCompanyProductList />
      </Route>
    </>
  )
}

export default Home
