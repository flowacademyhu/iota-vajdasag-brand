import React, { useState } from 'react'
import { useTranslation } from 'react-i18next'
import useUsers from './useUsers'
import ResponseModal from '../components/modals/ResponseModal'
import ListElement from './listofusers/ListElement'
import Searchbar from './listofusers/Searchbar'
import ListHeader from './listofusers/ListHeader'

const UsersList = () => {
  const [searchKeyword, setSearchKeyword] = useState('')
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)
  const [showConfirmDeletion, setShowConfirmDeletion] = useState(false)
  const [showResponseModal, setShowResponseModal] = useState(false)
  const [responseModalTitle, setResponseModalTitle] = useState('')
  const { t } = useTranslation()
  const { users, sendRegistrationApproval, deleteUser } = useUsers(
    searchKeyword,
    sortKey,
    isSortAscending
  )

  const onColumnClick = (value) => {
    setAscendingSort(!isSortAscending)
    setSortKey(value)
  }

  const confirmModalHandler = (session) => {
    if (session) {
      setResponseModalTitle(t('userListElement.successful'))
    } else {
      setResponseModalTitle(t('userListElement.unsuccessful'))
    }
    setShowResponseModal(true)
  }

  const handleDelete = async (userId) => {
    setShowConfirmDeletion(false)
    try {
      await deleteUser(userId)
      confirmModalHandler(true)
    } catch (error) {
      confirmModalHandler(false)
    }
  }

  const onClose = () => {
    setShowResponseModal(false)
  }

  return (
    <div className="table-responsive">
      <Searchbar setSearchKeyword={setSearchKeyword} />
      <table className="table table-striped table-sm">
        <ListHeader
          onColumnClick={onColumnClick}
          isSortAscending={isSortAscending}
          sortKey={sortKey}
        />
        <tbody>
          {users?.map((user) => (
            <ListElement
              user={user}
              key={user.id}
              handleDelete={handleDelete}
              sendRegistrationApproval={sendRegistrationApproval}
              setShowConfirmDeletion={setShowConfirmDeletion}
              showConfirmDeletion={showConfirmDeletion}
            />
          ))}
        </tbody>
      </table>
      <ResponseModal
        onClose={onClose}
        showResponseModal={showResponseModal}
        title={responseModalTitle}
      />
    </div>
  )
}

export default UsersList
