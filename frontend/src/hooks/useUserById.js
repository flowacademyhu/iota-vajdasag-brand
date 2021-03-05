import { useState, useEffect } from 'react'
import { getOneUser } from '../communications/userApi'

const useUserById = (ownerId) => {
  const [userData, setUserData] = useState([])

  const fetchOneUser = async (ownerId) => {
      const fetchedUser = await getOneUser(ownerId)
      setUserData(fetchedUser)
  }

  useEffect(() => {
      fetchOneUser(ownerId)
  }, [ownerId])

  return userData
}

export default useUserById
