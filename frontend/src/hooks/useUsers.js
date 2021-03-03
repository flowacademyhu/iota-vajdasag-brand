import { useState, useEffect, useCallback } from 'react'
import { normalize } from '../textHelpers'
import { sortColumn } from '../sortHelpers'
import {
  getUsers,
  sendApproval,
  deleteUserRegistration,
} from '../communications/userApi'

const useUsers = (searchKeyword, sortKey, isSortAscending) => {
  const [listOfAllUsers, setListOfAllUsers] = useState([])
  const [users, setUsers] = useState([])

  const fetchUsers = async () => {
    const fetchedUsers = await getUsers()
    setListOfAllUsers(fetchedUsers)
  }

  useEffect(() => {
    fetchUsers()
  }, [])

  const sendRegistrationApproval = useCallback(async (userId) => {
    const registrationStatus = await sendApproval(userId)

    if (registrationStatus.status === 200) {
      fetchUsers()
    }
  }, [])

  const deleteUser = useCallback(async (userId) => {
    await deleteUserRegistration(userId)
    fetchUsers()
  }, [])

  useEffect(() => {
    console.log('sortkey', sortKey, 'ascending', isSortAscending)
    setUsers(
      listOfAllUsers
        ?.sort((a, b) => sortColumn(a, b, sortKey, isSortAscending))
        .filter((user) =>
          normalize(user.full_name).includes(normalize(searchKeyword))
        )
    )
  }, [listOfAllUsers, searchKeyword, sortKey, isSortAscending])

  return { users, fetchUsers, sendRegistrationApproval, deleteUser }
}

export default useUsers
