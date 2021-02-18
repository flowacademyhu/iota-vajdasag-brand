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
          {t('accepted.name')}
          {SortingSign('name')}
        </th>
        <th scope="col" onClick={() => onColumnClick('email')}>
          {t('accepted.email')}
          {SortingSign('email')}
        </th>
        <th scope="col" onClick={() => onColumnClick('isApproved')}>
          {t('accepted.acceptedRegistration')}
          {SortingSign('isApproved')}
        </th>
        <th scope="col" onClick={() => onColumnClick('dateOfRegistration')}>
          {t('accepted.dateOfRegistration')}
          {SortingSign('dateOfRegistration')}
        </th>
        <th> {t('accepted.actions')}</th>
      </tr>
    </thead>
  )
}

export default ListHeader
