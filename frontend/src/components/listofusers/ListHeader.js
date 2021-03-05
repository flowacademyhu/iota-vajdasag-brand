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
          {t('userListHeader.name')}
          {SortingSign('name')}
        </th>
        <th scope="col" onClick={() => onColumnClick('email')}>
          {t('userListHeader.email')}
          {SortingSign('email')}
        </th>
        <th scope="col" onClick={() => onColumnClick('isApproved')}>
          {t('userListHeader.acceptedRegistration')}
          {SortingSign('isApproved')}
        </th>
        <th scope="col" onClick={() => onColumnClick('dateOfRegistration')}>
          {t('userListHeader.dateOfRegistration')}
          {SortingSign('dateOfRegistration')}
        </th>
        <th> {t('userListHeader.actions')}</th>
        <th />
      </tr>
    </thead>
  )
}

export default ListHeader
