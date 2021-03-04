import { useState, useEffect } from 'react'
import { getOneUser } from '../communications/userApi'

const useUserById = (userId) => {
  const [userData, setUserData] = useState({})

  const fetchOneUser = async (userId) => {
      const fetchedUser = await getOneUser(userId)
      setUserData(fetchedUser)
  }

  useEffect(() => {
      fetchOneUser(userId)
  }, [userId])

  return userData
}

export default useUserById
