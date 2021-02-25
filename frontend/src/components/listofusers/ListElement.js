import React from 'react'
import { useTranslation } from 'react-i18next'
import TheirItemsButton from './TheirItemsButton'
import OperationButtons from './OperationButtons'

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
        <OperationButtons user={user} />
      </td>
      <td>
        <TheirItemsButton user={user} />
      </td>
    </tr>
  )
}

export default ListElement
