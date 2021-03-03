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
        <th scope="col" onClick={() => onColumnClick('full_name')}>
          {t('userListHeader.name')}
          {SortingSign('full_name')}
        </th>
        <th scope="col" onClick={() => onColumnClick('email')}>
          {t('userListHeader.email')}
          {SortingSign('email')}
        </th>
        <th scope="col" onClick={() => onColumnClick('isApproved')}>
          {t('userListHeader.acceptedRegistration')}
          {SortingSign('isApproved')}
        </th>
        <th scope="col" onClick={() => onColumnClick('registeredAt')}>
          {t('userListHeader.dateOfRegistration')}
          {SortingSign('registeredAt')}
        </th>
        <th> {t('userListHeader.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
