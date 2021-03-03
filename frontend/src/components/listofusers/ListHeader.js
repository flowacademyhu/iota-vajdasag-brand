import React from 'react'
import { useTranslation } from 'react-i18next'
import SortingSign from '../SortingSign'

const ListHeader = ({ onColumnClick, isSortAscending, sortKey }) => {
  const { t } = useTranslation()

  return (
    <thead>
      <tr>
        <th scope="col" onClick={() => onColumnClick('full_name')}>
          {t('userListHeader.name')}
          <SortingSign
            value="full_name"
            sortKey={sortKey}
            isSortAscending={isSortAscending}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('email')}>
          {t('userListHeader.email')}
          <SortingSign
            value="email"
            sortKey={sortKey}
            isSortAscending={isSortAscending}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('enabled')}>
          {t('userListHeader.acceptedRegistration')}
          <SortingSign
            value="enabled"
            sortKey={sortKey}
            isSortAscending={isSortAscending}
          />
        </th>
        <th scope="col" onClick={() => onColumnClick('registeredAt')}>
          {t('userListHeader.dateOfRegistration')}
          <SortingSign
            value="registeredAt"
            sortKey={sortKey}
            isSortAscending={isSortAscending}
          />
        </th>
        <th> {t('userListHeader.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
