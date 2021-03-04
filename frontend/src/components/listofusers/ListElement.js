import React from 'react'
import { useTranslation } from 'react-i18next'
import OperationButtons from './OperationButtons'

const ListElement = ({
  user,
  sendRegistrationApproval,
  setShowConfirmDeletion,
  showConfirmDeletion,
  handleDelete,
}) => {
  const { t } = useTranslation()

  return (
    <tr>
      <td>{user.full_name}</td>
      <td>{user.email}</td>
      <td>
        {user.enabled ? t('userListElement.yes') : t('userListElement.no')}
      </td>
      <td>{user.id}</td>
      <td>{user.registeredAt}</td>
      <td>
        <OperationButtons
          user={user}
          handleDelete={handleDelete}
          sendRegistrationApproval={sendRegistrationApproval}
          setShowConfirmDeletion={setShowConfirmDeletion}
          showConfirmDeletion={showConfirmDeletion}
        />
      </td>
    </tr>
  )
}

export default ListElement
