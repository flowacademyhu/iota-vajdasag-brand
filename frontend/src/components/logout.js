import useTokenStateHandler from '../hooks/useTokenStateHandler'
import { Redirect } from 'react-router-dom'

const Logout = () => {
  const { deleteToken } = useTokenStateHandler()
  deleteToken()

  return <Redirect to="/login" />
}

export default Logout
