import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route,Switch } from 'react-router-dom'
import EditProductButton from '../components/listofproducts/EditProductButton'
import UsersList from '../components/UsersList'
import SuperAdminProductsPage from '../pages/SuperAdminProductsPage'
import AddAndEditProductPage from './AddAndEditProductPage'

const product = {
  id: '628a5a49-8f5e-448f-8adf-158455cd98f8',
  name: 'Alma',
  score: 34,
  bio: 'Konditorei',
  address: 'Múzeum körút 10.',
  city: 'Budapest',
  category: 'GASTRONOMY',
  coordinateX: '123',
  coordinateY: '456',
  phone: '123456789',
  website: 'www.bubu.hu',
  facebook: 'fb',
  instagram: 'insta',
  deletedAt: null,
}

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
      <Route path="/super-admin/products" exact>
        <SuperAdminProductsPage />
        <EditProductButton productId={product.id} />
      </Route>
      <Route path="/super-admin/products/edit/:productId" exact>
        <AddAndEditProductPage product={product} type="update"/>
      </Route>
      <Route path="/super-admin/products/create" exact>
        <AddAndEditProductPage type="create"/>
      </Route>
    </>
  )
}

export default SuperAdmin
