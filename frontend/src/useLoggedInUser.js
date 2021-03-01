import { useContext } from 'react'
import { TokenContext } from './TokenContext'
import jwt from 'jwt-simple'

const useLoggedInUser = () => {
  const { token } = useContext(TokenContext)

  try {
    const decoded = jwt.decode(token, false, 'RS256')
    const role = decoded?.resource_access.VajdasagClient.roles.includes(
      'SuperAdmin'
    )
      ? 'SuperAdmin'
      : 'CegAdmin'
    const email = decoded?.email
    return { role, email }
  } catch (error) {
    return { role: '', email: '' }
  }
}

export default useLoggedInUser
