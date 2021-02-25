import React from 'react'
import { useTranslation } from 'react-i18next'

const ListHeader = () => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col">{t('item.title')}</th>
        <th scope="col">{t('item.address')}</th>
        <th scope="col">{t('item.city')}</th>
        <th scope="col">{t('item.category')}</th>
        <th scope="col">{t('item.subcategory')}</th>
        <th scope="col">{t('item.name')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
