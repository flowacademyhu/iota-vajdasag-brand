import React from 'react'
import DeleteProductButton from '../components/listofproducts/DeleteProductButton'
import { useTranslation } from 'react-i18next'
import AddProductButton from "../components/listofproducts/AddProductButton";

const SuperAdminProductsPage = () => {
  const { t } = useTranslation()
  return (
    <div>
      <h1>Just for testing new product button</h1>
      <AddProductButton title={t("product.newitem")}/>
      <h1>Just for testing delete button</h1>
      <DeleteProductButton />
    </div>
  )
}

export default SuperAdminProductsPage
