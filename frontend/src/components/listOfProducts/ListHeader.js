import React from 'react'
import { useTranslation } from 'react-i18next'

const ListHeader = () => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col">{t('product.name')}</th>
        <th scope="col">{t('product.address')}</th>
        <th scope="col">{t('product.city')}</th>
        <th scope="col">{t('product.category')}</th>

        <th scope="col">{t('product.subcategory')}</th>
        <th scope="col">{t('product.companyName')}</th>
        <th>{t('')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
