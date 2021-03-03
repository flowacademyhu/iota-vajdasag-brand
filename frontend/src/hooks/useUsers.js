import { useState, useEffect, useCallback } from 'react'
import { normalize } from '../components/frequentlyUsedFunctionsAndVariables'
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

  const sortColumn = useCallback(
    (a, b) => {
      if (sortKey === '') {
        return 0
      }

      if (isSortAscending) {
        return a[sortKey] > b[sortKey] ? 1 : -1
      } else {
        return a[sortKey] < b[sortKey] ? 1 : -1
      }
    },
    [sortKey, isSortAscending]
  )

  useEffect(() => {
    setUsers(
      listOfAllUsers
        ?.sort((a, b) => sortColumn(a, b))
        .filter((user) =>
          normalize(user.full_name).includes(
            normalize(searchKeyword)
          )
        )
    )
  }, [listOfAllUsers, searchKeyword, sortKey, isSortAscending, sortColumn])

  return { users, fetchUsers, sendRegistrationApproval, deleteUser }
}

export default useUsers
