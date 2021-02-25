import React from 'react'
import DeleteProductButton from '../components/listofproducts/DeleteProductButton'
import UpdateButton from "../components/listofproducts/UpdateButton";
import { useTranslation } from 'react-i18next'


const SuperAdminProductsPage = () => {
  const { t } = useTranslation()
  return (
    <div>
      <h1>Just for testing delete button</h1>
      <DeleteProductButton />
      <h1>Just for testing update button</h1>
      <UpdateButton text={t("products.updatebutton")} productid={1}/>
    </div>
  )
}

export default SuperAdminProductsPage
