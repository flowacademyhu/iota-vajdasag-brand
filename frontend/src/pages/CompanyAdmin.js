import React from 'react'
import { useTranslation } from 'react-i18next'
import { Route } from 'react-router-dom'
import FullProductList from '../components/FullProductList'

const CompanyAdmin = () => {
  const { t } = useTranslation()

  return (
    <>
      <Route exact path="/company-admin">
        <h1>{t('welcome')}</h1>
      </Route>
      <Route path="/company-admin/products">
        <FullProductList />
      </Route>
    </>
  )
}

export default CompanyAdmin