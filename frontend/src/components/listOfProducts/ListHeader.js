import React from 'react'
import { useTranslation } from 'react-i18next'

const ListHeader = () => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col">{t('title')}</th>
        <th scope="col">{t('address')}</th>
        <th scope="col">{t('city')}</th>
        <th scope="col">{t('category')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
