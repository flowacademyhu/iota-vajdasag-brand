import React from 'react'
import { useTranslation } from 'react-i18next'
import { SortDown, SortUpAlt } from 'react-bootstrap-icons'

const ListHeader = ({ onColumnClick, isSortAscending, sortKey }) => {
  const { t } = useTranslation()

  const SortingSign = (value) => {
    if (sortKey === value) {
      return isSortAscending ? <SortUpAlt /> : <SortDown />
    }
  }

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick('name')}>
          {t('product.name')}
          {SortingSign('name')}
        </th>
        <th scope="col" onClick={() => onColumnClick('address')}>
          {t('product.address')}
          {SortingSign('address')}
        </th>
        <th scope="col" onClick={() => onColumnClick('city')}>
          {t('product.city')}
          {SortingSign('city')}
        </th>
        <th scope="col" onClick={() => onColumnClick('category')}>
          {t('product.category')}
          {SortingSign('category')}
        </th>
        <th scope="col" onClick={() => onColumnClick('ownerName')}>
          {t('product.owner')}
          {SortingSign('ownerName')}
        </th>
        <th scope="col">{t('product.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
