import React from 'react'
import { useTranslation } from 'react-i18next'
import OperationButtons from './OperationButtons'

const ListElement = ({ user }) => {
  const { t } = useTranslation()

  return (
    <tr>
      <td>{user.fullName}</td>
      <td>{user.email}</td>
      <td>
        {user.enabled ? t('userListElement.yes') : t('userListElement.no')}
      </td>
      <td>{user.registeredAt}</td>
      <td>
        <OperationButtons user={user} />
      </td>
    </tr>
  )
}

export default ListElement
