import React from 'react'
import { useTranslation } from 'react-i18next'

const ListHeader = ({list}) => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col">{t('product.name')}</th>
        <th scope="col">{t('product.address')}</th>
        <th scope="col">{t('product.city')}</th>
        <th scope="col">{t('product.category')}</th>
        {list==="full" && <th scope="col">{t('product.owner')}</th>}
        <th scope="col">{t('product.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
