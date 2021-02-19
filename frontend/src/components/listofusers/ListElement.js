import React from 'react'
import { useTranslation } from 'react-i18next'
import ApproveOrDeleteButton from './ApproveOrDeleteButton'

const ListElement = ({ user }) => {
  const { t } = useTranslation()

  return (
    <tr>
      <td>{user.name}</td>
      <td>{user.email}</td>
      <td>
        {user.isApproved ? t('userListElement.yes') : t('userListElement.no')}
      </td>
      <td>{user.dateOfRegistration}</td>
      <td>
        <ApproveOrDeleteButton user={user} />
      </td>
    </tr>
  )
}

export default ListElement
