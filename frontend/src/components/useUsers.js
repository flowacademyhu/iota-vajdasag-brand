import { useState, useEffect, useCallback } from 'react'
import {
  getUsers,
  sendApproval,
  deleteUserRegistration,
} from '../communications/userApi'

/*
 * Removes all accents from words and makes them uppercase.
 **/
const makeWordComparable = (keyword) => {
  return keyword
    ?.normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toUpperCase()
}

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
      console.log('Fresh users list from BE.')
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
          makeWordComparable(user.full_name).includes(
            makeWordComparable(searchKeyword)
          )
        )
    )
  }, [listOfAllUsers, searchKeyword, sortKey, isSortAscending, sortColumn])

  return { users, fetchUsers, sendRegistrationApproval, deleteUser }
}

export default useUsers
