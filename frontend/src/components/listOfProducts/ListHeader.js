import React from 'react'
import { useTranslation } from 'react-i18next'
import SortingSign from '../SortingSign'

const ListHeader = ({ onColumnClick, isSortAscending, sortKey }) => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick('name')}>
          {t('product.name')}
          <SortingSign
            value="name"
            isSortAscending={isSortAscending}
            sortKey={sortKey}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('address')}>
          {t('product.address')}
          <SortingSign
            value="address"
            isSortAscending={isSortAscending}
            sortKey={sortKey}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('city')}>
          {t('product.city')}
          <SortingSign
            value="city"
            isSortAscending={isSortAscending}
            sortKey={sortKey}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('category')}>
          {t('product.category')}
          <SortingSign
            value="category"
            isSortAscending={isSortAscending}
            sortKey={sortKey}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('ownerName')}>
          {t('product.owner')}
          <SortingSign
            value="ownerName"
            isSortAscending={isSortAscending}
            sortKey={sortKey}
          />
        </th>
        <th scope="col">{t('product.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
