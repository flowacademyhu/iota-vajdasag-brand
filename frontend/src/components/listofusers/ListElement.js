import React from 'react'
import { useTranslation } from 'react-i18next'
import ApproveButton from './ApproveButton'

const ListElement = ({ user }) => {
  const { t } = useTranslation()

  return (
    <tr>
      <td>{user.name}</td>
      <td>{user.email}</td>
      <td>{user.isApproved ? t('accepted.yes') : t('accepted.no')}</td>
      <td>{user.dateOfRegistration}</td>
      <td>
        <ApproveButton user={user} />
      </td>
    </tr>
  )
}

export default ListElement
